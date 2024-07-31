package com.example.moviez.presentation.commons

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.moviez.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun AddListDialog(
    onDismiss: ()-> Unit,
    onAdd:(String) -> Unit,
    lists: List<String>
){
    val focusRequester = remember {
        FocusRequester()
    }
    LaunchedEffect(focusRequester) {
        focusRequester.requestFocus()
    }
    var listName by remember {
        mutableStateOf("")
    }
    var enabled by remember {
        mutableStateOf(listName != "")
    }
    val buttonColor = if(enabled) colorResource(id = R.color.main)
                        else colorResource(id = R.color.disabled_color)
    val textColor = if(enabled) colorResource(id = R.color.white)
    else colorResource(id = R.color.unselected_gray)
    AlertDialog(
        containerColor = colorResource(id = R.color.bg_gray),
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Row {
                Button(onClick = {onDismiss()},
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.bg_gray)
                    ),
                    border = BorderStroke(0.dp, colorResource(id = R.color.bg_gray)),
                    elevation = ButtonDefaults.elevation(0.dp)
                ) {
                    Text(text = "Cancel",
                        color = colorResource(id = R.color.unselected_gray))
                }
                Button(onClick = {onAdd(listName)
                                 onDismiss()},
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = buttonColor
                    ),
                    enabled = enabled) {
                    Text(text = "Add List",
                        color = textColor)
                }
            }
        },
        text = {
            Column {
                Card(
                    modifier = Modifier
                        .height(40.dp)
                        .background(colorResource(id = R.color.white), RoundedCornerShape(4.dp))
                        .border(1.dp, colorResource(id = R.color.main), RoundedCornerShape(4.dp)),
                    shape = RoundedCornerShape(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = colorResource(id = R.color.white)
                    )
                ) {
                    BasicTextField(
                        value = listName,
                        onValueChange = {
                            listName = it
                            enabled = (listName != "")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 10.dp)
                            .focusRequester(focusRequester),
                        textStyle = TextStyle(color = colorResource(id = R.color.text),
                            fontSize = 20.sp),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done,
//                        autoCorrect = false,
                            capitalization = KeyboardCapitalization.Sentences
                        )
                    )
                }
                if(lists.contains(listName)){
                    Text(text = "** This list name already exist...",
                        color = colorResource(id = R.color.unselected_gray))
                    enabled = !(lists.contains(listName)) && listName != ""
                }
                else{
                    enabled = !(lists.contains(listName)) && listName != ""
                }
            }
        }
    )
}

@Composable
@Preview
fun AddListDialogPreview(){
    AddListDialog(
        onDismiss = {},
        onAdd = {},
        lists = listOf("Fav", "Watch")
    )
}