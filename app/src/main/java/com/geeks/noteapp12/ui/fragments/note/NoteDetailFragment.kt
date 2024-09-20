package com.geeks.noteapp12.ui.fragments.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geeks.noteapp12.databinding.FragmentNoteDetailBinding
import java.text.SimpleDateFormat
import java.util.Locale
import android.text.Editable
import android.text.TextWatcher
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.geeks.noteapp12.App
import com.geeks.noteapp12.R
import com.geeks.noteapp12.ui.adapter.ColorAdapter
import com.geeks.noteapp12.ui.data.models.ColorModel
import com.geeks.noteapp12.ui.data.models.NoteModel
import java.util.Calendar


class NoteDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteDetailBinding
    private lateinit var dateTime: String
    private lateinit var RVcolor: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDateAndTime()
        setColorDropdown()
        setupListeners()
    }

    private fun setDateAndTime() = with(binding) {
        val calendar = Calendar.getInstance().time
        val russianLocale = Locale("ru")
        val dateFormat = SimpleDateFormat("dd MMMM", russianLocale).format(calendar)
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault()).format(calendar)
        txtDate.text = dateFormat
        txtTime.text = timeFormat
        dateTime = "$dateFormat $timeFormat"
    }

    private fun setColorDropdown() = with(binding.menuColor) {
        val colors = listOf(
            ColorModel(R.drawable.ic_yellow, "#FFF599"),
            ColorModel(R.drawable.ic_purple, "#B69CFF"),
            ColorModel(R.drawable.ic_pink, "#FD99FF"),
            ColorModel(R.drawable.ic_orange_pink, "#FB9E9E"),
            ColorModel(R.drawable.ic_green, "#91F48F"),
            ColorModel(R.drawable.ic_blue, "#9EFFFF"),
        )
        adapter = ColorAdapter(requireContext(), colors)
        onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedItem = colors[p2]
                RVcolor = colors[p2].color
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }

    private fun setupListeners() = with(binding) {
        btnBack.setOnClickListener { findNavController().navigateUp() }
        isReadyVisible(etTitle, btnReady, menuColor)
        isReadyVisible(etDescription, btnReady, menuColor)
        btnReady.setOnClickListener {
            val etTitle = binding.etTitle.text.toString()
            val etDescription = binding.etDescription.text.toString()
            App.appDatabase?.noteDao()
                ?.insertMode(NoteModel(etTitle, etDescription, dateTime, RVcolor))
            findNavController().navigateUp()
        }
    }

    private fun isReadyVisible(editText: EditText, textView: TextView, spinner: Spinner) {
        editText.addTextChangedListener(object : TextWatcher {
            val params = spinner.layoutParams as ViewGroup.MarginLayoutParams
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotEmpty()) {
                    textView.visibility = View.VISIBLE
                    params.marginEnd = (resources.displayMetrics.density * 60).toInt()
                    spinner.layoutParams = params
                } else {
                    textView.visibility = View.INVISIBLE
                    params.marginEnd = (resources.displayMetrics.density * 20).toInt()
                    spinner.layoutParams = params
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }
}