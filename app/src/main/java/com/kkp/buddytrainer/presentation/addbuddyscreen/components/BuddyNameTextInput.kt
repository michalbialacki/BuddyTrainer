package com.kkp.buddytrainer.presentation.addbuddyscreen.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.lang.Exception


@Composable
fun BuddyNameTextInput(
    onSave : (String) -> Unit,
) {
    var text by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var cachedText = text
    val context = LocalContext.current
    var localFocus = LocalFocusManager.current
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }


    TextField(
        value = text,
        onValueChange = { changedText ->
            cachedText = text
            text = changedText
        },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colors.primary),
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = KeyboardType.Ascii),
        keyboardActions = KeyboardActions(onDone = {
            try {
                onSave(text.text)
            }catch (e:Exception){
                text = cachedText
                Toast.makeText(context,"Try to put a real name next time!", Toast.LENGTH_SHORT).show()
            }
            focusManager.clearFocus()
        }),
        label = {
            Text(text = "Buddy's name",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(0.7f)
            )
        },
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
    )

}