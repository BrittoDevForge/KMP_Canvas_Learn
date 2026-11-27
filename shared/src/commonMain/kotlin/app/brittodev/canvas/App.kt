package app.brittodev.canvas

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.brittodev.canvas.canvasElements.CallButtonCanvas
import app.brittodev.canvas.canvasElements.CircularVolumeKnob
import app.brittodev.canvas.canvasElements.FingerprintScannerComposable
import app.brittodev.canvas.canvasElements.MusicControllerCanvas
import app.brittodev.canvas.canvasElements.RadarScannerCanvas
import app.brittodev.canvas.canvasElements.ToggleButtonCanvas

@Composable
@Preview(
    showSystemUi = true,
    showBackground = true
)
fun App() {

    var radius by remember { mutableStateOf(40f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = { radius = 40f }
                ) { change, dragAmount ->
                    radius += dragAmount.x
                    radius = radius.coerceIn(40f,100f)
                }
            },
        contentAlignment = Alignment.Center
    ) {

        RadarScannerCanvas()
//        FingerprintScannerComposable()
//        MusicControllerCanvas()
//        ToggleButtonCanvas()
//        CircularVolumeKnob(volume = 0f)
//        CallButtonCanvas(radius)
//        Icon(
//            imageVector = Icons.Default.Call,
//            contentDescription = null,
//            modifier = Modifier.size(50.dp)
//        )
    } 




    
    // MaterialTheme {
    //     var showContent by remember { mutableStateOf(false) }
    //     Column(
    //         modifier = Modifier
    //             .background(MaterialTheme.colorScheme.primaryContainer)
    //             .safeContentPadding()
    //             .fillMaxSize(),
    //         horizontalAlignment = Alignment.CenterHorizontally,
    //     ) {
    //         Button(onClick = { showContent = !showContent }) {
    //             Text("Click me!")
    //         }
    //         AnimatedVisibility(showContent) {
    //             val greeting = remember { Greeting().greet() }
    //             Column(
    //                 modifier = Modifier.fillMaxWidth(),
    //                 horizontalAlignment = Alignment.CenterHorizontally,
    //             ) {
    //                 Image(painterResource(Res.drawable.compose_multiplatform), null)
    //                 Text("Compose: $greeting")
    //             }
    //         }
    //     }
    // }
}