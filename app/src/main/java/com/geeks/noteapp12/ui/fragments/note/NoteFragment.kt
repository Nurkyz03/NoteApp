package com.geeks.noteapp12.ui.fragments.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.noteapp12.App
import com.geeks.noteapp12.R
import com.geeks.noteapp12.databinding.FragmentNoteBinding
import com.geeks.noteapp12.ui.adapter.NoteAdapter

class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding
    private val noteAdapter = NoteAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setUpListeners()
        getData()
    }

    private fun initialize() {
        binding.homeRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdapter
        }
    }

    private fun setUpListeners() = with(binding) {
        btnAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_noteDetailFragment)
        }
    }
    private fun getData() {
        App.appDatabase?.noteDao()?.getAll()?.observe(viewLifecycleOwner){ list ->
            noteAdapter.submitList(list)
        }
    }
}