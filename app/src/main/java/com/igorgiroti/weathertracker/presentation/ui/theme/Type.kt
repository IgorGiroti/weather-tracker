package com.igorgiroti.weathertracker.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


val Typography = Typography(
    bodySmall = TextStyle(
        fontSize = 12.sp,
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.W400,
        lineHeight = 22.5.sp
    ),
    bodyMedium = TextStyle(
        fontSize = 15.sp,
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.W400,
        lineHeight = 22.5.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 30.sp,
        lineHeight = 45.sp,
        letterSpacing = 0.5.sp
    )
)