package afr.namtas.apps.tapestry

import afr.namtas.apps.tapestry.ui.theme.TapestryTheme
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TapestryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black)
                            .padding(innerPadding), contentAlignment = Alignment.Center
                    ) {
                        DrawAnimatedArrow()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DrawAnimatedArrow() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val tweenScaleXFactor = remember { Animatable(1f) }
        val tweenScaleYFactor = remember { Animatable(1f) }
        val rotationAngle = remember { Animatable(0f) }
        var visibility by remember { mutableStateOf(false) }
        LaunchedEffect((Any())) {
            delay(2000)
            visibility = true
            rotationAngle.animateTo(405f, spring(Spring.DampingRatioMediumBouncy, Spring.StiffnessLow))
            tweenScaleXFactor.animateTo(4f, tween(3000))
            tweenScaleYFactor.animateTo(4f, tween(3000))
        }

        AnimatedVisibility(visibility) {
            Canvas(
                modifier = Modifier
                    .size(400.dp)
                    .background(Color.LightGray.copy(alpha = 0.2f))
            ) {
                //1. Simple arrow
                //drawSampleTextArrow()

                //2. Scaled arrow
                /*scale(scaleX = tweenScaleXFactor.value, scaleY = tweenScaleYFactor.value) {
                    drawSampleTextArrow(false)
                }*/

                //2. Arrow rotated 45 degrees
                /*rotate(degrees = 45f, pivot = center) {
                    // Everything drawn here rotates 45° around center
                    drawSampleTextArrow(false)
                }*/

                rotate(degrees = rotationAngle.value, pivot = center) {
                    scale(tweenScaleXFactor.value, tweenScaleYFactor.value) {
                        // Everything drawn here rotates 45° around center
                        drawSampleTextArrow()
                    }
                }
            }
        }
    }
}

private fun DrawScope.drawSampleTextArrow() {
    drawIntoCanvas { canvas ->
        val paint = Paint().apply {
            color = android.graphics.Color.WHITE
            textSize = 40f
            typeface = Typeface.SANS_SERIF
            isAntiAlias = true
        }
        val txt = ">------------>"
        val x = size.width / 2f
        val y = size.height / 2f + paint.textSize / 2f
        canvas.nativeCanvas.drawText(txt, x - paint.measureText(txt) / 2f, y, paint)
    }
}