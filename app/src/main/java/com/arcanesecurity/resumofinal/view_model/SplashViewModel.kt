package com.arcanesecurity.resumofinal.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arcanesecurity.resumofinal.repository.PixabayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repository: PixabayRepository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadData() {
        viewModelScope.launch {
            _isLoading.value = true
            val listOfImages = repository.fetchImages("", 1)
            listOfImages?.let {
                repository.insert(it)
            }
            delay(2000)
            _isLoading.value = false
        }
    }

}