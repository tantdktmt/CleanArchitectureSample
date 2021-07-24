package com.majesticreader.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.majesticreader.presentation.util.LogUtils

abstract class CoreActivity<VM : ViewModel, T : ViewDataBinding> : AppCompatActivity() {

    companion object {
        const val ID_NULL = -1
    }

    var activityResultListener: ((ActivityResult) -> Unit)? = null
    lateinit var startActivityForResultNewAPI: ActivityResultLauncher<Intent>
    lateinit var viewModel: VM
    var binding: T? = null

    @LayoutRes
    abstract fun getLayoutId(): Int
    abstract fun getInstanceViewModel(): VM
    abstract fun observeLiveData()

    @IdRes
    open fun getContainerId(): Int {
        return ID_NULL
    }

    @IdRes
    open fun getBindingViewModelId(): Int {
        return ID_NULL
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getInstanceViewModel()
        try {
            binding = DataBindingUtil.setContentView(this, getLayoutId())
            if (getBindingViewModelId() != ID_NULL) {
                binding?.setVariable(getBindingViewModelId(), viewModel)
            }
            binding?.lifecycleOwner = this
            binding?.executePendingBindings()
        } catch (e: Exception) {
            LogUtils.e("${e.message}")
            if (getLayoutId() != ID_NULL) {
                setContentView(getLayoutId())
            }
        }
        initData()
        initViews()
        registerForActivityResult()
        observeLiveData()
    }

    protected open fun initData() = Unit

    protected open fun initViews() = Unit

    private fun registerForActivityResult() {
        startActivityForResultNewAPI =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                activityResultListener?.invoke(result)
            }
    }
}
