package com.oyamo.sinema.model

import com.oyamo.sinema.R
import com.oyamo.sinema.screens.destinations.AccountScreenDestination
import com.oyamo.sinema.screens.destinations.Destination
import com.oyamo.sinema.screens.destinations.FavoritesScreenDestination
import com.oyamo.sinema.screens.destinations.HomeScreenDestination

sealed class BottomNavItem(var title: String, var icon: Int, var destination: Destination) {
    object Home : BottomNavItem(
        title = "Home",
        icon = R.drawable.ic_home,
        destination = HomeScreenDestination
    )
    object Favorites: BottomNavItem(
        title = "Favorites",
        icon = R.drawable.ic_star,
        destination = FavoritesScreenDestination
    )
    object Account: BottomNavItem(
        title = "Account",
        icon = R.drawable.ic_profile,
        destination = AccountScreenDestination
    )
}