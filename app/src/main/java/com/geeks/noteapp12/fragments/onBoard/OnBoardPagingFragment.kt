package com.geeks.noteapp12.fragments.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geeks.noteapp12.R
import com.geeks.noteapp12.databinding.FragmentOnBoardPagingBinding

class OnBoardPagingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardPagingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardPagingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() = with(binding)  {
        when (requireArguments().getInt(ARG_ONBOARD_POSITION)){
            0 -> {
                txtTitle.text = "Удобство"
                lottieView.setAnimation(R.raw.lottie1)
                btnStart.visibility = View.INVISIBLE
                txtDescription.text = "Создавайте заметки в два клика! Записывайте мысли, идеи и важные задачи мгновенно."
            }
            1 -> {
                txtTitle.text = "Организация"
                lottieView.setAnimation(R.raw.lottie_2)
                btnStart.visibility = View.INVISIBLE
                txtDescription.text = "Организуйте заметки по папкам и тегам. Легко находите нужную информацию в любое время."
            }
            2 -> {
                txtTitle.text = "Синхронизация"
                lottieView.setAnimation(R.raw.lottie_3)
                txtDescription.text = "Синхронизация на всех устройствах. Доступ к записям в любое время и в любом месте."
            }
        }
    }

    companion object {
        const val ARG_ONBOARD_POSITION = "onBoard"
    }
}


