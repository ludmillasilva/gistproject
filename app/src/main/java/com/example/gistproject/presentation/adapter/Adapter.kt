package com.example.gistproject.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gistproject.presentation.fragment.FavoriteGist
import com.example.gistproject.presentation.fragment.FragmentListGist

class Adapter (fa: FragmentActivity): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 1
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> FragmentListGist()
         //   1 -> FavoriteGist() TODO
            else -> FragmentListGist()

        }

    }

}