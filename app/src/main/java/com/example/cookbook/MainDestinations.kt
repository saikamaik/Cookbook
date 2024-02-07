package com.example.cookbook

import androidx.compose.runtime.Composable
import com.example.cookbook.presentation.view.homePage.HomeUI

interface MainDestinations {
    val screen: @Composable () -> Unit
    val route: String
}

object HomePage: MainDestinations {
    override val screen: @Composable () -> Unit = {HomeUI()}
    override val route: String
        get() = TODO("Not yet implemented")
}

//object IngrPage: MainDestinations {
//    override val screen: @Composable () -> Unit
//        get() = TODO("Not yet implemented")
//    override val route: String
//        get() = TODO("Not yet implemented")
//}
//
//object ProfilePage: MainDestinations {
//    override val screen: @Composable () -> Unit
//        get() = TODO("Not yet implemented")
//    override val route: String
//        get() = TODO("Not yet implemented")
//}