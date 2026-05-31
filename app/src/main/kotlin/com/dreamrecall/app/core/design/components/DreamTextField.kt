package com.dreamrecall.app.core.design.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.dreamrecall.app.core.design.DreamPurple
import com.dreamrecall.app.core.design.SpaceNavyBg
import com.dreamrecall.app.core.design.SpaceNavyBorder
import com.dreamrecall.app.core.design.SpaceNavyBorderGlow
import com.dreamrecall.app.core.design.SpaceNavyCard
import com.dreamrecall.app.core.design.TextMuted
import com.dreamrecall.app.core.design.TextPrimary
import com.dreamrecall.app.core.design.TextSecondary

@Composable
fun DreamTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall,
            color = TextSecondary,
            modifier = Modifier.padding(bottom = 6.dp)
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyLarge,
                    color = TextMuted
                )
            },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = TextPrimary),
            shape = MaterialTheme.shapes.medium,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary,
                focusedContainerColor = SpaceNavyCard,
                unfocusedContainerColor = SpaceNavyBg.copy(alpha = 0.5f),
                errorContainerColor = SpaceNavyCard,
                focusedBorderColor = DreamPurple,
                unfocusedBorderColor = SpaceNavyBorder,
                errorBorderColor = MaterialTheme.colorScheme.error,
                cursorColor = DreamPurple,
                focusedLabelColor = DreamPurple,
                unfocusedLabelColor = TextSecondary
            )
        )
    }
}

/**
 * Specialized Large Text Area for typing detailed dream narrations.
 */
@Composable
fun DreamAreaTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    minLines: Int = 6,
    isError: Boolean = false
) {
    DreamTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        placeholder = placeholder,
        modifier = modifier,
        singleLine = false,
        minLines = minLines,
        isError = isError
    )
}
