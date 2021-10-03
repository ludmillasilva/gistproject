package com.example.gistproject.presentation.fragment

import androidx.fragment.app.Fragment
import com.example.gistproject.presentation.adapter.GitsAdapter

class FragmentListGist: Fragment(), ListenerGists {

    lateiniti var gitsAdapter: GitsAdapter
}