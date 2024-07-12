package com.aksara.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.widget.Toast
import com.aksara.ml.ModelCheckpointBlock4cProjectConvC
import com.aksara.utils.cropBitmapToCenter
import com.aksara.utils.saveBitmapToGallery
import com.aksara.utils.scaleBitmap
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder


fun processImageAndPredict(context: Context, uri: Uri, callback: (String) -> Unit) {
    val imageBitmap = uriToBitmap(context, uri)
    val scaledBitmap = scaleBitmap(imageBitmap!!, 0.3f) // Scale the bitmap by 4x
    val croppedBitmap = cropBitmapToCenter(scaledBitmap, 300, 300)

    val model = ModelCheckpointBlock4cProjectConvC.newInstance(context)
    val aksara = listOf(
        "ba",
        "ca",
        "da",
        "dha",
        "ga",
        "ha",
        "ja",
        "ka",
        "la",
        "ma",
        "na",
        "nga",
        "nya",
        "pa",
        "ra",
        "sa",
        "ta",
        "tha",
        "wa",
        "ya"
    )

    if (croppedBitmap != null) {
        val downsampledBitmap = Bitmap.createScaledBitmap(croppedBitmap, 300, 300, true)
        val grayBitmap = convertToGrayscale(downsampledBitmap)
//        val binaryBitmap = applyAdaptiveThreshold(grayBitmap, 300, 1)//ganti ke openCV
        val binaryBitmap = applyBinaryThreshold(grayBitmap, 120)

        val displayName = "processing_image"
        saveBitmapToGallery(context, binaryBitmap, displayName)

        val width = binaryBitmap.width
        val height = binaryBitmap.height

        // Creates inputs for reference.
        val inputFeature0 =
            TensorBuffer.createFixedSize(intArrayOf(1, 300, 300, 3), DataType.FLOAT32)

        val byteBuffer: ByteBuffer = ByteBuffer.allocateDirect(4 * width * height * 3)
        byteBuffer.order(ByteOrder.nativeOrder())

        val intValues = IntArray(width * height)
        binaryBitmap.getPixels(intValues, 0, width, 0, 0, width, height)
        var pixel = 0

        // Iterate over each pixel and extract R, G, and B values. Add those values individually to the byte buffer.
        for (i in 0 until width) {
            for (j in 0 until height) {
                val value = intValues[pixel++] // RGB
                byteBuffer.putFloat((value shr 16 and 0xFF) * (1f / 255f))
                byteBuffer.putFloat((value shr 8 and 0xFF) * (1f / 255f))
                byteBuffer.putFloat((value and 0xFF) * (1f / 255f))
            }
        }

        inputFeature0.loadBuffer(byteBuffer)

        // Runs model inference and gets result.
        val outputs: ModelCheckpointBlock4cProjectConvC.Outputs = model.process(inputFeature0)
        val outputFeature0: TensorBuffer = outputs.outputFeature0AsTensorBuffer
        val confidences = outputFeature0.floatArray

        // find the index of the class with the biggest confidence.
        var maxPos = 0
        var maxConfidence = 0f
        for (i in confidences.indices) {
            if (confidences[i] > maxConfidence) {
                maxConfidence = confidences[i]
                maxPos = i
            }
        }

        // Get the predicted aksara
        val aksaraPrediction = aksara[maxPos]

        // Invoke the callback with the result
        callback.invoke(aksaraPrediction)

        downsampledBitmap.recycle()
    } else {
        Toast.makeText(context, "Failed to load image bitmap.", Toast.LENGTH_SHORT).show()
    }
    model.close()
}

private fun convertToGrayscale(bitmap: Bitmap): Bitmap {
    val width = bitmap.width
    val height = bitmap.height
    val grayBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    for (i in 0 until width) {
        for (j in 0 until height) {
            val pixel = bitmap.getPixel(i, j)
            val r = (pixel shr 16 and 0xFF)
            val g = (pixel shr 8 and 0xFF)
            val b = (pixel and 0xFF)
            val gray = (0.299 * r + 0.587 * g + 0.114 * b).toInt()
            grayBitmap.setPixel(i, j, (0xFF shl 24) or (gray shl 16) or (gray shl 8) or gray)
        }
    }
    return grayBitmap
}

//private fun applyAdaptiveThreshold(bitmap: Bitmap, maxBlockSize: Int, C: Int): Bitmap {
//    val width = bitmap.width
//    val height = bitmap.height
//    val binaryBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
//
//    val pixels = IntArray(width * height)
//    bitmap.getPixels(pixels, 0, width, 0, 0, width, height)
//
//    val blockSize = min(maxBlockSize, min(width, height) / 6) // Example dynamic block size calculation
//
//    for (x in 0 until width) {
//        for (y in 0 until height) {
//            val localMean = calculateLocalMean(pixels, width, height, x, y, blockSize)
//            val grayValue = pixels[y * width + x] and 0xFF
//            val thresholdValue = localMean - C
//            val newPixel = if (grayValue > thresholdValue) 0xFFFFFFFF.toInt() else 0xFF000000.toInt()
//            binaryBitmap.setPixel(x, y, newPixel)
//        }
//    }
//
//    return binaryBitmap
//}
//
//// Calculate the local mean for a given block size
//private fun calculateLocalMean(pixels: IntArray, width: Int, height: Int, x: Int, y: Int, blockSize: Int): Int {
//    var sum = 0
//    var count = 0
//    val halfBlockSize = blockSize / 2
//    val startX = max(0, x - halfBlockSize)
//    val endX = min(width - 1, x + halfBlockSize)
//    val startY = max(0, y - halfBlockSize)
//    val endY = min(height - 1, y + halfBlockSize)
//
//    for (i in startX..endX) {
//        for (j in startY..endY) {
//            val pixel = pixels[j * width + i] and 0xFF
//            sum += pixel
//            count++
//        }
//    }
//
//    return if (count > 0) sum / count else 0
//}

private fun applyBinaryThreshold(bitmap: Bitmap, threshold: Int): Bitmap {
    val width = bitmap.width
    val height = bitmap.height
    val binaryBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    for (i in 0 until width) {
        for (j in 0 until height) {
            val pixel = bitmap.getPixel(i, j)
            val gray = Color.red(pixel)
            val binary = if (gray > threshold) 255 else 0
            binaryBitmap.setPixel(i, j, Color.rgb(binary, binary, binary))
        }
    }
    return binaryBitmap
}

private fun uriToBitmap(context: Context, uri: Uri): Bitmap? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri)
        BitmapFactory.decodeStream(inputStream)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}





