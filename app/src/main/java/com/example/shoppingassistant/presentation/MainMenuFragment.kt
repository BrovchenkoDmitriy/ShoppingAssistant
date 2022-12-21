package com.example.shoppingassistant.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shoppingassistant.R
import com.example.shoppingassistant.databinding.FragmentMainMenuBinding


class MainMenuFragment : Fragment() {

    private var _binding: FragmentMainMenuBinding? = null
    private val binding: FragmentMainMenuBinding
        get() {
            return _binding ?: throw RuntimeException("FragmentMainMenuBinding is null")
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonShoppingList.setOnClickListener {
            findNavController().navigate(R.id.action_mainMenuFragment_to_shoppingListFragment)
        }
        binding.buttonNotes.setOnClickListener {
            findNavController().navigate(R.id.action_mainMenuFragment_to_noteListFragment)
        }
    }

}