package com.aksara.hilt.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ScanViewModel @Inject constructor(
    private val scanRepository: ScanRepository,
) : ViewModel() {

    private var newScanId: Long = 0
    fun addScan(scanEntity: ScanEntity, onSuccess: (Long) -> Unit) {
        viewModelScope.launch(IO) {
            newScanId = scanRepository.addScan(scanEntity)
            withContext(Dispatchers.Main) {
                onSuccess(newScanId)
            }
        }
    }

    private val _scanDetail = MutableStateFlow<ScanEntity?>(null)
    val scanDetail = _scanDetail.asStateFlow()
    fun getScanByScanId(scanId: Int) {
        viewModelScope.launch(IO) {
            scanRepository.getScanByScanId(scanId).collect {
                _scanDetail.tryEmit(it)
            }
        }
    }

    private val _scanDetailsList = MutableStateFlow(emptyList<ScanEntity>())
    val scanDetailsList = _scanDetailsList.asStateFlow()
    fun getAllScans() {
        viewModelScope.launch(IO) {
            scanRepository.getAllScans().collectLatest {
                _scanDetailsList.tryEmit(it)
            }
        }
    }

//    fun getNewestScanId() {
//        viewModelScope.launch(IO) {
//            scanRepository.getNewestScanId()
//        }
//    }
}