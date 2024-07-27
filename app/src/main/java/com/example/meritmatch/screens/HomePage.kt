package com.example.meritmatch.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meritmatch.MainViewModel
import com.example.meritmatch.R
import com.example.meritmatch.ui.theme.HomeScreenColor
import com.example.meritmatch.ui.theme.NavBarColor
import com.example.meritmatch.ui.theme.NewTaskColor
import com.example.meritmatch.ui.theme.CardColor
import com.example.meritmatch.ui.theme.archivoBold
import com.example.meritmatch.ui.theme.archivoRegular

@Composable
fun HomePage(navController: NavController, viewModel: MainViewModel) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(viewModel = viewModel)
        },
        containerColor = HomeScreenColor
    ) {
        //page content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp, start = 20.dp, end = 20.dp, bottom = 20.dp),
            verticalArrangement = Arrangement.Top
        ) {
            //title
            Column {
                Text(
                    text = "Reserved Tasks",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontFamily = archivoRegular
                )
                HorizontalDivider(
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 25.dp)
                        .fillMaxWidth(0.9f),
                    thickness = 2.dp
                )
            }
            //reserved tasks
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                LazyColumn() {
                    item {
                        TaskBox()
                    }
                }
            }

            Column(
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Text(
                    text = "Available Tasks",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontFamily = archivoRegular
                )
                HorizontalDivider(
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 25.dp)
                        .fillMaxWidth(0.9f),
                    thickness = 2.dp
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
//                    .background(color = Color.Blue)
            ) {
                LazyColumn {
                    item {
                        TaskBox()
                    }
                }
            }
        }

        //new task button
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.BottomEnd
        ) {
            Box(contentAlignment = Alignment.Center) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(10.dp)
                        .size(60.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    )
                ) { }
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "newtask",
                    modifier = Modifier.size(40.dp),
                    tint = NewTaskColor
                )
            }
        }
    }
}

@Composable
fun TaskBox() {
    Card(
        onClick = { /*TODO*/ },
        colors = CardDefaults.cardColors(
            containerColor = CardColor.copy(0.28f),
            contentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(text = "Title", fontFamily = archivoBold, fontSize = 20.sp, modifier = Modifier.padding(bottom = 5.dp))
            Text(text = "ojiefoiwjfoiw", fontFamily = archivoRegular)
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Text(text = "User123", fontFamily = archivoBold)
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.star_karma),
                    contentDescription = "pause symbol",
                    modifier = Modifier.size(15.dp)
                )
                Text(text = "550")
            }
        }
    }
}

@Preview
@Composable
private fun HomePagePreview() {
    HomePage(navController = rememberNavController(), viewModel = MainViewModel())
//    TaskBox()
}