package com.example.moviez.presentation.settings

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
import com.example.moviez.utils.ResponseModel
import com.example.moviez.utils.showToast
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingScreen(
    settingViewModel: SettingsViewModel = hiltViewModel(),
    navigateToSignIn: ()-> Unit
){
    var isAuthenticated = remember {
        mutableStateOf(Firebase.auth.currentUser != null)
    }
    var userId = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val email = Firebase.auth.currentUser?.email
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = isAuthenticated) {
        isAuthenticated.value = Firebase.auth.currentUser != null
        if(isAuthenticated.value)userId.value = Firebase.auth.currentUser!!.uid
    }
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.bg_gray))
        ) {
            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .border(0.dp, colorResource(id = R.color.bg_gray)),
                shape = RoundedCornerShape(6.dp),
                backgroundColor = colorResource(id = R.color.white)
            ){
                if (isAuthenticated.value) {
                    Column (
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Text(
                            text = email ?: "Unable to get email",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(10.dp,7.dp)
                        )
                        Button(onClick = {
            coroutineScope.launch {
                withContext(Dispatchers.Main){ settingViewModel
                    .signOut()
                    .collect { response ->
                        when (response) {
                            is ResponseModel.Error -> {
                                showToast(
                                    context, response.errorMsg, Toast.LENGTH_LONG
                                )
                            }

                            ResponseModel.Loading -> {}
                            is ResponseModel.Success -> {
                                showToast(
                                    context, "Signed Out", Toast.LENGTH_SHORT
                                )
                                isAuthenticated.value =
                                    Firebase.auth.currentUser != null
                            }
                        }
                    }}
            }
                        },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = colorResource(id = R.color.disabled_color)
                            ),
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier
                                .padding(10.dp, 3.dp, 10.dp, 1.dp)
                        ) {
                            Text(text = "Sign Out",
                                fontSize = 15.sp,
                                color = colorResource(id = R.color.text),
                                modifier = Modifier.padding(5.dp, 3.dp)
                            )
                        }
                        Button(onClick = {
             coroutineScope.launch {
                withContext(Dispatchers.Main){ settingViewModel.deleteData(userId.value)
                    .collect{dataDelete->
                        when(dataDelete){
                            is ResponseModel.Error -> {
                                showToast(context, dataDelete.errorMsg, Toast.LENGTH_LONG)
                            }
                            ResponseModel.Loading -> {}
                            is ResponseModel.Success ->{
                               settingViewModel.delete()
                                    .collect { response ->
                                        when (response) {
                                            is ResponseModel.Error -> {
                                                showToast(
                                                    context, response.errorMsg, Toast.LENGTH_LONG
                                                )
                                            }

                                            ResponseModel.Loading -> {}
                                            is ResponseModel.Success -> {
                                                showToast(
                                                    context, "Account Deleted", Toast.LENGTH_SHORT
                                                )
                                                isAuthenticated.value = false
                                            }
                                        }
                                    }
                            }
                        }

                    }
                    }
            }
                        },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = colorResource(id = R.color.disabled_color)
                            ),
                            shape = RoundedCornerShape(6.dp,),
                            modifier = Modifier
                                .padding(10.dp, 3.dp, 10.dp, 8.dp)
                        ) {
                            Text(text = "Delete Account",
                                fontSize = 15.sp,
                                color = colorResource(id = R.color.text),
                                modifier = Modifier.padding(5.dp, 3.dp)
                            )
                        }
                    }
                } else {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = "You are not signed in.",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(15.dp, 7.dp)
                        )
                        Button(onClick = { navigateToSignIn() },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = colorResource(id = R.color.main)
                            ),
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier
                                .padding(10.dp, 7.dp)) {
                            Text(
                                text = "Sign In",
                                fontSize = 17.sp,
                                color = colorResource(id = R.color.white),
                                modifier = Modifier.padding(10.dp, 3.dp)
                            )
                        }
                    }
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .background(colorResource(id = R.color.white))
                    .border(0.dp, colorResource(id = R.color.bg_gray),)
            ) {
                Column {
                    Text(
                        text = "About",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(10.dp)
                    )
                    Text(text = stringResource(id = R.string.about),
                        modifier = Modifier.padding(10.dp, 0.dp, 10.dp, 10.dp),
                        fontSize = 17.sp,
                        lineHeight = 21.sp
                        )
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .background(colorResource(id = R.color.white))
                    .border(0.dp, colorResource(id = R.color.bg_gray),)
            ) {
                Column {
                    Text(
                        text = "Credits",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(10.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.img_1),
                        contentDescription = "credit_logo",
                        modifier = Modifier
                            .padding(10.dp)
                            .height(140.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Fit
                    )
                    Text(text = stringResource(id = R.string.credits),
                        modifier = Modifier.padding(10.dp),
                        fontSize = 14.sp)
                }
            }
        }
    }
}

