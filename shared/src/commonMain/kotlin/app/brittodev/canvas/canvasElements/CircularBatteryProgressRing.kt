package app.brittodev.canvas.canvasElements

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt


@Composable
fun CircularBatteryRingCanvas(
    modifier: Modifier = Modifier
) {

    var progress by remember {
        mutableStateOf(0f)
    }

    Slider(
        value = progress,
        onValueChange = {
            progress = it
        },
        valueRange = 0f..1f
    )

    val textMeasurer = rememberTextMeasurer()
    val textLayoutResult = textMeasurer.measure(
        text = (progress*100).roundToInt().toString(),
        style = TextStyle(fontSize = 56.sp, color = Color.White),
    )
    val arcColor = lerp(
        start = Color.Red,
        stop = Color.Green,
        fraction = progress
    )
    Canvas(
        modifier = Modifier
            .size(500.dp)
            .background(color = Color.Black)
    ) {
        val strokeWidth = 14.dp.toPx()
        val radius = size.minDimension/2f - strokeWidth/2f

        drawCircle(
            color = Color.DarkGray,
            radius = radius,
            style = Stroke(width = strokeWidth)
        )
        inset(size.minDimension/2f - radius) {
            drawArc(
                color = arcColor,
                startAngle = -90f,
                sweepAngle = 360 * progress,
                useCenter = false,
                style = Stroke(width = strokeWidth),
            )
        }
        drawText(
            textLayoutResult = textLayoutResult,
            topLeft = Offset(
                x = center.x - textLayoutResult.size.width/2f,
                y = center.y - textLayoutResult.size.height/2f
            )
        )

    }
}