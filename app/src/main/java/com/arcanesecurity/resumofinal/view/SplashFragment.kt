package com.arcanesecurity.resumofinal.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.arcanesecurity.resumofinal.R
import com.arcanesecurity.resumofinal.databinding.SplashFragmentBinding
import com.arcanesecurity.resumofinal.view_model.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.splash_fragment) {

    private lateinit var viewModel: SplashViewModel
    private lateinit var binding: SplashFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SplashFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
    }

}