package com.example.gistproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.gistproject.presentation.adapter.Adapter
import com.example.gistproject.presentation.adapter.GitsAdapter
import com.example.gistproject.presentation.fragment.FragmentListGist
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    lateinit var viewpager: ViewPager2
    lateinit var tablayout: TabLayout
    private var fragmentListGist: FragmentListGist? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewpager= findViewById(R.id.viewPager)
        tablayout= findViewById(R.id.tabLayout)

        viewpager.adapter = Adapter(this)
        viewpager.isUserInputEnabled = false
        TabLayoutMediator(tablayout, viewpager){ tab, position ->
            tab.text = tabTitle(position)
        }.attach()

    }

    private fun tabTitle(position: Int): String{
        return when (position){
            0 -> "Lista de Gists"
            1 -> "Favoritos"
            else -> ""
        }
    }
}