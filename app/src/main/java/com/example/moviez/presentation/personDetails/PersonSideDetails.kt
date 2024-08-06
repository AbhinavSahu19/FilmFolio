package com.example.moviez.presentation.personDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviez.R
import com.example.moviez.presentation.commons.formatDateString
import com.example.moviez.presentation.commons.getAge
import com.example.moviez.presentation.commons.getGender
import com.example.moviez.presentation.commons.toSingleDecimal

@Composable
fun PersonSideDetails(
    department: String?,
    gender: Int,
    birthDate: String?,
    deathDate: String?,
    birthPlace: String?
) {
    Column(
        Modifier
            .padding(start = 0.dp, top = 10.dp, end = 10.dp, bottom = 7.dp),
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.white)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
        ) {
            Column(
                modifier = Modifier.padding(10.dp, 5.dp)
            ) {
                Text(
                    text = "Department",
                    fontSize = 16.sp,
                )
                Text(
                    text = if(department == null)"--" else department,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
        Card(
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.white)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
        ) {
            Column(
                modifier = Modifier.padding(10.dp, 5.dp)
            ) {
                Text(
                    text = "Gender",
                    fontSize = 16.sp,
                )
                Text(
                    text = getGender(gender),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
        if(deathDate.isNullOrEmpty()){
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.white)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
            ) {
                Row(
                    modifier = Modifier.padding(10.dp, 5.dp)
                ) {
                    Text(
                        text = "Age :-  ",
                        fontSize = 16.sp,
                    )
                    Text(
                        text = if(birthDate == null)"--"
                        else getAge(birthDate).toString(),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }
        }
        Card(
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.white)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
        ) {
            Column(
                modifier = Modifier.padding(10.dp, 5.dp)
            ) {
                Text(
                    text = "Birth Date :-  ",
                    fontSize = 16.sp,
                )
                Text(
                    text = if(birthDate == null)"--"
                    else formatDateString(birthDate),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
        if(deathDate != null) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.white)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
            ) {
                Column(
                    modifier = Modifier.padding(10.dp, 5.dp)
                ) {
                    Text(
                        text = "Death Date :-  ",
                        fontSize = 16.sp,
                    )
                    Text(
                        text = formatDateString(deathDate),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }
        }
        if(birthPlace != null) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.white)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
            ) {
                Column(
                    modifier = Modifier.padding(10.dp, 5.dp)
                ) {
                    Text(
                        text = "Place of birth:-",
                        fontSize = 16.sp,
                    )
                    Text(
                        text = birthPlace,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }
        }
    }
}