package com.example.shoppingassistant.presentation

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingassistant.R
import com.example.shoppingassistant.databinding.FragmentPositionItemBinding
import com.example.shoppingassistant.databinding.FragmentShoppingListBinding
import com.example.shoppingassistant.presentation.shoppingListRecycler.PositionListAdapter
import kotlin.concurrent.thread

class ShoppingListFragment : Fragment() {

    private var _binding: FragmentShoppingListBinding? = null
    private val binding: FragmentShoppingListBinding
        get() = _binding ?: throw RuntimeException("FragmentShoppingListBinding is null")

    private lateinit var positionListAdapter: PositionListAdapter

    private val viewModel by lazy {
        ViewModelProvider(this)[ShoppingListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentShoppingListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.positionItemList.observe(viewLifecycleOwner) {
            positionListAdapter.submitList(it)
        }

        binding.buttonAddPositionItem.setOnClickListener {
            launchPositionItemFragment(PositionItemFragment.newInstanceAddItem())
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvShopList) {
            positionListAdapter = PositionListAdapter()
            adapter = positionListAdapter
            //устанавливаем максимальный пул для разных TypeView
        }
        setupLongClickListener()
        setupClickListener()
        setupSwipeListener(binding.rvShopList)
    }

    private fun setupSwipeListener(rvPositionItemList: RecyclerView) {
        val callback = object :
            ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = positionListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deletePositionItem(item)
            }

        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvPositionItemList)
    }

    private fun setupClickListener() {
        positionListAdapter.onPositionItemClickListener = {
            launchPositionItemFragment(PositionItemFragment.newInstanceEditItem(it.id))
        }
    }

    private fun setupLongClickListener() {
        positionListAdapter.onPositionItemLongClickListener = {
            viewModel.upgradePositionItem(it)
        }
    }

    private fun launchPositionItemFragment(fragment: Fragment) {
       // requireActivity().supportFragmentManager.popBackStack()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .addToBackStack(null).commit()
    }
}