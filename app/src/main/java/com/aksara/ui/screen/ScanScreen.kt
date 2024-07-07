package com.aksara.ui.screen

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aksara.R
import com.aksara.model.processImageAndPredict
import com.aksara.room.ScanEntity
import com.aksara.room.ScanViewModel
import com.aksara.ui.component.CameraPreview
import com.aksara.ui.component.Loading
import com.aksara.ui.component.dashedBorder
import com.aksara.ui.component.executor
import com.aksara.ui.component.getCameraProvider
import com.aksara.utils.Permission
import com.aksara.utils.createCustomTempFile
import com.aksara.utils.deleteTempFile
import com.aksara.utils.saveToGallery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

val imageFileInGallery = mutableStateOf("")

@Composable
fun ScanScreen(
    modifier: Modifier = Modifier,
    scanViewModel: ScanViewModel,
    navigateToDetail: (String) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var isCameraReady by remember { mutableStateOf(false) }
    val emptyImageUri = Uri.parse("/dev/null")
    var imageUri by remember { mutableStateOf(emptyImageUri) }

    var previewUseCase by remember { mutableStateOf<UseCase>(Preview.Builder().build()) }
    val imageCaptureUseCase by remember {
        mutableStateOf(
            ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY)
                .build()
        )
    }

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    var isLoading by remember { mutableStateOf(false) }

    var showDialog by remember { mutableStateOf(true) }

    val timeStamp: String = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())

    val launcherGallery = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    )
    { uri: Uri? ->
        if (uri != null) {
            isLoading = true
            processImageAndPredict(
                uri = uri,
                context = context,
                callback = { it: String? ->
                    if (it != null) {
                        isLoading = false
                        imageFileInGallery.value = uri.toString()
                        scanViewModel.addScan(
                            ScanEntity(
                                scanPicture = imageFileInGallery.value,
                                scanResult = it,
                                scanDate = timeStamp
                            )
                        ) { newScanId ->
                            navigateToDetail(newScanId.toString())
                        }
                    }
                }
            )
        } else {
            isLoading = false
            Log.d("Photo Picker", "No media selected")
        }
    }

    Permission(
        permission = Manifest.permission.CAMERA,
        text = stringResource(R.string.camera_permission),
        permissionNotAvailableContent = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = stringResource(R.string.camera_access_rejected),
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        context.startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                            data = Uri.fromParts("package", context.packageName, null)
                        })
                    }) {
                    Text(
                        text = stringResource(R.string.open_settings),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    ) {

        Box(modifier = modifier) {
            CameraPreview(
                modifier = Modifier.fillMaxSize(),
                onUseCase = {
                    previewUseCase = it
                },
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(bottom = 80.dp)
                    .align(Alignment.Center)
                    .fillMaxWidth()
            ) {
                val flashAnimation by rememberInfiniteTransition(label = "").animateFloat(
                    initialValue = 0.6f,
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        tween(5000, easing = FastOutSlowInEasing),
                        repeatMode = RepeatMode.Reverse
                    ), label = ""
                )

                val beatAnimation by rememberInfiniteTransition(label = "").animateFloat(
                    initialValue = 0.8f,
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        tween(5000),
                        repeatMode = RepeatMode.Reverse
                    ), label = ""
                )

                Text(
                    text = stringResource(id = R.string.lighting_instruction),
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White.copy(flashAnimation),
                    modifier = Modifier.scale(beatAnimation)
                )

                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                        .fillMaxWidth()
                )

                Text(
                    text = stringResource(id = R.string.scan_instruction),
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White.copy(flashAnimation),
                    modifier = Modifier.scale(beatAnimation)
                )

                Spacer(
                    modifier = Modifier
                        .height(42.dp)
                        .fillMaxWidth()
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .size(300.dp)
                        .clip(RectangleShape)
                        .dashedBorder(
                            color = Color.White,
                            shape = RectangleShape
                        )
                        .background(Color.Transparent)
                ) {
                }
            }

            Button(
                onClick = {
                    launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    isLoading = true
                },
                enabled = !isLoading,
                shape = CircleShape,
                contentPadding = PaddingValues(16.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                modifier = Modifier
                    .padding(vertical = 44.dp)
                    .padding(start = 40.dp, end = 8.dp)
                    .align(Alignment.BottomStart),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.gallery_icon),
                    contentDescription = stringResource(R.string.open_gallery),
                    tint = Color.White,
                    modifier = Modifier
                        .size(35.dp)
                )
            }

            OutlinedButton(
                modifier = Modifier
                    .padding(vertical = 32.dp)
                    .align(Alignment.BottomCenter),
                shape = CircleShape,
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.background),
                contentPadding = PaddingValues(if (isPressed) 8.dp else 12.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.background),
                onClick = { /* GNDN */ },
                enabled = false
            ) {
                Button(
                    modifier = Modifier
                        .size(80.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isPressed) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.primary
                    ),
                    interactionSource = interactionSource,
                    enabled = !isLoading,
                    onClick = {
                        isLoading = true
                        if (isCameraReady) {
                            val photoFile = createCustomTempFile(context)
                            val outputOptions =
                                ImageCapture.OutputFileOptions.Builder(photoFile).build()
                            imageCaptureUseCase.takePicture(
                                outputOptions,
                                context.executor,
                                object : ImageCapture.OnImageSavedCallback {
                                    override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                                        imageUri = output.savedUri
                                        processImageAndPredict(
                                            uri = imageUri,
                                            context = context,
                                            callback = { it: String? ->
                                                if (it != null) {
                                                    saveToGallery(context, imageUri) { file ->
                                                        imageFileInGallery.value =
                                                            file!!.toUri().toString()
                                                    }
                                                    isLoading = false
                                                    deleteTempFile(photoFile)

                                                    scanViewModel.addScan(
                                                        ScanEntity(
                                                            scanPicture = imageFileInGallery.value,
                                                            scanResult = it,
                                                            scanDate = timeStamp
                                                        )
                                                    ) { newScanId ->
                                                        navigateToDetail(newScanId.toString())

                                                    }
                                                }
                                            }
                                        )
                                    }


                                    override fun onError(ex: ImageCaptureException) {
                                        isLoading = false
                                        deleteTempFile(photoFile)
                                        Toast.makeText(
                                            context,
                                            "Failed to capture image.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        Log.e(ContentValues.TAG, "onError: ${ex.message}")
                                    }
                                }
                            )
                        } else {
                            isLoading = false
                            Toast.makeText(
                                context,
                                "Camera is not ready. Please wait for initialization.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                ) {
                }
            }

            LaunchedEffect(previewUseCase) {
                val cameraProvider = context.getCameraProvider()
                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        CameraSelector.DEFAULT_BACK_CAMERA,
                        previewUseCase,
                        imageCaptureUseCase
                    )
                    isCameraReady = true

                } catch (ex: Exception) {
                    Log.e("CameraCapture", "Failed to bind camera use cases", ex)
                }
            }
        }
        if (isLoading) {
            Box(modifier = Modifier.background(MaterialTheme.colorScheme.onBackground)) {
                Loading()
            }
        }
    }
}



