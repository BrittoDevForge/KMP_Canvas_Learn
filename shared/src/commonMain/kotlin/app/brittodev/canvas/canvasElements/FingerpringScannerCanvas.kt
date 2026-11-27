package app.brittodev.canvas.canvasElements

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp


@Composable
fun FingerprintScannerComposable(
    modifier: Modifier = Modifier,

) {
    Canvas(
        modifier = Modifier
            .size(500.dp)
            .background(color = Color.Black)
    ) {
        val path = Path()

        path.moveTo(
            x = size.width * .2f,
            y = size.height * .5f
        )

        path.quadraticTo(
            size.width * .5f,
            size.height * .2f,
            size.width * .8f,
            size.height * .5f
        )

        path.moveTo(
            x = size.width * .2f,
            y = size.height * .65f
        )

        path.cubicTo(
            size.width * .5f,
            size.height * .85f,
            size.width * .5f,
            size.height * .5f,
            size.width * .8f,
            size.height * .65f
        )

        drawPath(
            path = path,
            color = Color.Cyan,
            style = Stroke(width = 5f)
        )
    }
}