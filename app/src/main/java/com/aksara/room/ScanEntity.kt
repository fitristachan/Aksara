package com.aksara.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "scans")
data class ScanEntity(
    @PrimaryKey(autoGenerate = true)
    var scanId: Int = 0,
    var scanPicture: String,
    var scanResult: String,
    var scanDate: String
)
