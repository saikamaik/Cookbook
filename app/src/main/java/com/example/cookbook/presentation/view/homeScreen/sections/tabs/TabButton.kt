package com.example.cookbook.presentation.view.homeScreen.sections.tabs

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cookbook.ui.theme.Typography

@Composable
fun TabButton(
    text: String,
    color: Color,
    textColor: Color,
    fontWeight: FontWeight,
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        )

    ) {
        Text(
            text = text,
            color = textColor,
            fontWeight = fontWeight,
            style = Typography.bodySmall
        )
    }
}