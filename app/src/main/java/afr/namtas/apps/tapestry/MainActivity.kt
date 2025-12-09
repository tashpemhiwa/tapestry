package afr.namtas.apps.tapestry

import afr.namtas.apps.tapestry.ui.theme.TapestryTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
                        HeartShape()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HeartShape() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "An empty green heart shape",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        Canvas(
            modifier = Modifier
                .size(400.dp)
                .background(Color.LightGray.copy(alpha = 0.2f))
        ) {
            val pixelWidth = size.width
            val pixelHeight = size.height

            val path = Path().apply {
                // Step 1: Start 2/3 of the way down and half-way between left and right
                moveTo((pixelWidth / 2), (pixelHeight * 0.66f))

                // Step 2: Draw left side with curved top
                quadraticTo(pixelWidth * 0.05f, pixelHeight * 0.05f, pixelWidth *.5f, pixelHeight * 0.2f)

                // Step 3: Draw right side (mirror the left side)
                // Hint: Use moveTo() and quadraticTo()
                moveTo((pixelWidth / 2), (pixelHeight * 0.66f))
                quadraticTo(pixelWidth * 0.95f, pixelHeight * 0.05f, pixelWidth *.5f, pixelHeight * 0.2f)

                // Step 5: Close the path
                //close()
            }

            // Step 6: Draw the path
            drawPath(
                path = path,
                color = Color.Green,
                style = Stroke(5.0f)
            )
        }
    }
}