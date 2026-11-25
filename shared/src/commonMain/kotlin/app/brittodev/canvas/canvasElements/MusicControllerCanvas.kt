package app.brittodev.canvas.canvasElements

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.style.Style
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun MusicControllerCanvas() {

    var progress by remember { mutableStateOf(1f) }
    var thumbAngle by remember { mutableStateOf(0f) }
    var isPlaying by remember { mutableStateOf(false) }
    val totalDuration = 180
    val currentTime = (progress * totalDuration).toInt()

    val textMeasurer = rememberTextMeasurer()

    val minutes = currentTime/60
    val seconds = currentTime%60

    val formattedTime = "%d:%02d".format(minutes,seconds)
    val style = TextStyle(color = Color.Black, fontSize = 64.sp)
    val textLayoutResult = textMeasurer.measure(formattedTime,style)
    val animatedProgress = remember {
        Animatable(0f)
    }

    LaunchedEffect(progress) {
        animatedProgress.animateTo(
            targetValue = progress,
            animationSpec = tween(0)
        )
    }

    LaunchedEffect(isPlaying) {
        while (isPlaying && progress < 1f) {
            delay(1000)
            progress = (progress + 1f / 180f).coerceAtMost(1f)
        }
    }

    thumbAngle = -90f + (animatedProgress.value * 360f)

    Slider(
        value = progress,
        onValueChange = {
            progress = it
        }
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .size(500.dp)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        val angle = atan2(
                            y = change.position.y - size.height/2f.toDouble(),
                            x = change.position.x - size.width/2f.toDouble()
                        ) // in radians
                        val degree = Math.toDegrees(angle)
                        val adjustedAngle = degree + 90f
                        val normalizedAngle = (adjustedAngle + 360) % 360
                        progress = normalizedAngle.toFloat() / 360f
                    }
                }

        ) {
            val radius = size.minDimension/2f
            val thumbX = center.x + (radius * cos(Math.toRadians(thumbAngle.toDouble())).toFloat())
            val thumbY = center.y + (radius * sin(Math.toRadians(thumbAngle.toDouble())).toFloat())
            drawCircle(
                color = Color.DarkGray,
                radius = radius,
                style = Stroke(
                    width = 24.dp.toPx()
                )
            )
            drawArc(
                color = Color.Cyan,
                startAngle = -90f,
                sweepAngle = animatedProgress.value * 360f,
                useCenter = false,
                style = Stroke(
                    width = 24f,
                    cap = StrokeCap.Round
                )
            )
            drawCircle(
                color = Color.White,
                radius = 12.dp.toPx(),
                center = Offset(thumbX,thumbY)
            )
            drawText(
                textLayoutResult = textLayoutResult,
                topLeft = Offset(
                    x = center.x - textLayoutResult.size.width/2f,
                    y = center.y + textLayoutResult.size.height
                )
            )
        }

        IconButton(
            onClick = {
                isPlaying = !isPlaying
            },
            modifier = Modifier.size(100.dp)
        ) {
            Icon(
                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
        }
    }
}