package com.dreamrecall.app.core.design.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.dreamrecall.app.core.design.DreamPurple
import com.dreamrecall.app.core.design.GlassWhite15
import com.dreamrecall.app.core.design.GlassWhite8
import com.dreamrecall.app.core.design.LucidCyan
import com.dreamrecall.app.core.design.NightmarePink
import com.dreamrecall.app.core.design.PropheticGold
import com.dreamrecall.app.core.design.RegularBlue
import com.dreamrecall.app.core.design.SpaceNavyBg
import com.dreamrecall.app.core.design.SpaceNavyBorder
import com.dreamrecall.app.core.design.TextPrimary
import com.dreamrecall.app.core.design.TextSecondary

enum class DreamChipType {
    DEFAULT,
    LUCID,
    NIGHTMARE,
    PROPHETIC,
    REGULAR
}

@Composable
fun DreamChip(
    text: String,
    onClick: (() -> Unit)? = null,
    isSelected: Boolean = false,
    type: DreamChipType = DreamChipType.DEFAULT,
    icon: ImageVector? = null,
    modifier: Modifier = Modifier
) {
    // Dynamic styling colors based on chip classification
    val activeBorderColor = when (type) {
        DreamChipType.DEFAULT -> DreamPurple
        DreamChipType.LUCID -> LucidCyan
        DreamChipType.NIGHTMARE -> NightmarePink
        DreamChipType.PROPHETIC -> PropheticGold
        DreamChipType.REGULAR -> RegularBlue
    }

    val activeBgColor = activeBorderColor.copy(alpha = 0.12f)
    val inactiveBgColor = SpaceNavyBg.copy(alpha = 0.5f)
    val inactiveBorderColor = SpaceNavyBorder

    val animatedBgColor by animateColorAsState(
        targetValue = if (isSelected) activeBgColor else inactiveBgColor,
        label = "bgColor"
    )

    val animatedBorderColor by animateColorAsState(
        targetValue = if (isSelected) activeBorderColor else inactiveBorderColor,
        label = "borderColor"
    )

    val animatedTextColor by animateColorAsState(
        targetValue = if (isSelected) TextPrimary else TextSecondary,
        label = "textColor"
    )

    val chipShape = MaterialTheme.shapes.small

    val clickableModifier = if (onClick != null) {
        Modifier.clickable(onClick = onClick)
    } else {
        Modifier
    }

    Box(
        modifier = modifier
            .clip(chipShape)
            .background(animatedBgColor)
            .border(
                width = 1.dp,
                color = animatedBorderColor,
                shape = chipShape
            )
            .then(clickableModifier)
            .padding(horizontal = 14.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = if (isSelected) activeBorderColor else TextSecondary,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
            }
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                color = animatedTextColor
            )
        }
    }
}
