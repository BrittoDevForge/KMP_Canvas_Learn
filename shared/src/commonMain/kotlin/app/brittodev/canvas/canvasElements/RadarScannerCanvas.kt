package app.brittodev.canvas.canvasElements

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun RadarScannerCanvas(
    modifier: Modifier = Modifier
) {

    val infiniteTransition = rememberInfiniteTransition()
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                2000,
                easing = LinearEasing
            )
        )
    )

    Canvas(
        modifier = Modifier
            .size(500.dp)
            .background(color = Color.Black)
    ) {

        val maxRadius = size.minDimension/2f
        val angle = 360 * progress
        val radians = Math.toRadians(angle.toDouble())

        val endX = center.x + maxRadius * cos(radians).toFloat()
        val endY = center.y + maxRadius * sin(radians).toFloat()

        for (i in 1..8) {
            drawCircle(
                color = Color.Green,
                radius = maxRadius * (i/8f),
                style = Stroke(width = 1f)
            )
        }
        drawLine(
            color = Color.Green.copy(alpha = .5f),
            start = Offset(0f,center.y),
            end = Offset(size.width,center.y),
            strokeWidth = 1f
        )
        drawLine(
            color = Color.Green.copy(alpha = .5f),
            start = Offset(center.x,0f),
            end = Offset(center.x,size.height),
            strokeWidth = 1f
        )
        drawLine(
            color = Color.Green,
            start = center,
            end = Offset(endX,endY),
            strokeWidth = 3f
        )
        for (i in 1..4) {
            drawArc(
                color = Color.Green.copy(.05f + (i/4f)* .05f),
                startAngle = angle - 45f*(i/4f),
                sweepAngle = 45f * (i/4f),
                useCenter = true
            )
        }
        drawCircle(
            color = Color.Green,
            radius = 5.dp.toPx(),
            center = Offset(
                x = center.x + 50.dp.toPx(),
                y = center.y - 40.dp.toPx()
            )
        )
        drawCircle(
            color = Color.Green,
            radius = 5.dp.toPx(),
            center = Offset(
                x = center.x - 70.dp.toPx(),
                y = center.y + 30.dp.toPx()
            )
        )
        drawCircle(
            color = Color.Green,
            radius = 5.dp.toPx(),
            center = Offset(
                x = center.x + 20.dp.toPx(),
                y = center.y + 85.dp.toPx()
            )
        )
    }
}