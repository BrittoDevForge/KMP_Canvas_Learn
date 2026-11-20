package app.brittodev.canvas.canvasElements

import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke

import androidx.compose.ui.unit.dp
import kotlin.time.Duration.Companion.seconds


@Composable
fun CallButtonCanvas(radius: Float) {

    val infiniteTransition = rememberInfiniteTransition()

    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000),
            repeatMode = RepeatMode.Restart
        )
    )

    val animatedRadius by animateFloatAsState(
        targetValue = radius,
        animationSpec = tween(350)
    )

    Canvas(
        modifier = Modifier.size(300.dp).background(color = Color.Black)
    ) {

        listOf(0f,0.33f,0.66f).forEach { value ->
            val waveProgress = (progress + value) % 1f
            val radius = 40.dp.toPx() + (60.dp.toPx() * waveProgress)
            val alpha = 1f - waveProgress

            drawCircle(
                color = Color.Green.copy(alpha),
                radius = radius,
                style = Stroke(width = 5f)
            )
        }

        drawCircle(
            color = Color.Green.copy(alpha = 0.5f),
            radius = animatedRadius.dp.toPx(),
        )

        drawCircle(
            color = Color.Green,
            radius = 40f
        )
//        drawCircle(
//            color = Color.Green.copy(alpha = 0.5f),
//            radius = 80f,
//            style = Stroke(width = 5.dp.toPx())
//        )
//
//        drawCircle(
//            color = Color.Green.copy(alpha = 0.2f),
//            radius = 100f,
//            style = Stroke(width = 5.dp.toPx())
//        )
    }
}