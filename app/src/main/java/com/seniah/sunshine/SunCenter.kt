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
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun SunCenter() {
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
        SunCenterAnimation(widthPx, heightPx)
    }
}

@Composable
fun SunCenterAnimation(width: Float, height: Float) {
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 360F,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 7000, easing = FastOutLinearInEasing)
        )
    )
    val tri1 = getTrianglePath(width / 2f, width / 3f, height / 2f)
    val colorsBeam = listOf(Color(0x339EDB9B), Color(0xFF9EDB9B), Color(0xFF9EDB9B))
    val colorsSun = listOf(Color(0xFF9EDB9B), Color(0xFF9EDBAA))
    val offset = Offset(width / 2f, height / 2f)

    Canvas(
        modifier = Modifier.fillMaxSize(),
        onDraw = {
            withTransform({
                translate(left = 0f)
                rotate(
                    degrees = angle,
                )
            }) {
                rotate(
                    degrees = 0f
                ) {
                    drawPath(
                        path = tri1,
                        brush = Brush.verticalGradient(colors = colorsBeam)
                    )
                }
                rotate(
                    degrees = 90f
                ) {
                    drawPath(
                        path = tri1,
                        brush = Brush.verticalGradient(colors = colorsBeam)
                    )
                }

                rotate(
                    degrees = 180f
                ) {
                    drawPath(
                        path = tri1,
                        brush = Brush.verticalGradient(colors = colorsBeam)
                    )
                }

                rotate(
                    degrees = 270f
                ) {
                    drawPath(
                        path = tri1,
                        brush = Brush.verticalGradient(colors = colorsBeam)
                    )
                }
            }

            drawCircle(
                brush = Brush.radialGradient(colors = colorsSun),
                radius = width / 10f,
                center = offset
            )
        }
    )
}
