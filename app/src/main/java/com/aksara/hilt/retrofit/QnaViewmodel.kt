package com.aksara.hilt.retrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QnaViewModel @Inject constructor(
    private val qnaRepository: QnaRepository,
) : ViewModel() {

    val errorMessage: StateFlow<String> = qnaRepository.errorMessage
    val isLoading: StateFlow<Boolean> = qnaRepository.isLoading
    val qnaResponseResult: StateFlow<QnaResponse?> = qnaRepository.qnaResponseResult

    fun getAnswer(context: String, question: String) {
        viewModelScope.launch(Dispatchers.IO) {
            qnaRepository.getAnswer(context, question)
        }
    }
}