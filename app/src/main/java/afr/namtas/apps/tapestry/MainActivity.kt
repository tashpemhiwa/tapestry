package afr.namtas.apps.tapestry

import afr.namtas.apps.tapestry.ui.theme.TapestryTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TapestryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.fillMaxSize().padding(innerPadding), contentAlignment = Alignment.Center) {
                        Canvas(modifier = Modifier.size(300.dp)) {
                            drawCircle(color = Color.Red, radius = 120f)
                            drawCircle(color = Color.White, radius = 100f)
                            drawCircle(color = Color.Red, radius = 80f)
                            drawLine(color = Color.Black, start = Offset(x = center.x - 12f, y = center.y), end = Offset(x = center.x + 12f, y = center.y), strokeWidth = 2f)
                            drawLine(color = Color.Black, start = Offset(x = center.x, y = center.y - 12f), end = Offset(x = center.x, y = center.y + 12f), strokeWidth = 2f)
                        }
                    }
                }
            }
        }
    }
}