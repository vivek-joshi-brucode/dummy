package com.example.dummy.ui.fragments.privacy

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.camera.core.impl.utils.CameraOrientationUtil
import com.bumptech.glide.Glide
import com.example.dummy.databinding.FragmentPrivacyBinding
import com.example.dummy.ui.base.BaseFragment
import hilt_aggregated_deps._dagger_hilt_android_flags_FragmentGetContextFix_FragmentGetContextFixEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.processNextEventInCurrentThread
import okhttp3.internal.wait

/**
 * This is my personal Experimental Space
 **/

class PrivacyFragment : BaseFragment<FragmentPrivacyBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPrivacyBinding = FragmentPrivacyBinding.inflate(layoutInflater)

    override fun FragmentPrivacyBinding.observers() {

    }

    override fun FragmentPrivacyBinding.listeners() {

    }

    override fun FragmentPrivacyBinding.init() {
        Glide.with(requireContext()).load("https://user-images.githubusercontent.com/24237865/144350753-5a52e6e5-3517-476c-8e5c-adad919abe8e.png").into(zoomableImageView)

        CoroutineScope(Dispatchers.Main).launch {
            jobCanceling()
        }
    }

    private suspend fun joinLaunch() {
        var followers = 0
        var instaFollowers = 0
        val job = CoroutineScope(Dispatchers.IO).launch {
            followers = getFaceBookFollowers()
        }
        val job1 = CoroutineScope(Dispatchers.IO).launch {
            instaFollowers = getInstaFollowers()
        }
        job.join()
        job1.join()
        Log.e("TAG", "init: Facebook = $followers Instagram  = $instaFollowers")
    }

    private suspend fun asyncWait() {
        val fbFollowers = CoroutineScope(Dispatchers.IO).async {
            getFaceBookFollowers()
        }
        val instaFollowers = CoroutineScope(Dispatchers.IO).async {
            getInstaFollowers()
        }
        Log.e(
            "TAG",
            "init: Facebook = ${fbFollowers.await()} Instagram  = ${instaFollowers.await()}"
        )
    }

    private fun syncTogether() {
        CoroutineScope(Dispatchers.IO).launch {
            val fb = async { getFaceBookFollowers() }
            val insta = async { getInstaFollowers() }
            Log.e("TAG", "init: Facebook = ${fb.await()} Instagram  = ${insta.await()}")
        }
    }

    private suspend fun jobCanceling() {
        val parentJob = CoroutineScope(Dispatchers.IO).launch {
            for (i in 1..1000){
                if (isActive){
                    longRunningTask()
                    Log.e("TAG", "jobCanceling: $i")
                }
            }
        }
        delay(1000)
        Log.e("TAG", "jobCanceling: parent job cancelled")
        parentJob.cancel()
        parentJob.join()
        Log.e("TAG", "jobCanceling: parent job COMPLETED")

    }

    private suspend fun getInstaFollowers(): Int {
        delay(1000)
        return 500
    }

    private suspend fun getFaceBookFollowers(): Int {
        delay(2000)
        return 299
    }

    private fun longRunningTask(){
        for(i in 1..1000000000){

        }
    }

}