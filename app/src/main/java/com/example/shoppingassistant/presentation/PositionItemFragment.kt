package com.example.shoppingassistant.presentation

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shoppingassistant.databinding.FragmentPositionItemBinding
import com.example.shoppingassistant.domain.PositionItem

class PositionItemFragment : Fragment() {

    private var screenMode = SCREEN_MODE_UNDEFINED
    private var positionItemId: Int = PositionItem.UNDEFINED_ID

    private lateinit var onEditingIsFinishedListener: OnEditingIsFinishedListener

    private var _binding: FragmentPositionItemBinding? = null
    private val binding: FragmentPositionItemBinding
        get() = _binding ?: throw RuntimeException("FragmentPositionItemBinding == null")

    val viewModel by lazy {
        ViewModelProvider(this)[PositionItemViewModel::class.java]
    }

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
    ): View {
        _binding = FragmentPositionItemBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nameAndCountTextListener()
        chooseRightMode()
        setupViewModelObservers()
    }

    private fun setupViewModelObservers() {
        viewModel.closePositionItemScreen.observe(viewLifecycleOwner) {
            onEditingIsFinishedListener.onEditingIsFinishedListener()
        }
    }

    private fun nameAndCountTextListener() {
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetInputNameError()
            }

            override fun afterTextChanged(s: Editable?) {
//                if (s?.isBlank() == true) binding.tilName.error =
//                    getString(R.string.error_input_name)
            }
        })

        binding.etCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetInputCountError()
            }

            override fun afterTextChanged(s: Editable?) {
//                if ((s == null) || s.isBlank() || (s.toString().toInt() <= 0)) {
//                    binding.tilCount.error = getString(R.string.error_input_count)
//                }
            }
        })
    }

    private fun chooseRightMode() {
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
    }

    private fun launchEditMode() {
        viewModel.getPositionItem(positionItemId)
        binding.saveButton.setOnClickListener {
            viewModel.upgradePositionItem(
                binding.etName.text?.toString(),
                binding.etCount.text?.toString()
            )
        }
    }

    private fun launchAddMode() {
        binding.saveButton.setOnClickListener {
            viewModel.addPositionItem(
                binding.etName.text?.toString(),
                binding.etCount.text?.toString()
            )
        }
    }

    interface OnEditingIsFinishedListener {
        fun onEditingIsFinishedListener()
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
        Log.d("TAGIL", screenMode)
        if (screenMode == MODE_EDIT) {
            if (!args.containsKey(SHOP_ITEM_ID)) {
                throw java.lang.RuntimeException("Param shopItemId is absent")
            }
            positionItemId = args.getInt(SHOP_ITEM_ID, PositionItem.UNDEFINED_ID)
            Log.d("TAGIL", positionItemId.toString())
        }
    }

    companion object {
        private const val SCREEN_MODE = "extra_mode"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val SHOP_ITEM_ID = "ExtraShopItemID"
        private const val SCREEN_MODE_UNDEFINED = ""

        fun newInstanceAddItem(): Fragment {
            return PositionItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }

        fun newInstanceEditItem(shopItemId: Int): Fragment {
            return PositionItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(SHOP_ITEM_ID, shopItemId)
                }
            }
        }
    }

}