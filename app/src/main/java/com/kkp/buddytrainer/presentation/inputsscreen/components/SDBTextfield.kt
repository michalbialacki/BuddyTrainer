package com.kkp.buddytrainer.presentation.inputsscreen.components

import android.widget.Toast
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kkp.buddytrainer.domain.model.Person
import com.kkp.buddytrainer.ui.theme.BuddyTrainerTheme
import java.lang.NumberFormatException

@Composable
fun SDBTextfield(
    exerciseRecordValue : Float,
) {
    var exerciseRecordValueLocal = exerciseRecordValue
    var text by remember {
        mutableStateOf(TextFieldValue(exerciseRecordValueLocal.toString()))
    }
    val context = LocalContext.current
    Box{
        Row (
            modifier = Modifier
                .fillMaxWidth()
        ){
            Box(modifier = Modifier.weight(0.5f))  {
                /*"minus" icon*/
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Reduce weight",
                    modifier = Modifier
                        .pointerInput(Unit){
                            detectTapGestures(
                                onTap = {
                                    when(exerciseRecordValueLocal){
                                        in (0.5f..500.0f) -> exerciseRecordValueLocal -= 0.5f
                                        else -> Toast.makeText(context,"Input real weight",Toast.LENGTH_SHORT).show()
                                    }
                            }
                        )
                        }
                    )
            }
            TextField(
                value = text ,
                onValueChange = {changedText ->
                    try {
                        if (changedText.text.toFloat() in(0.5f..500f)){
                            text = changedText
                        }else{
                            text = text
                        }
                    }catch (e:NumberFormatException){
                        text = TextFieldValue(0f.toString())
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp)),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
            )
            /*TODO(Get this logic straight.)*/
            Box(modifier = Modifier.weight(0.5f)){
                /*"plus" icon*/
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Add weight",
                    modifier = Modifier
                        .pointerInput(Unit){
                            detectTapGestures(
                                onTap = {
                                    when(exerciseRecordValueLocal){
                                        in (0.0f..500.0f) -> exerciseRecordValueLocal += 0.5f
                                        else -> Toast.makeText(context,"Input real weight",Toast.LENGTH_SHORT).show()
                                    }
                                }
                            )
                        }
                    )
            }
        }
    }
}