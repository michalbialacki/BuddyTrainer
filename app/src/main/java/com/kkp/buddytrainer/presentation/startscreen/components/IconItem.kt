package com.kkp.buddytrainer.presentation.startscreen.components

import android.os.Build.VERSION.SDK_INT
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.kkp.buddytrainer.R

@Composable
fun IconItem(
    painter: Painter,
    onSelect:() -> Unit
) {
    val iconSize = 35.dp
    Box(modifier = Modifier
        .size(iconSize + 10.dp)
        .padding(2.dp)
        .clip(CircleShape)
        .background(MaterialTheme.colors.primary)
        .clickable {
            onSelect()
        },
        contentAlignment = Alignment.Center
    ){
        Icon(painter = painter, contentDescription = "Icon", modifier = Modifier
            .size(iconSize),
            tint = Color(255, 255, 240)
        )
    }

}

@Composable
fun IconGif(
    gifId : Int,
    onSelect: () -> Unit
) {
    val iconSize = 35.dp

    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(iconSize + 5.dp)
                .background(color = MaterialTheme.colors.primary),
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(context).data(data = gifId).apply(block = {
                        size(Size.ORIGINAL)
                    }).build(), imageLoader = imageLoader
                ),
                contentDescription = null,
                modifier = Modifier.size(iconSize).clickable { onSelect() },
                colorFilter = ColorFilter.tint(color = Color(255, 255, 240))
            )
        }


}