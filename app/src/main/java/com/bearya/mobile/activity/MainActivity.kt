package com.bearya.mobile.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.bearya.mobile.listener.R
import com.bearya.mobile.listener.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bindView: ActivityMainBinding

    private val fragmentLifecycleCallback = object : FragmentManager.FragmentLifecycleCallbacks() {

        override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
            if("SupportRequestManagerFragment" != f.javaClass.simpleName) {
                Log.i("FragmentLifecycle", "===== ${f.javaClass.simpleName} onFragmentCreated")
            }
        }

        override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
            if("SupportRequestManagerFragment" != f.javaClass.simpleName) {
                Log.i("FragmentLifecycle", "===== ${f.javaClass.simpleName} onFragmentViewCreated")
            }
        }

        override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
            if("SupportRequestManagerFragment" != f.javaClass.simpleName) {
                Log.i("FragmentLifecycle", "===== ${f.javaClass.simpleName} onFragmentResumed")
            }
        }

        override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
            if("SupportRequestManagerFragment" != f.javaClass.simpleName) {
                Log.i("FragmentLifecycle", "===== ${f.javaClass.simpleName} onFragmentPaused")
            }
        }

        override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
            if("SupportRequestManagerFragment" != f.javaClass.simpleName) {
                Log.i("FragmentLifecycle", "===== ${f.javaClass.simpleName} onFragmentDestroyed")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView = DataBindingUtil.setContentView(this, R.layout.activity_main)
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentLifecycleCallback, true)
        val navFragment = supportFragmentManager.findFragmentById(R.id.container_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(bindView.mainBottomMenu, navFragment.navController)
    }

    override fun onSupportNavigateUp(): Boolean = findNavController(R.id.container_fragment).navigateUp()

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentLifecycleCallback)
    }

}