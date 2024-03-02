package com.example.tsukeysmobile.Navigation

sealed class Screen(val route:String){
    object BookScreen : Screen(route = "book_screen")
    object RequestsScreen : Screen(route = "requests_screen")
    object ProfileScreen : Screen(route = "profile_screen")
    object CabScreen : Screen(route = "profile_screen")
    object RegScreen : Screen(route = "registration_screen")
    object AuthScreen : Screen(route = "authorization_screen")


    fun withArgs(vararg args:String) : String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}