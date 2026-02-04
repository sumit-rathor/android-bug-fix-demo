package com.example.buggedapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buggedapp.ui.theme.BuggedAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold (
                modifier = Modifier.fillMaxSize(),
//                backgro = Color(0xFFF6F6F6)
            ) { innerPadding ->
                ProfileFlow(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Composable
fun ProfileFlow(modifier: Modifier = Modifier) {

    var editing by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {

        AnimatedVisibility(
            visible = !editing,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            ProfileScreen(
                onEditClick = { editing = true }
            )
        }

        AnimatedVisibility(
            visible = editing,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            EditProfileScreen(
                onSave = { editing = false }
            )
        }
    }
}

/* ---------------- PROFILE SCREEN ---------------- */

@Preview
@Composable
fun ProfileScreenPreview() {
    BuggedAppTheme() {
        ProfileScreen {  }
    }
}
@Composable
fun ProfileScreen(onEditClick: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(18.dp))
            Text("Profile", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(24.dp))

        // Avatar
        Box(
            modifier = Modifier
                .size(90.dp)
                .background(Color(0xFFB39DDB), CircleShape)
                .align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center
        ) {
            Text("S", fontSize = 36.sp, color = Color.White)
        }

        Spacer(Modifier.height(12.dp))

        Text("Sumit Rathore", fontSize = 22.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.CenterHorizontally))

        Spacer(Modifier.height(12.dp))

        Button(
            onClick = onEditClick,
            shape = RoundedCornerShape(50),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Edit Profile")
        }

        Spacer(Modifier.height(24.dp))

        Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                StatCard("Calls", "7")
                StatCard("Friends", "0")
                StatCard("Time", "27 mins")
            }
        }

        Spacer(Modifier.height(24.dp))

        Text("Achievements", fontWeight = FontWeight.Bold)

        Spacer(Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
            Achievement("15\nMins")
            Achievement("3\nLikes")
            Achievement("3\nStreak")
        }
    }
}

@Composable
fun StatCard(title: String, value: String) {
    Card(
//        modifier = Modifier.weight(1f),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(value, fontWeight = FontWeight.Bold)
            Text(title, fontSize = 12.sp, color = Color.Gray)
        }
    }
}

@Composable
fun Achievement(text: String) {
    Box(
        modifier = Modifier
            .size(70.dp)
            .background(Color(0xFFE1BEE7), CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(text, fontSize = 12.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
    }
}

/* ---------------- EDIT PROFILE SCREEN ---------------- */

@Preview
@Composable
fun EditProfileScreenPreview() {
    BuggedAppTheme() {
        EditProfileScreen {  }
    }
}
@Composable
fun EditProfileScreen(onSave: () -> Unit) {

    var selectedAvatar by remember { mutableStateOf(0) }
    var mobileNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    val avatars = List(9) { it }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(18.dp))
            Text("Edit Profile", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.weight(1f))
            TextButton(onClick = {
                val number = mobileNumber.toLong()
                onSave()
            }) {
                Text("Save")
            }
        }

        Spacer(Modifier.height(20.dp))

/*
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.height(220.dp)
        ) {
            items(avatars) { index ->
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(
                            if (index == selectedAvatar) Color(0xFF81C784) else Color(
                                0xFFB39DDB
                            ),
                            CircleShape
                        )
                        .border(
                            2.dp,
                            if (index == selectedAvatar) Color.Black else Color.Transparent,
                            CircleShape
                        )
                        .clickable { selectedAvatar = index },
                    contentAlignment = Alignment.Center
                ) {
                    Text("S", fontSize = 24.sp, color = Color.White)
                }
            }
        }
*/

        Spacer(Modifier.height(24.dp))

        OutlinedTextField(
            value = "Sumit Rathore",
            onValueChange = {},
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))



        OutlinedTextField(
            value = mobileNumber,
            onValueChange = { mobileNumber = it },
            label = { Text("Contact") },
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
