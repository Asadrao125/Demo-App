package com.technado.demoapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.technado.demoapp.R
import com.technado.demoapp.base.BaseFragment
import com.technado.demoapp.databinding.HomeFragmentBinding
import com.technado.demoapp.helper.Titlebar

class HomeFragment : BaseFragment() {
    var binding: HomeFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        getActivityContext()?.unlockMenu()
        getActivityContext?.showBttomBar()

        return binding?.root
    }

    override fun setTitlebar(titlebar: Titlebar) {
        titlebar.setTitle(getActivityContext!!, "Home")
    }
}