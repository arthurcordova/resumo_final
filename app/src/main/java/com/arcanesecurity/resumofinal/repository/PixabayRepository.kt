package com.arcanesecurity.resumofinal.repository

import com.arcanesecurity.resumofinal.database.dao.PixabayDao
import com.arcanesecurity.resumofinal.model.Image
import com.arcanesecurity.resumofinal.services.PixabayApi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject


class PixabayRepository @Inject constructor(
    private val service: PixabayApi,
    private val pixabayDao: PixabayDao
) {

    suspend fun fetchImages(q: String, page: Int): List<Image>? {
        return withContext(Dispatchers.Default) {
            val response = service.fetchImage(q = q, page = page)
            val processedResponse = processData(response)
            processedResponse?.hits
        }
    }

    private fun <T> processData(response: Response<T>): T? {
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun insert(listOf: List<Image>): Boolean {
        return withContext(Dispatchers.Default) {
            pixabayDao.insert(listOf)
            true
        }
    }

    suspend fun fetchFromDb(): List<Image> {
        return withContext(Dispatchers.Default) {
            pixabayDao.fetch()
        }
    }


}