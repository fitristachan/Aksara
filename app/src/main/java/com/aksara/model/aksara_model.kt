package com.aksara.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.widget.Toast
import com.aksara.ml.ModelCheckpointBlock4cProjectConvB
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder


fun processImageAndPredict(context: Context, uri: Uri, callback: (String) -> Unit) {
    val imageBitmap = uriToBitmap(context, uri)
    val model = ModelCheckpointBlock4cProjectConvB.newInstance(context)
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

    if (imageBitmap != null) {
        val downsampledBitmap = Bitmap.createScaledBitmap(imageBitmap, 300, 300, true)
        val grayBitmap = convertToGrayscale(downsampledBitmap)
        val binaryBitmap = applyBinaryThreshold(grayBitmap, 170)

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
        val outputs: ModelCheckpointBlock4cProjectConvB.Outputs = model.process(inputFeature0)
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

fun convertToGrayscale(bitmap: Bitmap): Bitmap {
    val width = bitmap.width
    val height = bitmap.height
    val grayBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    for (i in 0 until width) {
        for (j in 0 until height) {
            val pixel = bitmap.getPixel(i, j)
            val gray = (Color.red(pixel) + Color.green(pixel) + Color.blue(pixel)) / 3
            grayBitmap.setPixel(i, j, Color.rgb(gray, gray, gray))
        }
    }
    return grayBitmap
}

fun applyBinaryThreshold(bitmap: Bitmap, threshold: Int): Bitmap {
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

fun uriToBitmap(context: Context, uri: Uri): Bitmap? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri)
        BitmapFactory.decodeStream(inputStream)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
