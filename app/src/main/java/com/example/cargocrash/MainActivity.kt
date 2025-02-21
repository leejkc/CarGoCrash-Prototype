package com.example.cargocrash

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.media3.common.util.UnstableApi
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cargocrash.ui.theme.CarGoCrashTheme

class MainActivity : ComponentActivity() {
    @OptIn(UnstableApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarGoCrashTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@UnstableApi
@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                on1Click = { navController.navigate("hello1") },
                on2Click = { navController.navigate("video2") }
            )
        }
        composable("hello1") { Hello1Screen(onBackClick = { navController.popBackStack() }) }
        composable("video2") { Video2Screen(onBackClick = { navController.popBackStack() }) }
    }
}

// home screen
@Composable
fun HomeScreen(
    on1Click: () -> Unit,
    on2Click: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Existing content
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.crashkit),
                contentDescription = "Title",
                modifier = Modifier
                    .padding(vertical = 70.dp)
                    .height(80.dp)
                    .clip(shape = RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = on1Click,
                modifier = Modifier.padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B0C1C),
                    contentColor = Color.White
                )
            ) {
                Text("Text Example")
            }

            Button(
                onClick = on2Click,
                modifier = Modifier.padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B0C1C),
                    contentColor = Color.White
                )
            ) {
                Text("Video Example")
            }
        }
    }
}

// sub-screens
@Composable
fun Hello1Screen(onBackClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "SAMPLE TEXT",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onBackClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B0C1C),
                    contentColor = Color.White
                )
            ) {
                Text("Back")
            }
        }
    }
}

@UnstableApi
@Composable
fun Video2Screen(onBackClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.crashkit),
                contentDescription = "Title",
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .height(70.dp)
                    .clip(shape = RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            LocalVideoScreen("getting_rear_ended")

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onBackClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B0C1C),
                    contentColor = Color.White
                )
            ) {
                Text("Back")
            }
        }
    }
}
@UnstableApi
@Composable
fun LocalVideoScreen(dir: String) {
    val context = LocalContext.current
    val videoUri = remember {
        Uri.parse("android.resource://${context.packageName}/raw/$dir")
    }

    VideoPlayer(
        videoUri = videoUri.toString(),
        modifier = Modifier.clip(shape = RoundedCornerShape(16.dp))
    )
}


// screen previews
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    CarGoCrashTheme {
        HomeScreen({}, {})
    }
}

@Preview(showBackground = true)
@Composable
fun Hello1ScreenPreview() {
    CarGoCrashTheme {
        Hello1Screen({})
    }
}

@OptIn(UnstableApi::class)
@Preview(showBackground = true)
@Composable
fun Video2ScreenPreview() {
    CarGoCrashTheme {
        Video2Screen({})
    }
}

@Preview(showBackground = true)
@UnstableApi
@Composable
fun VideoScreenPreview() {
    CarGoCrashTheme {
        LocalVideoScreen("getting_rear_ended") // example video
    }
}