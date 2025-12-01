package afr.namtas.apps.tapestry

import afr.namtas.apps.tapestry.ui.theme.TapestryTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

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
                            .padding(innerPadding), contentAlignment = Alignment.Center
                    ) {
                        Mural(Modifier.background(color = Color.White))
                    }
                }
            }
        }
    }
}

@Composable
fun Mural(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(300.dp)) {
        drawCircle(color = Color.Gray, radius = 160f, alpha = 0.3f)
        drawCircle(color = Color.Red, radius = 120f, style = Stroke(width = 8f))
        drawCircle(color = Color.White, radius = 100f)
        drawCircle(color = Color.Red, radius = 80f, style = Stroke(width = 20f))
        drawLine(color = Color.Black, start = Offset(x = center.x - 16f, y = center.y), end = Offset(x = center.x + 16f, y = center.y), strokeWidth = 4f)
        drawLine(color = Color.Black, start = Offset(x = center.x, y = center.y - 16f), end = Offset(x = center.x, y = center.y + 16f), strokeWidth = 4f)

        val radius = 160f
        val angle12Oclock = -90.0
        drawDot(angle12Oclock, radius, 10f, Color.Blue)
        val angle130 = (-45.0)
        drawDot(angle130, radius, 10f, Color.Yellow)
        val angle3Oclock = 0.0
        drawDot(angle3Oclock, radius, 10f, Color.Blue)
        val angle430 = 45.0
        drawDot(angle430, radius, 10f, Color.Green)
        val angle6Oclock = 90.0
        drawDot(angle6Oclock, radius, 10f, Color.Blue)
        val angle730 = 135.0
        drawDot(angle730, radius, 10f, Color.Yellow)
        val angle9Oclock = 180.0
        drawDot(angle9Oclock, radius, 10f, Color.Blue)
        val angle1030 = 225.0
        drawDot(angle1030, radius, 10f, Color.Green)
    }
}

fun DrawScope.drawDot(degrees: Double, radius: Float, dotSize: Float, color: Color) {
    val angle = degrees.toRadians()
    val x = center.x + radius * cos(angle)
    val y = center.y + radius * sin(angle)
    drawCircle(color = color, radius = dotSize, center = Offset(x, y))
}

fun Double.toRadians(): Float = Math.toRadians(this).toFloat()