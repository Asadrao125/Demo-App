package com.technado.demoapp.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.technado.demoapp.activities.MainActivity
import com.technado.demoapp.helper.BasePreferenceHelper
import com.technado.demoapp.helper.Titlebar

abstract class BaseFragment : Fragment() {
    var getActivityContext: MainActivity? = null
    var preferenceHelper: BasePreferenceHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setPreferenceHelper()
    }

    abstract fun setTitlebar(titlebar: Titlebar)

    fun getActivityContext(): MainActivity? {
        return getActivityContext
    }

    fun setPreferenceHelper() {
        if (preferenceHelper == null) {
            preferenceHelper = BasePreferenceHelper(getActivityContext)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            val contex = context as MainActivity?
            if (contex != null)
                getActivityContext = context
        }
    }

    override fun onResume() {
        super.onResume()
        if (getActivityContext != null) {
            setTitlebar(getActivityContext!!.getTitlebar())
        }
    }
}