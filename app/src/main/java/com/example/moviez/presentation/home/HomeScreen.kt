package com.example.moviez.presentation.home

import android.annotation.SuppressLint
import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviez.R
import com.example.moviez.dataModels.MovieListItem
import com.example.moviez.dataModels.MovieListResponse
import com.example.moviez.dataModels.SeriesListItem
import com.example.moviez.dataModels.SeriesListResponse
import com.example.moviez.presentation.Error.ErrorScreen
import com.example.moviez.presentation.Loading.LoadingScreen
import com.example.moviez.presentation.commons.AddListDialog
import com.example.moviez.presentation.commons.itemCards.ExtraLargeItemCard
import com.example.moviez.presentation.commons.itemCards.MidItemCard
import com.example.moviez.repositary.realtimeDatabase.StorageModel
import com.example.moviez.utils.ResponseModel
import com.example.moviez.utils.showToast
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "MutableCollectionMutableState")
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateToMovieDetails: (Int) -> Unit,
    navigateTosSeriesDetails: (Int) -> Unit,
    navigateToStoredList: (String)->Unit,
    navigateToSignIn: ()-> Unit
) {
    val activity = (LocalContext.current as? Activity)
    BackHandler {
        activity?.finish()
    }

    val movieListResponse by homeViewModel.trendingMovies.collectAsState()
    val seriesListResponse by homeViewModel.trendingSeries.collectAsState()
    val storedData by homeViewModel.storedData.collectAsState()
    val listsData by homeViewModel.listStatus.collectAsState()
    val addListStatus by homeViewModel.addListStatus.collectAsState()
    val deleteListStatus by homeViewModel.deleteListStatus.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        when (seriesListResponse) {
            is ResponseModel.Error -> {}
            ResponseModel.Loading -> {}
            is ResponseModel.Success -> {}
        }
    }
    var nameList = remember {
        mutableStateListOf<String>()
    }
    var dialogToAddList by remember {
        mutableStateOf(false)
    }
    var isSignedIn by remember {
        mutableStateOf(false)
    }
    var userId by remember {
        mutableStateOf("false")
    }
    LaunchedEffect(Unit) {
        isSignedIn = Firebase.auth.currentUser != null
        if (isSignedIn) {
            userId = Firebase.auth.currentUser!!.uid
            homeViewModel.getStoredData(userId)
            when (listsData) {
                is ResponseModel.Error -> {
                    showToast(
                        context,
                        (listsData as ResponseModel.Error).errorMsg,
                        Toast.LENGTH_LONG
                    )
                }

                ResponseModel.Loading -> {}

                is ResponseModel.Success -> {
                    nameList.clear()
                    nameList.addAll((listsData as ResponseModel.Success<List<String>>).data)
                }
            }
            if(!(nameList.contains("Favourites") && nameList.contains("To Watch"))){
                homeViewModel.addList(userId, "Favourites")
                homeViewModel.addList(userId, "To Watch")
                homeViewModel.resetAddListStatus()
            }
        }
    }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.bg_gray))
        ) {
            when (movieListResponse) {
                is ResponseModel.Error -> {
                    ErrorScreen(
                        (movieListResponse as ResponseModel.Error).errorMsg,
                        onReload = homeViewModel::load
                    )
                }

                ResponseModel.Loading -> {
                    LoadingScreen()
                }

                is ResponseModel.Success -> {}
            }
            LazyColumn (
                modifier = Modifier.fillMaxSize()
            ){
                item {
                    if (movieListResponse is ResponseModel.Success && seriesListResponse is ResponseModel.Success) {
                        HomeTrendingDisplay(
                            seriesList = (seriesListResponse as ResponseModel.Success<SeriesListResponse>).data.results,
                            movieList = (movieListResponse as ResponseModel.Success<MovieListResponse>).data.results,
                            onMovieClick = navigateToMovieDetails,
                            onSeriesClick = navigateTosSeriesDetails
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
                item {
                    if (isSignedIn) {
                        when (storedData) {
                            is ResponseModel.Error -> {
                                HomeStoredDisplay(msg = (storedData as ResponseModel.Error).errorMsg,
                                    buttonText = "Reload",
                                    onClick = { homeViewModel.getStoredData(userId) }
                                )
                            }

                            ResponseModel.Loading -> {
//                    HalfLoadingScreen()
                            }

                            is ResponseModel.Success -> {
                                homeViewModel.getLists(userId)
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 5.dp)
                                ) {
                                    Text(text = "My Lists",
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(start = 10.dp),
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = colorResource(id = R.color.text))
                                    Icon(painter = painterResource(id = R.drawable.add_icon),
                                        contentDescription = "",
                                        Modifier
                                            .padding(end = 20.dp)
                                            .size(24.dp)
                                            .clickable {
                                                dialogToAddList = true
                                            })
                                }
                                HomeStoredDataDisplay(
                                    allData = (storedData as ResponseModel.Success<List<StorageModel>>).data,
                                    onMovieClick = navigateToMovieDetails,
                                    onSeriesClick = navigateTosSeriesDetails,
                                    deleteList = {homeViewModel.deleteList(userId, it)},
                                    navigateToStoredList = navigateToStoredList
                                )
                                Spacer(modifier = Modifier.height(80.dp))
                            }
                        }
                    } else {
                        HomeStoredDisplay(
                            msg = stringResource(id = R.string.not_signed_in),
                            buttonText = "Sign In",
                            onClick = { navigateToSignIn() }
                        )

                    }
                }
            }
        }
    when(deleteListStatus){
        is ResponseModel.Error ->{ showToast(context, (deleteListStatus as ResponseModel.Error).errorMsg, Toast.LENGTH_LONG)
        }
        ResponseModel.Loading ->{}
        is ResponseModel.Success -> {
            showToast(context, "List Deleted", Toast.LENGTH_SHORT)
            homeViewModel.getStoredData(userId)
        }
    }
    if(dialogToAddList){
        AddListDialog(
            onDismiss = { dialogToAddList = false},
            onAdd = { homeViewModel.addList(userId, it)
                    when(addListStatus){
                        is ResponseModel.Error -> {
                            showToast(context, (addListStatus as ResponseModel.Error).errorMsg, Toast.LENGTH_LONG)
                        }
                        ResponseModel.Loading -> {}
                        is ResponseModel.Success -> {
                            showToast(context, "List Added Successfully", Toast.LENGTH_SHORT)
                            homeViewModel.getLists(userId)
                            dialogToAddList = false
                        }
                    } },
            lists = nameList
        )
    }
}



@Composable
fun HomeStoredDataDisplay(
    allData: List<StorageModel>,
    onMovieClick: (Int) -> Unit,
    onSeriesClick:  (Int)-> Unit,
    deleteList : (String) -> Unit,
    navigateToStoredList: (String) -> Unit
) {
    Column (
        modifier = Modifier.fillMaxWidth(),
    ){
        for(stModel in allData){
            var dropdown by remember {
                mutableStateOf(false)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.bg_gray))
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stModel.name,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Medium,
                        color = colorResource(id = R.color.text),
                        modifier = Modifier.padding(12.dp, 6.dp))
                    Box(modifier = Modifier
                        .background(colorResource(id = R.color.bg_gray))
                        .padding(10.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.three_dots),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .size(20.dp)
                                .clickable {
                                    dropdown = !dropdown
                                })
                        DropdownMenu(expanded = dropdown,
                            onDismissRequest = {
                                dropdown = false
                            },) {
                            DropdownMenuItem(text = { Text(text = "View") },
                                onClick = {
                                    dropdown = false
                                    navigateToStoredList(stModel.name)
                                })
                            DropdownMenuItem(text = { Text(text = "Delete") },
                                onClick = { deleteList(stModel.name)
                                          dropdown = false},
                                enabled = !(stModel.name == "Favourites" || stModel.name == "To Watch"))
                        }
                    }
                }
                if(stModel.list.isNotEmpty()){
                    LazyRow (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp, bottom = 6.dp)
                    ){
                        items(stModel.list){item->
                            MidItemCard(
                                posterPath = item.posterPath,
                                onItemClick = {
                                    if(item.type == "m")onMovieClick(item.id)
                                    else onSeriesClick(item.id)
                                },
                                itemId = item.id,
                                itemType = item.type
                            )
                        }
                    }
                }
                else{
                    Text(text = stringResource(id = R.string.no_item_found),
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 30.dp, top = 3.dp, bottom = 10.dp),
                        color = colorResource(id = R.color.unselected_gray))
                }
            }
        }
    }
}


@Composable
fun HomeStoredDisplay(
    msg: String,
    buttonText: String,
    onClick:()->Unit
){
    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = msg,
            fontSize = 20.sp)
        Button(
            onClick = {onClick() },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.main)
            ),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier
                .padding(10.dp, 7.dp)
        ) {
            Text(
                text = buttonText,
                fontSize = 17.sp,
                color = colorResource(id = R.color.white),
                modifier = Modifier.padding(15.dp, 4.dp)
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeTrendingDisplay(
    movieList: List<MovieListItem>,
    seriesList: List<SeriesListItem>,
    onSeriesClick: (Int) -> Unit,
    onMovieClick: (Int) -> Unit
){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .background(colorResource(id = R.color.bg_gray)),
    ){
       Text(text = "Trending Today",
           fontSize = 24.sp,
           fontWeight = FontWeight.Medium,
           textAlign = TextAlign.Left,
           modifier = Modifier.padding(15.dp, 7.dp))
        val pagerState = rememberPagerState()
        Column (
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            HorizontalPager(count = Math.min(9, seriesList.size),
                state = pagerState,
            ) {page->
                var id:Int;
                var posterPath: String;
                if(page%2 == 0) {
                    id = movieList[page].id
                    posterPath = movieList[page].posterPath
                }
                else{
                    id = seriesList[page].id
                    posterPath = seriesList[page].posterPath
                }

                ExtraLargeItemCard(onItemClick = {
                    if(page%2 == 0)onMovieClick(id)
                    else onSeriesClick(id)
                },
                    posterPath = posterPath)
            }
            Row(
                Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .height(15.dp)
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val color = if (pagerState.currentPage == iteration) colorResource(id = R.color.main) else colorResource(
                        id = R.color.disabled_color
                    )
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(10.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
    }
}
