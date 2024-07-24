package com.aksara.hilt.retrofit

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

interface QnaRepository {
    suspend fun getAnswer(context: String, question: String)
    val errorMessage: StateFlow<String>
    val isLoading: StateFlow<Boolean>
    val qnaResponseResult: StateFlow<QnaResponse?>
}

class QnaRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : QnaRepository {

    private val _errorMessage = MutableStateFlow<String>("")
    override val errorMessage = _errorMessage.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    override val isLoading = _isLoading.asStateFlow()

    private val _qnaResponseResult = MutableStateFlow<QnaResponse?>(null)
    override val qnaResponseResult = _qnaResponseResult.asStateFlow()

    override suspend fun getAnswer(context: String, question: String) {
        return withContext(Dispatchers.IO) {
            _isLoading.tryEmit(true)
            _errorMessage.tryEmit("")
            _qnaResponseResult.tryEmit(null)
            try {
                apiService.getAnswer(context = context, question = question).collect{
                    _isLoading.tryEmit(false)
                    _qnaResponseResult.tryEmit(it)
                }
            } catch (e: HttpException) {
                val errorResponse = parseError(e)
                _isLoading.tryEmit(false)
                _errorMessage.tryEmit(errorResponse)
            } catch (e: IOException) {
                _isLoading.tryEmit(false)
                _errorMessage.tryEmit(e.message ?: "Network error")
            } catch (e: Exception) {
                _isLoading.tryEmit(false)
                _errorMessage.tryEmit(e.message ?: "Unknown error")
            }
        }
    }
}

private fun parseError(exception: HttpException): String {
    return try {
        val errorBody = exception.response()?.errorBody()?.string()
        val apiErrorResponse = Gson().fromJson(errorBody, ApiErrorResponse::class.java)
        apiErrorResponse.error
    } catch (e: Exception) {
        "Unknown error"
    }
}