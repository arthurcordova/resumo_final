package com.arcanesecurity.resumofinal.view_model

import androidx.lifecycle.ViewModel
import com.arcanesecurity.resumofinal.repository.PixabayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repository: PixabayRepository): ViewModel() {



}