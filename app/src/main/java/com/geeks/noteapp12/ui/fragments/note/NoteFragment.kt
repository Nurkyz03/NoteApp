package com.geeks.noteapp12.ui.fragments.note

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.noteapp12.App
import com.geeks.noteapp12.R
import com.geeks.noteapp12.databinding.FragmentNoteBinding
import com.geeks.noteapp12.interfaces.OnClickItem
import com.geeks.noteapp12.ui.adapter.NoteAdapter
import com.geeks.noteapp12.ui.data.models.NoteModel
import com.geeks.noteapp12.utils.PreferenceHelper

class NoteFragment : Fragment(), OnClickItem {

    private lateinit var binding: FragmentNoteBinding
    private val noteAdapter = NoteAdapter(this, this)
    private lateinit var toggle: ActionBarDrawerToggle

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
            val sharedPreferences = PreferenceHelper()
            sharedPreferences.unit(requireContext())
            if (sharedPreferences.isRecyclerViewGrid){
                layoutManager = GridLayoutManager(requireContext(), 2)
                binding.btnChangerRv.setImageResource(R.drawable.ic_rv2)
            }else if (!sharedPreferences.isRecyclerViewGrid){
                layoutManager = LinearLayoutManager(requireContext())
                binding.btnChangerRv.setImageResource(R.drawable.ic_rv)
            }
            adapter = noteAdapter
        }
        binding.apply {
            toggle = ActionBarDrawerToggle(
                requireActivity(),
                drawerLayout,
                R.string.open,
                R.string.close
            )
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            navView.setNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.onBoardFragment ->
                        findNavController().navigate(R.id.onBoardFragment)

                    R.id.noteFragment ->
                        findNavController().navigate(R.id.noteFragment)

                    R.id.chatFragment ->
                        findNavController().navigate(R.id.chatFragment)
                }
                true
            }
        }
    }

    private fun setUpListeners() = with(binding) {
        btnAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_noteDetailFragment)
        }
        btnChangerRv.setOnClickListener {
            val sharedPreferences = PreferenceHelper()
            sharedPreferences.unit(requireContext())
            if (!sharedPreferences.isRecyclerViewGrid) {
                btnChangerRv.setImageResource(R.drawable.ic_rv2)
                homeRecyclerView.apply {
                    layoutManager = GridLayoutManager(requireContext(), 2)
                    sharedPreferences.isRecyclerViewGrid = true
                }
            } else if (sharedPreferences.isRecyclerViewGrid) {
                btnChangerRv.setImageResource(R.drawable.ic_rv)
                homeRecyclerView.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    sharedPreferences.isRecyclerViewGrid = false
                }
            }
        }
        btnMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    private fun getData() {
        App.appDatabase?.noteDao()?.getAll()?.observe(viewLifecycleOwner){ list ->
            noteAdapter.submitList(list)
        }
    }

    override fun onLongClick(noteModel: NoteModel) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("Вы точно хотите удалить заметку?")
            setPositiveButton("Да") { dialog, _ ->
                App.appDatabase?.noteDao()?.deleteNote(noteModel)
            }
            setNegativeButton("Нет") { dialog, _ ->
                dialog.cancel()
            }
            show()
        }
        builder.create()
    }

    override fun onClick(noteModel: NoteModel) {
        val action = NoteFragmentDirections.actionNoteFragmentToNoteDetailFragment(noteModel.id)
        findNavController().navigate(action)
    }
}