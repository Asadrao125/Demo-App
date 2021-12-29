package com.technado.demoapp.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import com.technado.demoapp.R
import com.technado.demoapp.base.BaseFragment
import com.technado.demoapp.databinding.AboutFragmentBinding
import com.technado.demoapp.helper.CustomTextWatcher
import com.technado.demoapp.helper.Titlebar

class AboutFragment : BaseFragment() {
    var binding: AboutFragmentBinding? = null
    lateinit var result: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false)

        getActivityContext()?.lockMenu()
        getActivityContext?.hideBttomBar()

        /*binding?.edtEmail?.doOnTextChanged { text, start, before, count ->
            if (text?.trim()?.length!! > 0) {
                binding?.btnLogin?.isEnabled = true
                result = text.trim().toString()
            } else {
                binding?.btnLogin?.isEnabled = false
            }
        }*/

        binding?.edtEmail?.addTextChangedListener(CustomTextWatcher(
            onChanged = { text, start, before, count ->
                if (binding?.edtPassword?.length()!! >= 6 && Patterns.EMAIL_ADDRESS.matcher(
                        text
                    ).matches()
                ) {
                    result = text?.trim().toString()
                    binding?.btnLogin?.isEnabled = true
                } else {
                    binding?.btnLogin?.isEnabled = false
                }
            }
        ))

        binding?.edtPassword?.addTextChangedListener(CustomTextWatcher(
            onChanged = { text, start, before, count ->
                val email = binding?.edtEmail?.text?.trim()
                if (text?.trim()?.length!! >= 6 && Patterns.EMAIL_ADDRESS.matcher(
                        email
                    ).matches()
                ) {
                    binding?.btnLogin?.isEnabled = true
                    result = text.trim().toString()
                } else {
                    binding?.btnLogin?.isEnabled = false
                }
            }
        ))

        binding?.btnLogin?.setOnClickListener(View.OnClickListener {
            val email = binding?.edtEmail?.text?.trim()
            val pass = binding?.edtPassword?.text?.trim()
            Toast.makeText(
                getActivityContext,
                "Email: " + email + "\nPass: " + pass,
                Toast.LENGTH_SHORT
            ).show()
        })

        return binding?.root
    }

    override fun setTitlebar(titlebar: Titlebar) {
        titlebar.setBackTitle(getActivityContext!!, "About")
    }
}