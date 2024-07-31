package com.example.moviez.presentation.search

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviez.R
import com.example.moviez.dataModels.Keyword
import com.example.moviez.utils.isInternetAvailable
import com.example.moviez.presentation.Error.HalfErrorScreen
import com.example.moviez.presentation.commons.GeneralBottomBar
import com.example.moviez.presentation.commons.GeneralTopBar
import com.example.moviez.presentation.Loading.HalfLoadingScreen
import com.example.moviez.utils.ResponseModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel = hiltViewModel(),
    navigateToSearchResult: (String) -> Unit
) {
    val searchKeywordListResponse by searchViewModel.searchKeywordResponse.collectAsState()
    val storageKeywordResponse by searchViewModel.storageKeywordResponse.collectAsState()
    val addKeywordStatus by searchViewModel.addKeywordStatus.collectAsState()
    val deleteKeywordStatus by searchViewModel.deleteKeywordStatus.collectAsState()

    val query = remember {
        mutableStateOf(TextFieldValue(searchViewModel.query))
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        keyboardController?.show()
    }
    val context = LocalContext.current
    var isSignedIn by remember {
        mutableStateOf(false)
    }
    var userId by remember {
        mutableStateOf("")
    }

    var storageKeywordList by rememberSaveable {
        mutableStateOf(listOf<String>())
    }

//    LaunchedEffect(addKeywordStatus) {
//        addKeywordStatus?.let { status ->
//            showToast(context, status.toString(), Toast.LENGTH_LONG)
//        }
//    }

    LaunchedEffect(isInternetAvailable(context)) {
        isSignedIn = Firebase.auth.currentUser != null
        if(isSignedIn){
            userId = Firebase.auth.currentUser!!.uid
            searchViewModel.getStorageKeywords(userId)
        }
    }

    Scaffold(
        topBar = {
            GeneralTopBar()
        },
        bottomBar = {
            GeneralBottomBar()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.bg_gray))
        ) {
            SearchBar(
                query = query.value,
                onValueChange = {
                    query.value = it
                    searchViewModel.searchKeyword(it.text)
                },
                onSearch = {
                    if(isSignedIn && !(storageKeywordList.contains(query.value.text))) {
                        searchViewModel.addKeyword(userId, query.value.text)
                    }
//                    showToast(context, addKeywordStatus.toString(), Toast.LENGTH_LONG)
                    navigateToSearchResult(query.value.text)
                }
            )
            when(storageKeywordResponse){
                is ResponseModel.Error -> {}
                ResponseModel.Loading -> {}
                is ResponseModel.Success -> {
                    storageKeywordList = (storageKeywordResponse as ResponseModel.Success).data
                    SearchKeywordDisplay(
                        searchKeyword = StringToKeyword(Sortkeywords(storageKeywordList,query.value.text )),
                        onEnter = {
                            navigateToSearchResult(it)
                        },
                        onArrowClick = {
                            query.value = TextFieldValue(
                                text = it,
                                selection = TextRange(it.length)
                            )
                            searchViewModel.searchKeyword(it)
                        },
                        isSaved = true,
                        onRemove = { searchViewModel.deleteKeyword(userId, it)}
                    )
                }
            }
            when (searchKeywordListResponse) {
                is ResponseModel.Error -> {
                    HalfErrorScreen(
                        errorMsg = (searchKeywordListResponse as ResponseModel.Error).errorMsg,
                        onReload = { searchViewModel.searchKeyword(query.value.text) }
                    )
                }
                ResponseModel.Loading -> {
                    HalfLoadingScreen()
                }
                is ResponseModel.Success -> {
                    SearchKeywordDisplay(
                        searchKeyword = (searchKeywordListResponse as ResponseModel.Success).data.results,
                        onEnter = {
                            if(isSignedIn && storageKeywordList.contains(query.value.text))searchViewModel.addKeyword(userId, it)
//                            showToast(context, addKeywordStatus.toString(), Toast.LENGTH_LONG)
                            navigateToSearchResult(it)
                        },
                        onArrowClick = {
                            query.value = TextFieldValue(
                                text = it,
                                selection = TextRange(it.length)
                            )
                            searchViewModel.searchKeyword(it)
                        },
                        isSaved = false,
                        onRemove = {}
                    )
                }
            }
        }
    }
}

fun StringToKeyword(list: List<String>): List<Keyword> {
    val returnModel = mutableListOf<Keyword>()
    for(item in list){
        returnModel.add(Keyword(name = item))
    }
    return returnModel.reversed()
}

fun Sortkeywords(list: List<String>, st: String): List<String>{
    val updatedList = mutableListOf<String>()
    for(item in list){
        if(item.contains(st))updatedList.add(item)
    }
    return updatedList
}

