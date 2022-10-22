package com.kkp.buddytrainer.presentation.inputsscreen.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
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
                modifier = Modifier
                    .width(80.dp)
                    .height(50.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .onFocusChanged {

                    },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions(onDone = {
                    try {
                        if("," in text.text){
                            val temp = text.text.replace(",",".")
                            text = TextFieldValue(temp)
                        }
                        val newPersonalRecord = text.text.toFloat()
                        Toast.makeText(
                            context,
                            "Congrats! New PR is $newPersonalRecord",
                            Toast.LENGTH_SHORT).show()
                        updateUser(newPersonalRecord)
                    }catch (e:NumberFormatException){
                        text = cachedText
                        Toast.makeText(context,"Treat this rivalry seriously!",Toast.LENGTH_SHORT).show()
                    }
                    focusManager.clearFocus()
                }),
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp
                ),
            )
        }
    }
}