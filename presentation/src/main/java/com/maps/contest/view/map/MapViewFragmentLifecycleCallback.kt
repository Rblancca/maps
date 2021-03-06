package com.maps.contest.view.map

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object MapViewFragmentLifecycleCallback : FragmentManager.FragmentLifecycleCallbacks() {

    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
        if (f is MapProvider) {
            f.getMapView()?.onResume()
        }
    }

    override fun onFragmentViewCreated(
        fm: FragmentManager,
        f: Fragment,
        v: View,
        savedInstanceState: Bundle?
    ) {
        if (f is MapProvider) {
            f.getMapView()?.onCreate(savedInstanceState)
        }
    }

    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        if (f is MapProvider) {
            f.getMapView()?.onDestroy()
        }
    }

    override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
        if (f is MapProvider && f.getMapView() != null && f.getMapView()!!.isLaidOut) {
            f.getMapView()!!.onStart()
        }
    }

    override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
        if (f is MapProvider) {
            f.getMapView()?.onStop()
        }
    }

    override fun onFragmentSaveInstanceState(fm: FragmentManager, f: Fragment, outState: Bundle) {
        if (f is MapProvider) {
            f.getMapView()?.onSaveInstanceState(outState)
        }
    }

    override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
        if (f is MapProvider) {
            f.getMapView()?.onPause()
        }
    }

}
