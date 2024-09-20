package com.geeks.noteapp12.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.geeks.noteapp12.ui.fragments.onBoard.OnBoardPagingFragment
import com.geeks.noteapp12.ui.fragments.onBoard.OnBoardPagingFragment.Companion.ARG_ONBOARD_POSITION

class OnBoardPagerAdapter(fragment: Fragment):FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int) = OnBoardPagingFragment().apply {
        arguments = Bundle().apply {
            putInt(ARG_ONBOARD_POSITION,position)
        }
    }
}