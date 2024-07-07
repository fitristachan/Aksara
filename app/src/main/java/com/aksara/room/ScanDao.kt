package com.aksara.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScanDao {

    // Insert new data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addScan(scanEntity: ScanEntity): Long

    // Select where scanId = $scanId
    @Query("SELECT * FROM scans WHERE scanId = :scanId")
    fun getScanByScanId(scanId: Int): Flow<ScanEntity?>

    // Select all data
    @Query("SELECT * FROM scans")
    fun getAllScans(): Flow<List<ScanEntity>>

     //Select ID of the data with the newest ID (the most recently inserted data)
    @Query("SELECT scanId FROM scans ORDER BY scanId DESC LIMIT 1")
    fun getNewestScanId(): Int?
}
