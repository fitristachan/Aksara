package com.dicoding.aksara.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dicoding.aksara.R

// Set of Material typography styles to start with

val InriasansRegular =  FontFamily(
    Font(R.font.inriasans_regular)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = InriasansRegular,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    titleLarge = TextStyle(
        fontFamily = InriasansRegular,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),

    titleMedium= TextStyle(
        fontFamily = InriasansRegular,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),

    labelLarge = TextStyle(
        fontFamily = InriasansRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),

    labelSmall = TextStyle(
        fontFamily = InriasansRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    )
)