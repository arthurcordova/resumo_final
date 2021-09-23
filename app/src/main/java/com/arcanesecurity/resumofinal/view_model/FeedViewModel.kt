package com.arcanesecurity.resumofinal.view_model

import android.app.job.JobInfo
import android.content.ComponentName
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arcanesecurity.resumofinal.model.Image
import com.arcanesecurity.resumofinal.repository.PixabayRepository
import com.arcanesecurity.resumofinal.services.AppJobService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(private val repository: PixabayRepository) : ViewModel() {

    private val _images = MutableLiveData<List<Image>>()
    val images: LiveData<List<Image>> = _images

    private val _page = MutableLiveData<Int>()
    val page: LiveData<Int> = _page

    private var _query : String? = null
    private var onlyFirstTime = true

    fun fetchImages(page: Int = 1) {
        viewModelScope.launch {

            val listFromDb = repository.fetchFromDb()
            if (listFromDb.isNotEmpty() && onlyFirstTime){
                _images.value = listFromDb
                onlyFirstTime = false
            } else {
                val returnedImages = repository.fetchImages(q = _query ?: "", page = page)
                returnedImages?.let {
                    _images.value = it
                }
            }
        }
    }

    fun nextPage() {
        _page.value = (_page.value ?: 0) + 1
    }

    fun searchFor(q : String) {
        _query = q
        _page.value = 1
    }




}