package com.example.shoppingassistant.presentation.noteList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingassistant.R
import com.example.shoppingassistant.databinding.FragmentNoteListBinding
import com.example.shoppingassistant.presentation.noteItem.NoteItemFragment
import com.example.shoppingassistant.presentation.noteList.noteListRecycler.NoteListAdapter

class NoteListFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this)[NoteListViewModel::class.java]
    }

    private lateinit var noteListAdapter: NoteListAdapter

    private var _binding: FragmentNoteListBinding? = null
    private val binding: FragmentNoteListBinding
        get() = _binding ?: throw RuntimeException("FragmentShoppingListBinding is null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.noteItemList.observe(viewLifecycleOwner){
            noteListAdapter.submitList(it)
        }
        binding.floatingActionButton.setOnClickListener {
            launchNoteItemFragment(NoteItemFragment.newInstanceAddItem())
        }
    }

    private fun setupRecyclerView(){
        noteListAdapter = NoteListAdapter()
        binding.rvNoteList.adapter = noteListAdapter
        //binding.rvNoteList.itemAnimator= DefaultItemAnimator()
        setupItemClickListener()
        setupSwipeListener(binding.rvNoteList)
    }

    private fun setupItemClickListener() {
        noteListAdapter.onPositionItemClickListener = {
            launchNoteItemFragment(NoteItemFragment.newInstanceEditItem(it.id))
        }
    }

    private fun launchNoteItemFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .addToBackStack(null).commit()
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
                val item = noteListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteNoteItem(item)
            }

        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvPositionItemList)
    }


}