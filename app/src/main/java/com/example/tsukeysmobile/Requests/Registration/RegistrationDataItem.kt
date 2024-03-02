package com.example.tsukeysmobile.Requests.Registration

data class RegistrationDataItem(
    val name: String,
    val lastname: String,
    val birthDate: String,
    val gender: String,
    val email: String,
    val password: String
)