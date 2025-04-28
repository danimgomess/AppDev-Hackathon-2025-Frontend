package com.example.appdevhackthon2025.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appdevhackthon2025.R
import com.example.appdevhackthon2025.ui.components.HorizontalLine

@Composable fun HomeScreen(
    navController: NavHostController
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item {
            MainHeader()

            HorizontalLine()

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal=100.dp, vertical = 20.dp),
                onClick = { navController.navigate("itemScreen") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.hsl(0f, 0.74f, 0.40f),
                    contentColor = Color.White )
            ){
                Text(
                    text = "Submit New Item",
                    modifier = Modifier
                        .padding(8.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            HorizontalLine()

            Text(
                text = "Current Listings:",
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                modifier = Modifier.fillMaxWidth()
                    .padding(top=25.dp)
            )


            //HorizontalLine()
        }
    }
}

@Composable
private fun MainHeader() {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = CenterVertically,
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 15.dp, vertical = 16.dp)
    ) {
        Spacer(Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.cornell_university_logo),
            contentDescription = "logo",
            modifier = Modifier.size(110.dp)
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = "Cornell Lost and Found",
            fontSize = 48.sp,
            lineHeight = 42.sp        )
        Spacer(Modifier.weight(1f))
    }
}

@Preview
@Composable
fun MainHeaderPreview() {
    MainHeader()
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}