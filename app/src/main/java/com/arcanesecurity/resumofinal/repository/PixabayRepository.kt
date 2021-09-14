package com.arcanesecurity.resumofinal.repository

import com.arcanesecurity.resumofinal.model.Image
import com.arcanesecurity.resumofinal.services.PixabayApi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject


class PixabayRepository @Inject constructor(private val service: PixabayApi) {

    lateinit var onError: (String?) -> Unit

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError(throwable.message)
    }

    suspend fun fetchImages(q: String, onError: (String?) -> Unit): List<Image>? {
        this.onError = onError
        return withContext(Dispatchers.Default + exceptionHandler) {
            val response = service.fetchImage(q = q)
            val processedResponse = processData(response)
            processedResponse?.hits
        }
    }

    private fun <T> processData(response: Response<T>): T? {
        return if (response.isSuccessful) response.body() else null
    }

}