package com.netguru.codereview.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.netguru.codereview.ui.MainViewModel


class Util {
    companion object {
        // if not using dagger we can use it here as generic call for fragment and activity declaration
        fun declarViewModel(activity: Fragment?): MainViewModel? { // listen life cycle to fragment only
            return ViewModelProvider(activity!!).get(MainViewModel::class.java)
        }
        fun declarViewModel(activity: FragmentActivity?): MainViewModel? {
            return ViewModelProvider(activity!!).get(MainViewModel::class.java)
        }


    }
}