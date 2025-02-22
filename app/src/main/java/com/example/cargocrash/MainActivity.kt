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
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
            HomeScreen(collisionsPageClick = { navController.navigate("collisionsPage") })
        }
        composable("collisionsPage") {
            CollisionsPage(
                onBackClick = { navController.popBackStack() },
                on2Click = { navController.navigate("video2") }
            )
        }
        composable("video2") { Video2Screen(onBackClick = { navController.popBackStack() }) }
    }
}

// home screen
@Composable
fun HomeScreen(
    collisionsPageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        // background Image
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            // use lazyColumn if there are more elements that don't fit on screen
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.crashkit),
                contentDescription = "Title",
                modifier = Modifier
                    .padding(vertical = 80.dp)
                    .height(80.dp)
                    .clip(shape = RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

        }

//        Column(
//            modifier = Modifier.fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Bottom
//        ) {
//            Text(
//                "Demo Y2 v0.3",
//                fontWeight = FontWeight.Bold,
//                fontSize = 20.sp,
//                modifier = Modifier.padding(vertical = 80.dp),
//                color = Color(0xFF5B0C1C)
//            )
//        }

        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

//            Button(
//                onClick = on2Click,
//                modifier = Modifier.padding(8.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFF5B0C1C),
//                    contentColor = Color.White
//                )
//            ) {
//                Text(
//                    "Video Example",
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 20.sp
//                )
//            } note: old button for video example

            var expanded by remember { mutableStateOf(false) }
            var selectedIndex by remember { mutableStateOf(0) }
            val items = listOf("Select Here!", "Collisions", "-More soon!")

            Box (
                modifier = Modifier
                    .background(Color(0xFF5B0C1C), shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .width(200.dp)
                    .clickable { expanded = true }
            ) {
                Text(
                    items[selectedIndex],
                    modifier = Modifier.clickable { expanded = true },
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    items.forEachIndexed { index, item ->
                        DropdownMenuItem(
                            text = { Text(item) },
                            onClick = {
                                when (index) {
                                    1 -> {
                                        collisionsPageClick()
                                        selectedIndex = index
                                    }
                                    else -> {}
                                }
                                expanded = false
                            },
                            enabled = index == 1 // disable buttons 1 and 3
                        )
                    }
                }
            }

        }
    }
}

// sub-screens
@UnstableApi
@Composable
fun Video2Screen(onBackClick: () -> Unit) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF5B0C1C))
    ) {

        val scrollState = rememberScrollState()
        Column(
            // use lazyColumn if there are more elements that don't fit on screen
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.crashkit),
                contentDescription = "Title",
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .height(70.dp)
                    .clip(shape = RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))

            Box (
                modifier = Modifier
                    .background(Color(0xFFf2ebed), shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus tellus tellus, accumsan non nisl quis, efficitur accumsan purus. Vivamus iaculis ligula sed maximus lobortis. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nunc non sagittis urna, ac porttitor tortor. Duis dui sapien, vulputate nec enim ut, dignissim rhoncus lorem. Aliquam in vehicula mauris. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Praesent et ligula in leo gravida lobortis ac eu orci. Fusce nulla augue, placerat sit amet velit at, faucibus scelerisque mi. Praesent ante ex, fermentum et pretium ac, porta non massa. Cras placerat mollis fermentum.",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF5B0C1C)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            LocalVideoScreen("getting_rear_ended")

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onBackClick,
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color(0xFF5B0C1C),
                    containerColor = Color.White
                )
            ) {
                Text(
                    "Back",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(64.dp))

        }

    }
}

@Composable
fun CollisionsPage(
    on2Click: () -> Unit,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()) {
        // background Image
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        val scrollState = rememberScrollState()
        Column(
            // use lazyColumn if there are more elements that don't fit on screen
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.crashkit),
                contentDescription = "Title",
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .height(70.dp)
                    .clip(shape = RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = on2Click,
                modifier = Modifier.padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B0C1C),
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Getting Rear Ended",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            // empty buttons
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B0C1C),
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Empty Button Example",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B0C1C),
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Empty Button Example",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B0C1C),
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Empty Button Example",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B0C1C),
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Empty Button Example",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B0C1C),
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Empty Button Example",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B0C1C),
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Empty Button Example",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B0C1C),
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Empty Button Example",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B0C1C),
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Empty Button Example",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B0C1C),
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Empty Button Example",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B0C1C),
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Empty Button Example",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B0C1C),
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Empty Button Example",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B0C1C),
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Empty Button Example",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B0C1C),
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Empty Button Example",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B0C1C),
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Empty Button Example",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B0C1C),
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Empty Button Example",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B0C1C),
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Empty Button Example",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B0C1C),
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Empty Button Example",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B0C1C),
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Empty Button Example",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B0C1C),
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Empty Button Example",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B0C1C),
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Empty Button Example",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B0C1C),
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Empty Button Example",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B0C1C),
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Empty Button Example",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B0C1C),
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Empty Button Example",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B0C1C),
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Empty Button Example",
                    fontWeight = FontWeight.Bold
                )
            }

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
            Spacer(modifier = Modifier.height(64.dp))

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
        HomeScreen(collisionsPageClick = {})
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

@Preview(showBackground = true)
@Composable
fun CollisionsPagePreview() {
    CarGoCrashTheme {
        CollisionsPage(on2Click = {}, onBackClick = {})
    }
}