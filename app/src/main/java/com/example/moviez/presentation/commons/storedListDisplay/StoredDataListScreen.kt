package com.example.moviez.presentation.commons.storedListDisplay

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviez.R
import com.example.moviez.presentation.Error.ErrorScreen
import com.example.moviez.presentation.Loading.LoadingScreen
import com.example.moviez.presentation.commons.GeneralBottomBar
import com.example.moviez.presentation.commons.GeneralTopBar
import com.example.moviez.presentation.commons.itemCards.ItemDisplayCard
import com.example.moviez.utils.ResponseModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StoredDataListScreen(
    viewModel: StoredListViewModel = hiltViewModel(),
    navigateToMovieDetails: (Int) -> Unit,
    navigateToSeriesDetails: (Int) -> Unit
) {
    val dataStatus by viewModel.dataResponse.collectAsState()

    var userId by remember {
        mutableStateOf<String>("")
    }
    LaunchedEffect(Unit) {
        Firebase.auth.currentUser?.let { user ->
            userId = user.uid
            viewModel.getData(userId)
        }
    }
    Scaffold(
        topBar = { GeneralTopBar() },
        bottomBar = { GeneralBottomBar() }
    ) { paddingValues ->
        when (dataStatus) {
            is ResponseModel.Error -> {
                ErrorScreen(
                    errorMsg = (dataStatus as ResponseModel.Error).errorMsg,
                    onReload = { viewModel.getData(userId) }
                )
            }
            ResponseModel.Loading -> {
                LoadingScreen()
            }
            is ResponseModel.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 15.dp)
                        .background(colorResource(id = R.color.bg_gray)),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LazyColumn(
                        modifier = Modifier.weight(1f)
                    ) {
                        val dataList = (dataStatus as ResponseModel.Success).data.list
                        items(dataList.chunked(2)) { pair ->
                            Row {
                                pair.forEach { item ->
                                    ItemDisplayCard(
                                        posterPath = item.posterPath,
                                        onItemClick = {
                                            if (item.type == "m") {
                                                navigateToMovieDetails(item.id)
                                            } else {
                                                navigateToSeriesDetails(item.id)
                                            }
                                        },
                                        itemId = item.id,
                                        itemType = item.type
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
