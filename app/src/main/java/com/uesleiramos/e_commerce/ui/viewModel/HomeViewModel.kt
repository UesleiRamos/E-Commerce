package com.uesleiramos.e_commerce.ui.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uesleiramos.e_commerce.BuildConfig
import com.uesleiramos.e_commerce.R

class HomeViewModel() : ViewModel() {

    val title: MutableLiveData<String> = MutableLiveData()
    val vesion: MutableLiveData<String> = MutableLiveData()
    val versionCode: MutableLiveData<String> = MutableLiveData()

    fun init(context: Context?) {
        title.value = context?.getString(R.string.home_title)
        context?.let { contextLet ->
            vesion.value = String.format(contextLet.getString(R.string.version), BuildConfig.VERSION_NAME)
            versionCode.value = String.format(contextLet.getString(R.string.version_code), BuildConfig.VERSION_CODE)
        }
    }
}