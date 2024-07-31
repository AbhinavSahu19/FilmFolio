package com.example.moviez.presentation.signIn

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviez.BuildConfig
import com.example.moviez.R
import com.example.moviez.presentation.commons.GeneralBottomBar
import com.example.moviez.presentation.commons.GeneralTopBar
import com.example.moviez.utils.showToast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SignInPage(
    signInViewModel: SignInViewModel = hiltViewModel(),
    navigateToMain: ()->Unit
) {
    val context = LocalContext.current
    val status by signInViewModel.status.collectAsState()

    val googleSignInClient = getGoogleSignInClient(context)
    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            account?.idToken?.let { idToken ->
                signInViewModel.signInWithGoogle(idToken)
            }
        } catch (e: ApiException) {
            Log.d("User e", e.message ?: "Unknown Error")
            showToast(context, "Error" + e.message, Toast.LENGTH_LONG)
        }
    }
    var isLoading by remember {
        mutableStateOf(false)
    }
   when(status){
       true -> {
           showToast(context, "Signed in successfully", Toast.LENGTH_SHORT)
           navigateToMain()
           isLoading = false
           signInViewModel.resetStatus()
       }
       false -> {}
   }

    Scaffold(
        topBar = { GeneralTopBar() },
        bottomBar = { GeneralBottomBar() }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.bg_gray)),
                horizontalArrangement = Arrangement.End
            ){
                Text(text = "Skip Now",
                    modifier = Modifier
                        .padding(top = 10.dp, end = 20.dp)
                        .clickable {
                            navigateToMain()
                        },
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    color = colorResource(id = R.color.text))
            }
            Column (
                Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.bg_gray)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Spacer(modifier = Modifier.height(150.dp))
                Text(text = "Welcome",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = stringResource(id = R.string.sign_in_page),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(5.dp))
                Button(onClick = {
                    isLoading = true
                    googleSignInClient.signOut().addOnCompleteListener {
                        googleSignInLauncher.launch(googleSignInClient.signInIntent)
                    }.addOnFailureListener {
                        isLoading = false
                        Log.d("Exception", it.message ?: "Unknown Error")
                        showToast(context, it.message ?: "Unknown Error", Toast.LENGTH_LONG)
                    }
                },
                    modifier = Modifier
                        .width(270.dp)
                        .height(70.dp)
                        .padding(top = 10.dp, bottom = 20.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.white)
                    )
                ) {
                    Row (
                        Modifier.fillMaxWidth(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Absolute.SpaceAround
                    ){
                        Image(painter = painterResource(id = R.drawable.google_icon),
                            contentDescription = "",
                            modifier = Modifier.size(30.dp))
                        Text(text = "Sign In with Google",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = colorResource(id = R.color.main),
                            modifier =  Modifier.padding(start = 10.dp))
                    }
                }
                if(isLoading){
                    CircularProgressIndicator(modifier = Modifier
                        .width(50.dp) ,
                        color = colorResource(id = R.color.main),
                        trackColor = colorResource(id = R.color.white),)
                }
            }
        }
    }
}

fun getGoogleSignInClient(context: Context): GoogleSignInClient {
    val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(BuildConfig.DEFAULT_WEB_CLIENT_ID)
        .requestEmail()
        .build()
    return GoogleSignIn.getClient(context, googleSignInOptions)
}

@Composable
@Preview
fun P(){

}