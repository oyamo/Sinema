package com.oyamo.sinema.screens.account

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.oyamo.sinema.R
import com.oyamo.sinema.model.AccountItem
import com.oyamo.sinema.screens.destinations.AboutScreenDestination
import com.oyamo.sinema.ui.theme.primaryDarkVariant
import com.oyamo.sinema.ui.theme.primaryGray
import com.oyamo.sinema.ui.theme.primaryPink
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun AccountScreen(
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val context = LocalContext.current
        val showSocialsDialog = remember { mutableStateOf(false) }

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painterResource(
                id = R.drawable.muviz
            ),
            contentDescription = "App logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(8.dp)
        )

        LazyColumn(
            contentPadding = PaddingValues(16.dp)
        ) {

            val accountItems = listOf(
                AccountItem(
                    "About",
                    R.drawable.ic_danger_circle
                ),
                AccountItem(
                    "Rate us",
                    R.drawable.ic_star
                ),
                AccountItem(
                    "Share",
                    R.drawable.ic_share
                ),
                AccountItem(
                    "Get in touch",
                    R.drawable.ic_socials
                )
            )

            items(accountItems) { item ->
                AccountItems(
                    accountItem = item,
                    onClick = {
                        when (item.title) {
                            "About" -> {
                                //Toast.makeText(context, "About the app", Toast.LENGTH_SHORT).show()
                                navigator.navigate(AboutScreenDestination)
                            }
                            "Rate us" -> {

                            }
                            "Share" -> {

                            }
                            "Get in touch" -> {

                            }
                        }
                    }
                )
            }
        }


        if (showSocialsDialog.value) {
            AlertDialog(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                onDismissRequest = {
                    showSocialsDialog.value = false
                },
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Get in touch",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                text = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    val intent = Intent(Intent.ACTION_VIEW)
                                    intent.data =
                                        Uri.parse("https://www.linkedin.com/in/joel-kanyi-037270174/")
                                    startActivity(context, intent, null)
                                },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic__linkedin),
                                    tint = Color(0xFF0072ba),
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Linkedin",
                                    color = Color.White,
                                    textAlign = TextAlign.Left,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Icon(
                                painter = painterResource(id = R.drawable.ic_chevron_right),
                                tint = primaryGray,
                                contentDescription = null
                            )
                        }
                        Spacer(modifier = Modifier.height(24.dp))


                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    Toast
                                        .makeText(context, "Twitter", Toast.LENGTH_SHORT)
                                        .show()

                                    val intent = Intent(Intent.ACTION_VIEW)
                                    intent.data =
                                        Uri.parse("https://twitter.com/_joelkanyi")
                                    startActivity(context, intent, null)
                                },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_twitter),
                                    tint = Color(0xFF0072ba),
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Twitter",
                                    color = Color.White,
                                    textAlign = TextAlign.Left,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Icon(
                                painter = painterResource(id = R.drawable.ic_chevron_right),
                                tint = primaryGray,
                                contentDescription = null
                            )
                        }
                        Spacer(modifier = Modifier.height(24.dp))


                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    Toast
                                        .makeText(context, "Github", Toast.LENGTH_SHORT)
                                        .show()

                                    val intent = Intent(Intent.ACTION_VIEW)
                                    intent.data =
                                        Uri.parse("https://github.com/JoelKanyi")
                                    startActivity(context, intent, null)
                                },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_github),
                                    tint = Color(0xFF0072ba),
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Github",
                                    color = Color.White,
                                    textAlign = TextAlign.Left,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Icon(
                                painter = painterResource(id = R.drawable.ic_chevron_right),
                                tint = primaryGray,
                                contentDescription = null
                            )
                        }
                        Spacer(modifier = Modifier.height(24.dp))


                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    Toast
                                        .makeText(context, "Facebook", Toast.LENGTH_SHORT)
                                        .show()

                                    val intent = Intent(Intent.ACTION_VIEW)
                                    intent.data =
                                        Uri.parse("https://www.facebook.com/joel.kanyi.71/")
                                    startActivity(context, intent, null)
                                },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic__facebook),
                                    tint = Color(0xFF0072ba),
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Facebook",
                                    color = Color.White,
                                    textAlign = TextAlign.Left,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Icon(
                                painter = painterResource(id = R.drawable.ic_chevron_right),
                                tint = primaryGray,
                                contentDescription = null
                            )
                        }
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            showSocialsDialog.value = false
                        },
                        colors = ButtonDefaults.buttonColors(primaryPink)
                    ) {
                        Text(text = "Okay", color = Color.White)
                    }
                },
                backgroundColor = primaryDarkVariant,
                contentColor = Color.Black,
                shape = RoundedCornerShape(10.dp)
            )
        }
    }
}

@Composable
fun AccountItems(
    accountItem: AccountItem,
    onClick: () -> Unit = {},
) {
    Column(modifier = Modifier
        .clickable {
            onClick()
        }
    ) {

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .height(24.dp)
                    .width(24.dp),
                painter = painterResource(id = accountItem.icon),
                contentDescription = null,
                tint = primaryGray
            )

            Spacer(modifier = Modifier.width(16.dp))
            Text(text = accountItem.title, color = Color.White)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Divider(
            color = primaryGray,
            thickness = 1.dp,
            modifier = Modifier
                .padding(
                    start = 8.dp,
                    end = 8.dp
                )
        )

    }
}