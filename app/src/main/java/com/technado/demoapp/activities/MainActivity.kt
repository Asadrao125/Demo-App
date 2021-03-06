package com.technado.demoapp.activities

import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.technado.demoapp.R
import com.technado.demoapp.base.BaseActivity
import com.technado.demoapp.databinding.MainActivityBinding
import com.technado.demoapp.dialogFragments.ExitDialog
import com.technado.demoapp.fragments.AboutFragment
import com.technado.demoapp.fragments.HelpFragment
import com.technado.demoapp.fragments.HomeFragment
import com.technado.demoapp.helper.Titlebar

class MainActivity : BaseActivity(), View.OnClickListener {
    var binding: MainActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setMainFrameLayoutID()
        setListener()

        preferenceHelper?.setLoginStatus("true")

        replaceFragment(HomeFragment(), HomeFragment::class.java.simpleName, true, false)
    }

    fun setListener() {
        binding?.helpLayout?.setOnClickListener(this)
        binding?.aboutLayout?.setOnClickListener(this)
        binding?.llAbout?.setOnClickListener(this)
        binding?.llHelp?.setOnClickListener(this)
    }

    fun getTitlebar(): Titlebar {
        return binding!!.titlebar
    }

    fun hideBttomBar() {
        binding?.llBotomBar?.visibility = View.GONE
    }

    fun showBttomBar() {
        binding?.llBotomBar?.visibility = View.VISIBLE
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            val fragmentManager = supportFragmentManager
            val fragments: List<Fragment> = fragmentManager.fragments
            val last: Fragment = fragments.get(fragments.size - 1)

            supportFragmentManager.popBackStack()

        } else {
            val exitDialog = ExitDialog()
            exitDialog.show(supportFragmentManager, "exitDialog")
        }
    }

    fun mainHideTitle() {
        binding!!.titlebar.visibility = View.GONE
    }

    fun mainShowTitle() {
        binding!!.titlebar.visibility = View.VISIBLE
    }

    fun clearBackStack() {
        val fragmentManager = supportFragmentManager
        for (i in 0 until fragmentManager.backStackEntryCount) {
            fragmentManager.popBackStack()
        }
    }

    override fun setMainFrameLayoutID() {
        mainFrameLayoutID = binding?.mainContainer!!.id
    }

    fun unlockMenu() {
        binding!!.drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    fun lockMenu() {
        binding!!.drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    fun openDrawer() {
        binding!!.drawerlayout.openDrawer(GravityCompat.START)
    }

    fun closeDrawers() {
        binding!!.drawerlayout.closeDrawers()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.aboutLayout -> {
                replaceFragment(AboutFragment(), AboutFragment::class.java.simpleName, true, true)
            }

            R.id.helpLayout -> {
                replaceFragment(HelpFragment(), HelpFragment::class.java.simpleName, true, true)
            }

            R.id.llAbout -> {
                replaceFragment(AboutFragment(), AboutFragment::class.java.simpleName, true, true)
            }

            R.id.llHelp -> {
                replaceFragment(HelpFragment(), HelpFragment::class.java.simpleName, true, true)
            }
        }
    }
}