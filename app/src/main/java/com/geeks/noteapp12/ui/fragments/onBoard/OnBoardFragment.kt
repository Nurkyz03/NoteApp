package com.geeks.noteapp12.ui.fragments.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.geeks.noteapp12.R
import com.geeks.noteapp12.ui.adapter.OnBoardPagerAdapter
import com.geeks.noteapp12.databinding.FragmentOnBoardBinding
import com.geeks.noteapp12.utils.PreferenceHelper
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setUplisteners()
        tabLayout()
    }

    private fun initialize() {
        binding.viewPaper2.adapter = OnBoardPagerAdapter(this)
    }

    private fun setUplisteners() = with(binding.viewPaper2){
        binding.viewPaper2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        binding.txtSkip.visibility = View.VISIBLE
                        binding.btnStart.visibility = View.INVISIBLE
                        binding.tabLayout1.setBackgroundResource(R.drawable.selected_dot)
                        binding.tabLayout2.setBackgroundResource(R.drawable.default_dot)
                        binding.tabLayout3.setBackgroundResource(R.drawable.default_dot)
                    }
                    1 -> {
                        binding.txtSkip.visibility = View.VISIBLE
                        binding.btnStart.visibility = View.INVISIBLE
                        binding.tabLayout1.setBackgroundResource(R.drawable.default_dot)
                        binding.tabLayout2.setBackgroundResource(R.drawable.selected_dot)
                        binding.tabLayout3.setBackgroundResource(R.drawable.default_dot)
                    }
                    2 -> {
                        binding.txtSkip.visibility = View.INVISIBLE
                        binding.btnStart.visibility = View.VISIBLE
                        binding.tabLayout1.setBackgroundResource(R.drawable.default_dot)
                        binding.tabLayout2.setBackgroundResource(R.drawable.default_dot)
                        binding.tabLayout3.setBackgroundResource(R.drawable.selected_dot)
                    }
                }
            }
        })

        binding.txtSkip.setOnClickListener {
            if (currentItem < 3)
                setCurrentItem(currentItem + 2, true)
        }
        binding.btnStart.setOnClickListener {
            val sharedPreferenceHelper = PreferenceHelper()
            sharedPreferenceHelper.unit(requireContext())
            sharedPreferenceHelper.isOnBoardShown = true
            findNavController().navigate(R.id.action_onBoardFragment_to_signUpFragment)
        }
    }

    private fun tabLayout() {
        TabLayoutMediator(binding.tabLayout1, binding.viewPaper2){ _, _ ->
        }.attach()
        TabLayoutMediator(binding.tabLayout2, binding.viewPaper2){ _, _ ->
        }.attach()
        TabLayoutMediator(binding.tabLayout3, binding.viewPaper2){ _, _ ->
        }.attach()
    }
}