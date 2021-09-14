package com.arcanesecurity.resumofinal.repository

import com.arcanesecurity.resumofinal.model.Image
import com.arcanesecurity.resumofinal.services.PixabayApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject


class PixabayRepository @Inject constructor(private val service: PixabayApi) {

    suspend fun fetchImages(q: String): List<Image>? {
        return withContext(Dispatchers.Default) {
            val response = service.fetchImage(q = q)
            val processedResponse = processData(response)
            processedResponse?.hits
        }
    }

    private fun <T> processData(response: Response<T>): T? {
        return if (response.isSuccessful) response.body() else null
    }

}