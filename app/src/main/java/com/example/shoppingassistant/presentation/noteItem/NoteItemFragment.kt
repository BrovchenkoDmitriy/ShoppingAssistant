package com.example.shoppingassistant.presentation.noteItem

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.media.ExifInterface
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shoppingassistant.R
import com.example.shoppingassistant.databinding.FragmentNoteItemBinding
import com.example.shoppingassistant.databinding.NoteItemBinding
import com.example.shoppingassistant.domain.PositionItem
import com.example.shoppingassistant.domain.ShopItem
import com.example.shoppingassistant.presentation.noteList.PictureUtils
import com.example.shoppingassistant.presentation.positionItem.PositionItemFragment
import java.io.File


class NoteItemFragment : Fragment() {

    private var screenMode = SCREEN_MODE_UNDEFINED
    private var shopItemId: Int = ShopItem.UNDEFINED_ID

    private val viewModel by lazy {
        ViewModelProvider(this)[NoteItemViewModel::class.java]
    }
    private lateinit var onEditingIsFinishedListener: OnEditingIsFinishedListener
    private lateinit var photoUri: Uri
    private lateinit var photoFile: File

    private var _binding: FragmentNoteItemBinding? = null
    private val binding: FragmentNoteItemBinding
        get() = _binding ?: throw RuntimeException("FragmentNoteItemBinding == null")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnEditingIsFinishedListener) {
            onEditingIsFinishedListener = context
        } else throw RuntimeException("Activity must implement OnEditingIsFinishedListener")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNoteItemBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chooseRightMode()
        setupViewModelObservers()

        viewModel.shopItem.observe(viewLifecycleOwner){
            photoFile = viewModel.getPhotoFile(it)
            photoUri = FileProvider.getUriForFile(
                requireActivity(),
                "com.example.shoppingassistant.fileprovider",
                photoFile
            )
            updatePhotoView()
        }



        binding.createPhotoButton.apply {
            val packageManager: PackageManager = requireActivity().packageManager
            val captureImage = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val resolvedActivity: ResolveInfo? =
                packageManager.resolveActivity(captureImage, PackageManager.MATCH_DEFAULT_ONLY)
            if (resolvedActivity == null) {
                isEnabled = false
            }

            setOnClickListener {

                captureImage.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                val cameraActivities: List<ResolveInfo> =
                    packageManager.queryIntentActivities(
                        captureImage,
                        PackageManager.MATCH_DEFAULT_ONLY
                    )
                for (cameraActivity in cameraActivities) {
                    requireActivity().grantUriPermission(
                        cameraActivity.activityInfo.packageName, photoUri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                }

                startActivityForResult(captureImage, REQUEST_PHOTO)
            }
        }

    }

    private fun updatePhotoView() {
        if (photoFile.exists()) {
            val ei = ExifInterface(photoFile.path)
            val bitmap =
                PictureUtils().getRotatedBitmap(
                    PictureUtils().getScaledBitmap(
                        photoFile.path,
                        requireActivity()
                    ), ei
                )

            binding.imNoteItemPhoto.setImageBitmap(bitmap)
        }
//        else {
//            binding.imNoteItemPhoto.setImageDrawable(null)
//        }
    }

    private fun launchEditMode() {
        viewModel.getShopItem(shopItemId)
        viewModel.shopItem.observe(viewLifecycleOwner) {
            with(binding) {
                etNoteItemName.setText(it.name)
                etNoteItemCategory.setText(it.category)
                etNoteItemPrice.setText(it.price.toString())
                etMarketName.setText(it.marketName)
                tvDate.text = it.date.toString()
            }
        }
        binding.saveNoteItemButton.setOnClickListener {
            viewModel.upgradeShopItem(
                binding.etNoteItemName.text?.toString(),
                binding.etNoteItemCategory.text?.toString(),
                binding.etNoteItemPrice.text?.toString()?.toDouble(),
                binding.etMarketName.text?.toString()
            )
        }
    }

    private fun launchAddMode() {
        //время создания карточки продукта
       // val format = SimpleDateFormat("dd MMMM, HH:mm", Locale.getDefault())
        val currentTime = System.currentTimeMillis()
        //берем координаты устройства
        val lat = 1000.0
        val lng = 1000.0
        binding.saveNoteItemButton.setOnClickListener {
            viewModel.addShopItem(
                binding.etNoteItemName.text?.toString(),
                binding.etNoteItemCategory.text?.toString(),
                binding.etNoteItemPrice.text?.toString()?.toDouble(),
                binding.etMarketName.text?.toString(),
                currentTime,
                lat,
                lng
            )
        }
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = args.getString(SCREEN_MODE)

        if (mode != MODE_ADD && mode != MODE_EDIT) {
            throw java.lang.RuntimeException("Param screen mode is wrong")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!args.containsKey(SHOP_ITEM_ID)) {
                throw java.lang.RuntimeException("Param shopItemId is absent")
            }
            shopItemId = args.getInt(SHOP_ITEM_ID, PositionItem.UNDEFINED_ID)
        }
    }

    private fun chooseRightMode() {
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
    }

    private fun setupViewModelObservers() {
        viewModel.closeShopItemScreen.observe(viewLifecycleOwner) {
            onEditingIsFinishedListener.onEditingIsFinishedListener()
        }
    }

    interface OnEditingIsFinishedListener {
        fun onEditingIsFinishedListener()
    }

    companion object {
        private const val SCREEN_MODE = "extra_mode"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val SHOP_ITEM_ID = "ExtraShopItemID"
        private const val SCREEN_MODE_UNDEFINED = ""
        private const val REQUEST_PHOTO = 101

        fun newInstanceAddItem(): Fragment {
            return NoteItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }

        fun newInstanceEditItem(noteItemId: Int): Fragment {
            return NoteItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(SHOP_ITEM_ID, noteItemId)
                }
            }
        }
    }

}