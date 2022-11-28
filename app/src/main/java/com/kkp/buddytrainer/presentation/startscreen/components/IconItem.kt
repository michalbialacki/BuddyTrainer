package com.kkp.buddytrainer.presentation.startscreen.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kkp.buddytrainer.R

@Composable
fun IconItem(
    icon : ImageVector = Icons.Default.Menu,
    onSelect:() -> Unit
) {
    Icon(imageVector = icon, contentDescription = "Icon", modifier = Modifier.size(30.dp).clickable {
        onSelect()
    })

}