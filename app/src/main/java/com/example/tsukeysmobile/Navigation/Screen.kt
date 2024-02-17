package com.example.tsukeysmobile.Navigation

sealed class Screen(val route:String){
    object BookScreen : Screen(route = "book_screen")
    object RequestsScreen : Screen(route = "requests_screen")
    object ProfileScreen : Screen(route = "profile_screen")


    fun withArgs(vararg args:String) : String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}