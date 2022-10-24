package com.kkp.buddytrainer.presentation.addbuddyscreen.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun BuddyParameterTextInput(
    parName : String ="",
    onSave : (Float) -> Unit,
) {
    var text by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var cachedText = text
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    var isHintDisplayed by remember {
        mutableStateOf(parName !="")
    }
    val focusRequester = remember { FocusRequester() }
    var textFieldFocus by remember { mutableStateOf(false)}


    TextField(
        modifier = Modifier
            .onFocusChanged {
                if(!it.isFocused && !text.text.isNullOrEmpty()){
                    try {
                        if("," in text.text){
                            val temp = text.text.replace(",",".")
                            text = TextFieldValue(temp)
                        }
                        val newBudPR = text.text.toFloat()
                        if (newBudPR in (0f..600f)){
                            text = TextFieldValue(newBudPR.toString())
                            onSave(newBudPR)
                        }else{
                            text = cachedText
                            Toast.makeText(context,"Try to put a real PR next time!", Toast.LENGTH_SHORT).show()
                            text = TextFieldValue("")
                        }
                    }catch (e:NumberFormatException){
                        text = cachedText
                        Toast.makeText(context,"Try to put a real PR next time!", Toast.LENGTH_SHORT).show()
                        text = TextFieldValue("")
                    }
                }
            },
        value = text,
        onValueChange = { changedText ->
            cachedText = text
            text = changedText
        },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = KeyboardType.Number),
        keyboardActions = KeyboardActions( onDone = {
            try {
                if("," in text.text){
                    val temp = text.text.replace(",",".")
                    text = TextFieldValue(temp)
                }
                val newBudPR = text.text.toFloat()
                if (newBudPR in (0f..600f)){
                    text = TextFieldValue(newBudPR.toString())
                    onSave(newBudPR)
                }else{
                    text = cachedText
                    Toast.makeText(context,"Try to put a real PR next time!", Toast.LENGTH_SHORT).show()
                    text = TextFieldValue("")
                }
            }catch (e:NumberFormatException){
                text = cachedText
                Toast.makeText(context,"Try to put a real PR next time!", Toast.LENGTH_SHORT).show()
                text = TextFieldValue("")
            }
            focusManager.clearFocus()
        }),
        label = {
            Text(text = parName, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        },
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
    )

}