package com.tomhaj.smartshoppinglistapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// Creates the heading-like text element
@Composable
fun Heading(s: String) {
    Text(
        text = s,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier.padding(bottom = 5.dp)
    )
}

// Creates text element with modified color, style, and size
@Composable
fun NormalText(s: String) {
    Text(text = s, color = Color.Magenta, style = MaterialTheme.typography.bodyLarge)
}