package com.gmail.redballtoy.sdsms_reader_one.navigation

sealed class Screen(val route: String) {
    object Permission : Screen(route = "permission_screen")
    object Main : Screen(route = "main_screen")
}

