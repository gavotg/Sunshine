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
fun SunLight() {
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
        SunLightAnimation(widthPx, heightPx)
    }
}

@Composable
fun SunLightAnimation(width: Float, height: Float) {
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 360F,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 9000, easing = LinearEasing)
        )
    )
    val tri1 = getTrianglePath(width / 2f, width / 4f, height)
    val tri2 = getTrianglePath(width / 2f, width / 2.6f, height)
    val colorsBeam = listOf(Color(0x33FCE13D), Color(0xFFFEB125))
    val colorsSun = listOf(Color(0xFFFC8E1F), Color(0xFFFEB125))
    val offset = Offset(width / 2f, height)

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
                    brush = Brush.verticalGradient(colors = colorsBeam)
                )

                rotate(
                    degrees = 45f, pivot = offset
                ) {
                    drawPath(
                        path = tri2,
                        brush = Brush.verticalGradient(colors = colorsBeam)
                    )
                }

                rotate(
                    degrees = 90f, pivot = offset
                ) {
                    drawPath(
                        path = tri1,
                        brush = Brush.verticalGradient(colors = colorsBeam)
                    )
                }

                rotate(
                    degrees = 135f, pivot = offset
                ) {
                    drawPath(
                        path = tri2,
                        brush = Brush.verticalGradient(colors = colorsBeam)
                    )
                }

                rotate(
                    degrees = 180f, pivot = offset
                ) {
                    drawPath(
                        path = tri1,
                        brush = Brush.verticalGradient(colors = colorsBeam)
                    )
                }

                rotate(
                    degrees = 225f, pivot = offset
                ) {
                    drawPath(
                        path = tri2,
                        brush = Brush.verticalGradient(colors = colorsBeam)
                    )
                }

                rotate(
                    degrees = 270f, pivot = offset
                ) {
                    drawPath(
                        path = tri1,
                        brush = Brush.verticalGradient(colors = colorsBeam)
                    )
                }

                rotate(
                    degrees = 315f, pivot = offset
                ) {
                    drawPath(
                        path = tri2,
                        brush = Brush.verticalGradient(colors = colorsBeam)
                    )
                }
            }

            drawCircle(
                brush = Brush.radialGradient(colors = colorsSun),
                radius = width / 5f,
                center = offset
            )
        }
    )
}
