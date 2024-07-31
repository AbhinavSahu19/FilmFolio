package com.example.moviez.presentation.commons.bottomAction

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviez.R
import com.example.moviez.utils.isInternetAvailable
import com.example.moviez.presentation.Error.HalfErrorScreen
import com.example.moviez.presentation.Loading.HalfLoadingScreen
import com.example.moviez.presentation.commons.AddListDialog
import com.example.moviez.repositary.realtimeDatabase.StorageItem
import com.example.moviez.utils.ResponseModel
import com.example.moviez.utils.showToast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomActionSheet(
    bottomActionViewModel: BottomActionViewModel = hiltViewModel(),
    onDismiss: ()-> Unit,
    itemType: String,
    itemId: Int,
    posterPath: String
){
    val listStatus by bottomActionViewModel.listStatus.collectAsState()
    val addListStatus by bottomActionViewModel.addListStatus.collectAsState()
    val addItemStatus by bottomActionViewModel.addItemStatus.collectAsState()
    val deleteItemStatus by bottomActionViewModel.deleteItemStatus.collectAsState()
    val containItemStatusMap by bottomActionViewModel.containItemStatusMap.collectAsState()

    var isDialogOpen by remember {
        mutableStateOf(false)
    }
    val lists = remember { mutableStateListOf<String>() }
    val context = LocalContext.current

    val sheetState = rememberModalBottomSheetState()
    var isInternetAvailable by remember {
        mutableStateOf(false)
    }
    var isSignedIn by remember {
        mutableStateOf(false)
    }
    var userId by remember {
        mutableStateOf("")
    }
    LaunchedEffect(Unit) {
        isInternetAvailable = isInternetAvailable(context)
    }
    LaunchedEffect(isInternetAvailable(context)) {
        isSignedIn = Firebase.auth.currentUser != null
        if(isSignedIn){
            userId = Firebase.auth.currentUser!!.uid
//            if(!lists.contains("To Watch") && !lists.contains("Favourites")){
//            }
            bottomActionViewModel.getLists(userId)
        }
    }
    LaunchedEffect(addListStatus) {
        when(addListStatus){
            is ResponseModel.Error -> {
                showToast(context, (addListStatus as ResponseModel.Error).errorMsg, Toast.LENGTH_LONG)
            }
            ResponseModel.Loading -> {}
            is ResponseModel.Success -> {
                showToast(context, "List added Successfully", Toast.LENGTH_SHORT)
            }
        }
    }
    ModalBottomSheet(onDismissRequest = {onDismiss()},
        sheetState = sheetState,
        containerColor = colorResource(id = R.color.bg_gray),
        dragHandle = { BottomSheetDefaults.DragHandle() },
        scrimColor = Color.Black.copy(alpha = .2f),
        windowInsets = WindowInsets(0, 0, 0, 0)
        ) {
        if(isInternetAvailable){
            if(isSignedIn){
                when(listStatus){
                    is ResponseModel.Error -> {
                        HalfErrorScreen(errorMsg = (listStatus as ResponseModel.Error).errorMsg,
                            onReload = { Firebase.auth.currentUser?.let { bottomActionViewModel.getLists(it.uid) } })
                    }
                    ResponseModel.Loading -> {
                        HalfLoadingScreen()
                    }
                    is ResponseModel.Success -> {
                        lists.clear()
                        lists.addAll((listStatus as ResponseModel.Success<List<String>>).data)
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row (
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Text("Add to",
                                    Modifier
                                        .weight(1f)
                                        .padding(start = 20.dp, top = 10.dp, bottom = 10.dp),
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 21.sp
                                )
                                Image(painter = painterResource(id = R.drawable.add_icon),
                                    contentDescription = "add_icon",
                                    modifier = Modifier
                                        .padding(end = 15.dp)
                                        .clickable { isDialogOpen = true })

                            }
                            LazyColumn (
                                Modifier.padding(20.dp)
                            ){
                                items (lists) {list->
                                    bottomActionViewModel.containsItem(userId, list, StorageItem(itemType, itemId, posterPath))

                                    containItemStatusMap[list]?.let {
                                        BottomSheetSuccess(
                                            listName = list,
                                            contains = it,
                                            addItemStatus = addItemStatus,
                                            deleteItemStatus = deleteItemStatus,
                                            onChange = { isChecked ->
                                                if (isChecked) {
                                                    bottomActionViewModel.addItem(
                                                        userId,
                                                        list,
                                                        StorageItem(itemType, itemId, posterPath)
                                                    )
                                                } else {
                                                    bottomActionViewModel.deleteItem(
                                                        userId,
                                                        list,
                                                        StorageItem(itemType, itemId, posterPath)
                                                    )
                                                }
                                            },
                                            onAddItemHandled = { bottomActionViewModel.resetAddItemStatus() },
                                            onDeleteItemHandled = { bottomActionViewModel.resetDeleteItemStatus() }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else{
                NotSignInSheet()
            }
        }
        else{
            HalfErrorScreen(errorMsg = "Internet Not Connected",
                onReload = {isInternetAvailable = isInternetAvailable(context) })
        }
    }
    if(isDialogOpen){
        AddListDialog(onDismiss = { isDialogOpen = false },
            onAdd = { bottomActionViewModel.addList(userId, it)},
            lists)
    }

}


@Composable
fun BottomSheetSuccess(
    listName: String,
    contains: ResponseModel<Boolean>,
    addItemStatus: ResponseModel<String>,
    deleteItemStatus: ResponseModel<String>,
    onChange: (Boolean) ->Unit,
    onAddItemHandled: () -> Unit,
    onDeleteItemHandled: () -> Unit
){
    val context = LocalContext.current
    var checked by remember {
        mutableStateOf(false)
    }
    var isLoading by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .height(35.dp),
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(
            colorResource(id = R.color.white)
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = listName,
                fontSize = 17.sp,
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .weight(1f)
            )
            when (addItemStatus) {
                is ResponseModel.Error -> {
                    if (addItemStatus.errorMsg != "Not Started") {
                        showToast(context, addItemStatus.errorMsg, Toast.LENGTH_LONG)
                    }
                    isLoading = false
                    onAddItemHandled()
                }

                ResponseModel.Loading -> {
                    isLoading = true
                }

                is ResponseModel.Success -> {
                    isLoading = false
//                    showToast(context, "Item Added Successfully", Toast.LENGTH_SHORT)
                    onAddItemHandled()
                }
            }

            when (contains) {
                is ResponseModel.Error -> {
                        showToast(context, contains.errorMsg, Toast.LENGTH_LONG)
                        isLoading = false
                    }

                    ResponseModel.Loading -> {
                        isLoading = true
                    }

                    is ResponseModel.Success -> {
                        if (checked != contains.data) {
                            checked = contains.data
                        }
                        isLoading = false
                    }
                }
            when (deleteItemStatus) {
                is ResponseModel.Error -> {
                    if (deleteItemStatus.errorMsg != "Not Started") {
                        showToast(context, deleteItemStatus.errorMsg, Toast.LENGTH_LONG)
                    }
                    isLoading = false
                    onDeleteItemHandled()
                }

                ResponseModel.Loading -> {
                    isLoading = true
                }

                is ResponseModel.Success -> {
                    isLoading = false
//                    showToast(context, "Item Removed Successfully", Toast.LENGTH_SHORT)
                    onDeleteItemHandled()
                }
            }

            if (isLoading) {
                CircularProgressIndicator(
                    Modifier
                        .height(20.dp)
                        .width(50.dp)
                        .padding(horizontal = 15.dp),
                    trackColor = colorResource(id = R.color.bg_gray),
                    color = colorResource(id = R.color.main)
                )
            }
            Checkbox(
                checked = checked,
                onCheckedChange = { isChecked ->
                    if (isChecked != checked) {
                        checked = isChecked
                        onChange(isChecked)
                    }
                })
        }
    }
}
@Composable
@Preview
fun BottomSheetSuccessPreview(){
    BottomSheetSuccess(
        listName = "Fav",
        contains = ResponseModel.Success(false),
        addItemStatus = ResponseModel.Loading,
        deleteItemStatus = ResponseModel.Loading,
        onChange = {},
        onDeleteItemHandled = {},
        onAddItemHandled = {}
    )
}

@Composable
fun NotSignInSheet(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .background(color = colorResource(id = R.color.bg_gray))
            .padding(15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.sad_icon),
            contentDescription = "sad_icon",
            Modifier
                .size(60.dp)
                .padding(10.dp))
        Text(text = stringResource(id = R.string.not_signed_in),
            fontSize = 20.sp)
        Text(text = "Kindly Sign In",
            fontSize = 20.sp)
        Text(text = "Setting -> Sign In",
            fontSize = 17.sp)
    }
}
