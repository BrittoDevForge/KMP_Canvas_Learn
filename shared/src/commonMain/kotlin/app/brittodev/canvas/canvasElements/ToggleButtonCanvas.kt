package app.brittodev.canvas.canvasElements

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlin.math.sign


@Composable
fun ToggleButtonCanvas() {

    var isOn by remember { mutableStateOf(false) }
    var isPressed by remember { mutableStateOf(false) }
    val animatedProgress by animateFloatAsState(
        targetValue = if (isOn) 1f else 0f,
        animationSpec = tween(300)
    )
    val trackColor by animateColorAsState(
        targetValue = if (isOn) Color.Green else Color.DarkGray,
        animationSpec = tween(300)
    )
    val animatedThumbRadius by animateFloatAsState(
        targetValue = if (isPressed) 21f else 24f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    Canvas(
        modifier = Modifier
            .size(width = 120.dp, height = 64.dp)
            .clip(shape = RoundedCornerShape(32.dp))
            .clickable {
                isOn = !isOn
            }
            .pointerInput(Unit) {
                awaitEachGesture {
                    awaitFirstDown()
                    isPressed = true
                    waitForUpOrCancellation()
                    isPressed = false
                }
            }

    ) {
        val thumbPadding = 8.dp
        val normalRadius = 24.dp
        val thumbRadius = animatedThumbRadius.toDp().toPx()
        val startX = thumbPadding + normalRadius
        val endX = size.width.toDp() - normalRadius - thumbPadding
        val thumbX = startX + (endX-startX) * animatedProgress
        drawRoundRect(
            color = trackColor,
            cornerRadius = CornerRadius(size.height/2f)
        )

        drawCircle(
            color = Color.Black.copy(alpha = .5f),
            radius = thumbRadius + 2.dp.toPx(),
            center = Offset(
                x = thumbX.toPx()+3.dp.toPx(),
                y = size.height/2f + 2.dp.toPx()
            )
        )
        drawCircle(
            color = Color.White,
            radius = thumbRadius,
            center = Offset(
                x = thumbX.toPx(),
                y = size.height/2f
            )
        )
    }
}