package com.dreamrecall.app.core.design.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dreamrecall.app.core.design.GlassWhite15
import com.dreamrecall.app.core.design.GlassWhite8
import com.dreamrecall.app.core.design.SpaceNavyBorder
import com.dreamrecall.app.core.design.SpaceNavyCard

@Composable
fun DreamCard(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.large,
    borderColor: Color = SpaceNavyBorder,
    borderWidth: Dp = 1.dp,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val clickableModifier = if (onClick != null) {
        Modifier.clickable(onClick = onClick)
    } else {
        Modifier
    }

    Card(
        modifier = modifier
            .clip(shape)
            .border(
                width = borderWidth,
                color = borderColor,
                shape = shape
            )
            .then(clickableModifier),
        colors = CardDefaults.cardColors(
            containerColor = SpaceNavyCard
        ),
        shape = shape
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            content = content
        ) { }
    }
}

/**
 * Translucent Glassmorphic Card that gives an ultra-premium glass overlay look.
 * Recommended for dashboards, AI analysis, and detail views.
 */
@Composable
fun DreamGlassCard(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.large,
    glowColor: Color? = null,
    glowWidth: Dp = 1.dp,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val clickableModifier = if (onClick != null) {
        Modifier.clickable(onClick = onClick)
    } else {
        Modifier
    }

    // Dynamic gradient border: blends glowColor (if present) with standard glass highlight
    val borderBrush = if (glowColor != null) {
        Brush.linearGradient(
            colors = listOf(
                glowColor.copy(alpha = 0.5f),
                glowColor.copy(alpha = 0.1f),
                GlassWhite8
            )
        )
    } else {
        Brush.linearGradient(
            colors = listOf(
                GlassWhite15,
                Color.Transparent,
                GlassWhite8
            )
        )
    }

    Box(
        modifier = modifier
            .graphicsLayer {
                // Prepares graphics layer for optimal rendering of translucent surfaces
                clip = true
                this.shape = shape
            }
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        SpaceNavyCard.copy(alpha = 0.65f),
                        SpaceNavyCard.copy(alpha = 0.45f)
                    )
                )
            )
            .border(
                border = BorderStroke(glowWidth, borderBrush),
                shape = shape
            )
            .then(clickableModifier)
            .padding(20.dp)
    ) {
        Column {
            content()
        }
    }
}
