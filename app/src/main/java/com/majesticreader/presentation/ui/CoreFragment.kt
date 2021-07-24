package com.majesticreader.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.majesticreader.presentation.util.LogUtils
import com.majesticreader.presentation.util.inflateExt

abstract class CoreFragment<VM : ViewModel, T : ViewDataBinding> : Fragment() {

    companion object {
        const val ID_NULL = -1
    }

    var activityResultListener: ((ActivityResult) -> Unit)? = null
    lateinit var startActivityForResultNewAPI: ActivityResultLauncher<Intent>
    lateinit var viewModel: VM
    var binding: T? = null
    var viewRoot: View? = null

    @LayoutRes
    abstract fun getLayoutId(): Int
    abstract fun getInstanceViewModel(): VM
    abstract fun observeLiveData()

    @IdRes
    open fun getBindingViewModelId(): Int {
        return ID_NULL
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getInstanceViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (viewRoot == null) {
            viewRoot = super.onCreateView(inflater, container, savedInstanceState)
        }
        try {
            binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
            if (getBindingViewModelId() != ID_NULL) {
                binding?.setVariable(getBindingViewModelId(), viewModel)
            }
            binding?.lifecycleOwner = this
            binding?.executePendingBindings()

            return binding?.root
        } catch (e: Exception) {
            LogUtils.e("${e.message}")
            if (getLayoutId() != ID_NULL) {
                return container?.inflateExt(getLayoutId())
            }
        }
        return viewRoot
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initViews()
        registerForActivityResult()
        observeLiveData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activityResultListener = null
        binding = null
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
