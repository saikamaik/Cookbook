package com.example.cookbook.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.cookbook.R

val fontFamilyPoppins = FontFamily(
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_regular, FontWeight.Normal)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = fontFamilyPoppins,
        fontSize = 24.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = fontFamilyPoppins,
        fontSize = 56.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = fontFamilyPoppins,
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontFamily = fontFamilyPoppins,
        fontSize = 12.sp
    ),
    labelMedium = TextStyle(
        fontFamily = fontFamilyPoppins,
        fontSize = 20.sp
    ),
    labelSmall = TextStyle(
        fontFamily = fontFamilyPoppins,
        fontSize = 14.sp
    )
)