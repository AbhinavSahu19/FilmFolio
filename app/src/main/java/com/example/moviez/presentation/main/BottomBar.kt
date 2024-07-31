package com.example.moviez.presentation.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviez.R

@Composable
fun BottomBar(
    option: Screen,
    onclick: (Screen) -> Unit
) {
    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .shadow(40.dp, spotColor = colorResource(id = R.color.black)),
        backgroundColor = colorResource(id = R.color.white)
    ) {
        BottomNavigationItem(
            icon = {
                Column (
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Icon(painterResource(id = R.drawable.home_icon,),contentDescription = "")
                    Text(text = "Home",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium)
                }
            },
            selectedContentColor = colorResource(id = R.color.main),
            unselectedContentColor = colorResource(id = R.color.unselected_gray),
            selected = option == Screen.Home,
            onClick = { onclick(Screen.Home) }
        )
        BottomNavigationItem(
            icon = {
                Column (
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Icon(painterResource(id = R.drawable.genre_icon,),contentDescription = "")
                    Text(text = "Genres",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium)
                }
            },
            selectedContentColor = colorResource(id = R.color.main),
            unselectedContentColor = colorResource(id = R.color.unselected_gray),
            selected = option == Screen.Genres,
            onClick = { onclick(Screen.Genres) }
        )
        BottomNavigationItem(
            icon = {
                Column (
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Icon(painterResource(id = R.drawable.movie_icon,),contentDescription = "")
                    Text(text = "Movies",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium)
                }
            },
            selected = option == Screen.Movies,
            selectedContentColor = colorResource(id = R.color.main),
            unselectedContentColor = colorResource(id = R.color.unselected_gray),
            onClick = { onclick(Screen.Movies) }
        )
        BottomNavigationItem(
            icon = {
                Column (
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Icon(painterResource(id = R.drawable.tv_icon,),contentDescription = "")
                    Text(text = "TV Shows",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium)
                }
            },
            selectedContentColor = colorResource(id = R.color.main),
            unselectedContentColor = colorResource(id = R.color.unselected_gray),
            selected = option == Screen.Series,
            onClick = { onclick(Screen.Series) }
        )
        BottomNavigationItem(
            icon = {
                Column (
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Icon(painterResource(id = R.drawable.setting_icon,),contentDescription = "")
                    Text(text = "Settings",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium)
                }
            },            selectedContentColor = colorResource(id = R.color.main),
            unselectedContentColor = colorResource(id = R.color.unselected_gray),
            selected = option == Screen.Settings,
            onClick = { onclick(Screen.Settings) }
        )
    }
}

@Composable
@Preview
fun BottomBarPreview(){
    BottomBar(
        option = Screen.Movies,
        onclick = {}
    )
}