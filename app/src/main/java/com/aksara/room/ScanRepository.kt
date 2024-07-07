package com.aksara.room

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface ScanRepository {
    suspend fun addScan(scanEntity: ScanEntity): Long
    suspend fun getScanByScanId(scanId: Int): Flow<ScanEntity?>
    suspend fun getAllScans(): Flow<List<ScanEntity>>

//    suspend fun getNewestScanId(): Int?
}


class ScanRepositoryImpl @Inject constructor(
    private val scanDao: ScanDao,
) : ScanRepository {
    override suspend fun addScan(scanEntity: ScanEntity): Long {
        return withContext(IO) {
            scanDao.addScan(scanEntity)
        }
    }

    override suspend fun getScanByScanId(scanId: Int): Flow<ScanEntity?> {
        return withContext(IO) {
            scanDao.getScanByScanId(scanId)
        }
    }
    override suspend fun getAllScans(): Flow<List<ScanEntity>> {
        return withContext(IO) {
            scanDao.getAllScans()
        }
    }

//    override suspend fun getNewestScanId(): Int? {
//        return withContext(IO) {
//            scanDao.getNewestScanId()
//        }
//    }
}