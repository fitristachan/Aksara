package com.aksara.utils

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val FILENAME_FORMAT = "yyyyMMdd_HHmmss"
private val timeStamp: String = SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(Date())
private const val MAXIMAL_SIZE = 1000000

fun createCustomTempFile(context: Context): File {
    val filesDir = context.externalCacheDir
    return File.createTempFile(timeStamp, ".jpg", filesDir)
}

fun saveToGallery(context: Context, imageUri: Uri, callback: (File?) -> Unit) {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())

    val relativePath = Environment.DIRECTORY_PICTURES + File.separator + "Aksara"
    val directory = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Aksara")
    if (!directory.exists()) {
        directory.mkdirs()
    }

    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, "$timeStamp.jpg")
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        put(MediaStore.Images.Media.RELATIVE_PATH, relativePath)
    }

    val contentResolver = context.contentResolver
    val insertedUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

    var file: File? = null

    insertedUri?.let { newUri ->
        contentResolver.openInputStream(imageUri)?.use { inputStream ->
            // Convert the InputStream to a Bitmap
            val originalBitmap = BitmapFactory.decodeStream(inputStream)
            val scaledBitmap = scaleBitmap(originalBitmap, 0.3f) // Scale the bitmap by 4x
            val croppedBitmap = cropBitmapToCenter(scaledBitmap, 300, 300)

            contentResolver.openOutputStream(newUri)?.use { outputStream ->
                croppedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                // Retrieve the file corresponding to the URI after insertion
                val projection = arrayOf(MediaStore.Images.Media.DATA)
                val cursor = context.contentResolver.query(newUri, projection, null, null, null)
                cursor?.use {
                    val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                    if (it.moveToFirst()) {
                        val filePath = it.getString(columnIndex)
                        file = File(filePath)
                        callback.invoke(file)
                    }
                }
            }
        }
    }
}

fun scaleBitmap(bitmap: Bitmap, scaleFactor: Float): Bitmap {
    val width = (bitmap.width * scaleFactor).toInt()
    val height = (bitmap.height * scaleFactor).toInt()
    return Bitmap.createScaledBitmap(bitmap, width, height, true)
}

fun cropBitmapToCenter(bitmap: Bitmap, width: Int, height: Int): Bitmap {
    val x = (bitmap.width - width) / 2
    val y = (bitmap.height - height) / 2
    return Bitmap.createBitmap(bitmap, x, y, width, height)
}

fun uriToFile(imageUri: Uri, context: Context): File {
    val myFile = createCustomTempFile(context)
    val inputStream = context.contentResolver.openInputStream(imageUri) as InputStream
    val outputStream = FileOutputStream(myFile)
    val buffer = ByteArray(1024)
    var length: Int
    while (inputStream.read(buffer).also { length = it } > 0) outputStream.write(buffer, 0, length)
    outputStream.close()
    inputStream.close()
    return myFile
}

//fun File.reduceFileImage(): File {
//    val file = this
//    val bitmap = BitmapFactory.decodeFile(file.path)
//    var compressQuality = 100
//    var streamLength: Int
//    do {
//        val bmpStream = ByteArrayOutputStream()
//        bitmap?.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpStream)
//        val bmpPicByteArray = bmpStream.toByteArray()
//        streamLength = bmpPicByteArray.size
//        compressQuality -= 5
//    } while (streamLength > MAXIMAL_SIZE)
//    bitmap?.compress(Bitmap.CompressFormat.JPEG, compressQuality, FileOutputStream(file))
//    return file
//}

fun deleteTempFile(file: File): Boolean {
    return try {
        if (file.exists()) {
            file.delete()
        } else {
            false
        }
    } catch (e: Exception) {
        false
    }
}

fun saveBitmapToGallery(context: Context, bitmap: Bitmap, displayName: String) {

    val relativePath = Environment.DIRECTORY_PICTURES + File.separator + "Aksara"
    val directory = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Aksara")
    if (!directory.exists()) {
        directory.mkdirs()
    }

    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, "$displayName.jpg")
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        put(MediaStore.Images.Media.RELATIVE_PATH, relativePath)
    }

    val contentResolver = context.contentResolver
    val insertedUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

    insertedUri?.let { newUri ->
        var outputStream: OutputStream? = null
        try {
            outputStream = contentResolver.openOutputStream(newUri)
            if (outputStream != null) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            outputStream?.close()
        }
    }
}