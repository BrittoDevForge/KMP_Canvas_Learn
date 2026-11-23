package app.brittodev.canvas.canvasElements

import androidx.compose.animation.core.EaseInBack
import androidx.compose.animation.core.EaseInCirc
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun CircularVolumeKnob(
    modifier: Modifier = Modifier,
    volume: Float = 0f
) {
    var volumeLevel by remember { mutableStateOf(volume) }
    val animatedVolumeLevel by animateFloatAsState(
        targetValue = volumeLevel,
        animationSpec = tween(
            durationMillis = 200
        )
    )
    Canvas(
        modifier = Modifier
            .size(500.dp)
            .background(color = Color.Black)
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    val centerX = size.width/2f
                    val centerY = size.height/2f
                    val angle = calculateAngle(change.position.x,change.position.y,centerX, centerY)
                    //println("Angle : $angle")
                    //println("volume level : ${angleToVolume(angle)}")
                    volumeLevel = angleToVolume(angle)
                }
            }
    ) {
        inset(50.dp.toPx()) {

            val radius = size.minDimension / 2f
            val thumbX = center.x + radius * cos((360*animatedVolumeLevel-90f)*(PI/180)).toFloat()
            val thumbY = center.y + radius * sin((360*animatedVolumeLevel-90f)*(PI/180)).toFloat()

            drawCircle(
                color = Color.Gray,
                style = Stroke(15f)
            )
            drawArc(
                color = Color.Green,
                startAngle = -90f,
                sweepAngle = 360 * animatedVolumeLevel,
                useCenter = false,
                style = Stroke(width = 15f, cap = StrokeCap.Round)
            )
            drawCircle(
                color = Color.Cyan,
                radius = 20.dp.toPx(),
                center = Offset(thumbX,thumbY)
            )
        }
    }
}

fun calculateAngle(
    touchX: Float, // finger pos
    touchY: Float, // finger pos
    centerX: Float,
    centerY: Float
): Float {
    println("touchX : $touchX \ntouchY : $touchY ")
    val dx = touchX - centerX // where from the center x
    val dy = touchY - centerY // where from the center y

    val radians = atan2(dy,dx) // angle in radian unit
    val angle = radians * (180f/PI).toFloat() // angle in deg
    return angle
}

fun angleToVolume(angle: Float): Float {
    val adjustedAngle = angle + 90f // make the starting point as top
    val normalizedAngle = (adjustedAngle+360f) % 360f // circle has different angle, we normalize within 360
    val volume = normalizedAngle / 360f // value this b/w 0f , 1f
    return volume
}
