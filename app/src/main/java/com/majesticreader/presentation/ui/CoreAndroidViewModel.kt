package com.ascend.money.android.core.utils

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow

open class CoreAndroidViewModel(application: Application) : AndroidViewModel(application) {

    protected val loading: MutableStateFlow<Boolean> by lazy { MutableStateFlow(false) }
}
