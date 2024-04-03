package com.example.cookbook.navigation

import com.example.cookbook.R

sealed class Screen(
    val route: String,
    val icon_active: Int?,
    val icon_inactive: Int?
) {
    object Launch : Screen(
        route = "launch_screen",
        null, null
    )

    object AuthChoice: Screen(
        route = "auth_choice_screen",
        null, null
    )

    object SignIn: Screen(
        route = "sign_in_screen",
        null, null
    )

    object SignUp: Screen(
        route = "sign_up_screen",
        null, null
    )

    object Home : Screen(
        route = "home_screen",
        icon_active = R.drawable.ic_bottomnav_home_active,
        icon_inactive = R.drawable.ic_bottomnav_home_inactive
    )

    object Bookmark : Screen(
        route = "bookmark_screen",
        icon_active = R.drawable.ic_bottomnav_bookmark_inactive,
        icon_inactive = R.drawable.ic_bottomnav_bookmark_inactive
    )

    object Search : Screen(
        route = "search_screen",
        icon_active = R.drawable.ic_search_active,
        icon_inactive = R.drawable.ic_search
    )

    object Profile : Screen(
        route = "profile_screen",
        icon_active = R.drawable.ic_bottomnav_profile_active,
        icon_inactive = R.drawable.ic_bottomnav_profile_inactive
    )

    object CreateRecipe : Screen(
        route = "create_recipe_screen",
        null,
        null
    )

    object RecipeInfo : Screen(
        route = "recipe_info_screen",
        null,
        null
    )
}
