package com.seniah.sunshine

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun SearchLight() {
    val width = 300.dp
    val height = 200.dp
    val widthPx = with(LocalDensity.current) { width.toPx() }
    val heightPx = with(LocalDensity.current) { height.toPx() }

    Card(
        modifier = Modifier
            .width(width)
            .height(height),
        elevation = 8.dp
    ) {
        SearchLightAnimation(widthPx, heightPx)
    }
}

@Composable
fun SearchLightAnimation(width: Float, height: Float) {
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = -100F,
        targetValue = 100F,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val tri1 = getTrianglePath(width / 2f, width / 5f, height)
    val tri2 = getTrianglePath(width / 2f, width / 17f, height)
    val colors1 = listOf(Color(0xFF02B8F9), Color(0x0000FF00))
    val colors2 = listOf(Color(0xEEFFFFFF), Color(0x00FFFFFF))

    Canvas(
        modifier = Modifier.fillMaxSize(),
        onDraw = {
            withTransform({
                translate(left = 0f)
                rotate(
                    degrees = angle,
                    pivot = Offset(width / 2f, height)
                )
            }) {
                drawPath(
                    path = tri1,
                    Brush.horizontalGradient(colors = colors1)
                )
                drawPath(
                    path = tri2,
                    Brush.horizontalGradient(colors = colors2)
                )
            }
        }
    )
}

fun getTrianglePath(midPoint: Float, scale: Float, length: Float) = Path().apply {
    moveTo(midPoint, length)
    lineTo(midPoint - scale, -length)
    lineTo(midPoint + scale, -length)
    close()
}
