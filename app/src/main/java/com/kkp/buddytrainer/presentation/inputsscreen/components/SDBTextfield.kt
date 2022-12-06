package com.kkp.buddytrainer.presentation.inputsscreen.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kkp.buddytrainer.presentation.inputsscreen.InputScreenViewModel

@Composable
fun SDBTextfield(
    exerciseRecordValue : Float,
    exerciseName : String,
    updateUser : (Float) -> Unit
) {
    var exerciseRecordValueLocal = exerciseRecordValue
    var text by remember {
        mutableStateOf(TextFieldValue(exerciseRecordValueLocal.toString()))
    }
    var cachedText = text
    val context = LocalContext.current
    var localFocus = LocalFocusManager.current
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .padding(4.dp),
        contentAlignment = Alignment.Center){
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            TextField(
                value = text ,
                onValueChange = {changedText ->
                    cachedText = text
                    text = changedText
                },
                maxLines = 1,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colors.onPrimary)
                    .onFocusChanged {
                    },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions(onDone = {
                    try {
                        if("," in text.text){
                            val temp = text.text.replace(",",".")
                            text = TextFieldValue(temp)
                        }
                        val newBudPR = text.text.toFloat()
                        if (newBudPR in (0f..600f)){
                            text = TextFieldValue(newBudPR.toString())
                            updateUser(newBudPR)
                        }else{
                            text = cachedText
                            Toast.makeText(context,"Try to put a real PR next time!", Toast.LENGTH_SHORT).show()
                        }
                    }catch (e:NumberFormatException){
                        text = cachedText
                        Toast.makeText(context,"Try to put a real PR next time!", Toast.LENGTH_SHORT).show()
                    }
                    focusManager.clearFocus()
                }),
                label = {
                    Text(text = exerciseName, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                },
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp
                ),
            )
        }
    }
}