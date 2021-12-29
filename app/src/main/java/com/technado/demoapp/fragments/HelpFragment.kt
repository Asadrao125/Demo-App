package com.technado.demoapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.technado.demoapp.R
import com.technado.demoapp.adapter.DummyAdapter
import com.technado.demoapp.base.BaseFragment
import com.technado.demoapp.databinding.HelpFragmentBinding
import com.technado.demoapp.helper.Titlebar

class HelpFragment : BaseFragment() {
    var binding: HelpFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_help, container, false)

        getActivityContext()?.lockMenu()
        getActivityContext?.hideBttomBar()

        binding?.recyclerView?.layoutManager = LinearLayoutManager(getActivityContext)
        binding?.recyclerView?.setHasFixedSize(true)
        binding?.recyclerView?.adapter =
            DummyAdapter(getActivityContext!!, resources.getStringArray(R.array.dummy).toList())

        /*Toast.makeText(getActivityContext, "" + getActivityContext?.preferenceHelper?.getLoginStatus(), Toast.LENGTH_SHORT).show()*/

        return binding?.root
    }

    override fun setTitlebar(titlebar: Titlebar) {
        titlebar.setBackTitle(getActivityContext!!, "Help")
        titlebar.binding
    }
}