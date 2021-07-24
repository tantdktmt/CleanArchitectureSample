package com.majesticreader.presentation.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

open class CoreViewModel : ViewModel() {

    protected val loading: MutableStateFlow<Boolean> by lazy { MutableStateFlow(false) }
}
