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

        Column(
            // use lazyColumn if there are more elements that don't fit on screen
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom
        ) {
            Image(
                painter = painterResource(id = R.drawable.character),
                contentDescription = "Character",
                modifier = Modifier
                    .width(200.dp)
                    .clip(shape = RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

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
        val superHeaderSize = 25.sp
        val headerSize = 20.sp
        val textSize = 13.sp
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
                    .height(80.dp)
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
                Column {
                    Text(
                        "VIDEO BELOW!",
                        fontSize = superHeaderSize,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5B0C1C)
                    )
                    Text(
                        "The most important thing to keep in mind during this entire process is that you must remain at the scene, at least until police arrive. There could be legal repercussions if you leave. Turn hazard lights on (if necessary) to warn passerby drivers, and plan to stay there for a little bit.\n",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5B0C1C),
                        fontSize = textSize
                    )

                    Text(
                        "1. Check for injuries.",
                        fontSize = headerSize,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5B0C1C)
                    )
                    Text(
                        "Assess everyone in your own vehicle for wellness. If someone is seriously injured, do not move them. Call 911 if anyone is injured, no matter how minor it may appear. Small injuries can become huge issues if left untreated. Do not take any risks.\n",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5B0C1C),
                        fontSize = textSize
                    )

                    Text(
                        "2. Move your vehicle to a safe location.",
                        fontSize = headerSize,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5B0C1C)
                    )
                    Text(
                        "If possible, move your vehicle out of the way of traffic if it isn’t already. Move to the shoulder of the road or towards the nearest curb. Get out of your car and go to the side furthest from the road or stay in your car if it isn’t safe to exit. If there’s broken glass, don’t touch it.\n",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5B0C1C),
                        fontSize = textSize
                    )

                    Text(
                        "3. Call the police.",
                        fontSize = headerSize,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5B0C1C)
                    )
                    Text(
                        "A police report can be critical for your insurance claims (especially if there are injuries or significant damage - over \$2000), and it’s also just helpful, as the police will document the accident and be able to affirm that you were not at fault. Use their non-emergency line if you did not need to previously call 911 (aka. This isn’t an emergency.)\n",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5B0C1C),
                        fontSize = textSize
                    )

                    Text(
                        "3.a.",
                        fontSize = headerSize,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5B0C1C)
                    )
                    Text(
                        "However, if the damage is not severe, you don’t necessarily have to call the police, and can instead report the accident to the nearest CRC (Collision Report Centre). The CRC is a Police and Private facility that assists motorists in reporting motor vehicle collisions to the Police.\n",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5B0C1C),
                        fontSize = textSize
                    )

                    Text(
                        "3.b.",
                        fontSize = headerSize,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5B0C1C)
                    )
                    Text(
                        "If you do choose to go this route, take or have your vehicle taken to the CRC most convenient to you. You should attend with your vehicle. Without exception, vehicles that have to be towed must go directly to the CRC from the collision location.\n",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5B0C1C),
                        fontSize = textSize
                    )

                    Text(
                        "3.c.",
                        fontSize = headerSize,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5B0C1C)
                    )
                    Text(
                        "Bring your documentation with you to the CRC; Driver's licence, ownership, insurance, details of the accident and other involved parties information. Remember that by law, you must contact the CRC within twenty-four hours of the accident.\n",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5B0C1C),
                        fontSize = textSize
                    )

                    Text(
                        "4. Exchange information with the other party.",
                        fontSize = headerSize,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5B0C1C)
                    )
                    Text(
                        "Get their contact information (phone number, email, home address), full name, and insurance details (insurance company name, insurance company number). You should also note their license plate number and their drivers license information (home address, drivers license number). If there are witnesses, get their contact information (full name, phone number) as well, for future use.\n",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5B0C1C),
                        fontSize = textSize
                    )

                    Text(
                        "5. Document the scene.",
                        fontSize = headerSize,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5B0C1C)
                    )
                    Text(
                        "Take photos and videos of the damage done to all vehicles involved, the accident scene, and any relevant road conditions. Don’t let a single thing go unphotographed - this can be valuable for any future insurance claims. Record the time, location, and speed of your vehicle (approximately if not already recorded).\n",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5B0C1C),
                        fontSize = textSize
                    )

                    Text(
                        "6. See a medical professional immediately",
                        fontSize = headerSize,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5B0C1C)
                    )
                    Text(
                        "after being rear-ended will ensure all injuries will be documented and treated. Some injuries, like whiplash, might not be immediately apparent. If you feel “off” (aches, pains) at any point after the incident, be sure to seek medical treatment then.\n",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5B0C1C),
                        fontSize = textSize
                    )

                    Text(
                        "7. These next bits are optional, if you have insurance.",
                        fontSize = headerSize,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5B0C1C)
                    )
                    Text(
                        "Report the accident to your insurance. Provide them with the documentation you gathered at the accident site and the police report number. Also provide them with any relevant medical reports.\n",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5B0C1C),
                        fontSize = textSize
                    )

                    Text(
                        "8. Consult a lawyer if needed.",
                        fontSize = headerSize,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5B0C1C)
                    )
                    Text(
                        "If you face challenges with your insurance claim or believe you need additional compensation for injuries, consider seeking legal advice. A lawyer can help ensure you receive fair treatment from insurance companies.",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5B0C1C),
                        fontSize = textSize
                    )
                }
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
                .fillMaxWidth()
                .padding(vertical = 64.dp)
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