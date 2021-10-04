package com.example.gistproject.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gistproject.presentation.fragment.FragmentFavoriteGist
import com.example.gistproject.presentation.fragment.FragmentListGist

class Adapter (fa: FragmentActivity): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> FragmentListGist()
            1 -> FragmentFavoriteGist()
            else -> FragmentListGist()

        }

    }

}