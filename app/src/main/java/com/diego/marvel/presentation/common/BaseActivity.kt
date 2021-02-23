package com.diego.marvel.presentation.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.diego.marvel.R
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity<V : ViewBinding> : AppCompatActivity() {
    lateinit var binding: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
    }

    protected abstract fun getViewBinding(): V

    open fun showConnectionError() {
        Snackbar.make(binding.root, R.string.connectionError, Snackbar.LENGTH_LONG).show()
    }

    open fun showDefaultError() {
        Snackbar.make(binding.root, R.string.defaultError, Snackbar.LENGTH_LONG).show()
    }

    open fun showError(errorDescription: String) {
        Snackbar.make(binding.root, errorDescription, Snackbar.LENGTH_LONG).show()
    }
}