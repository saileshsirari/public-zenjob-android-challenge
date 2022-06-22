package com.zenjob.android.browsr

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    val errorLiveData = MutableLiveData<String>()

}