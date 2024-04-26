package com.example.tsukeysmobile.Requests.KeyRequests

data class KeyRequestsData(
    val requestId: String,
    val classroomNumber: String,
    val endOfRequest: String,
    val keyOwnerEmail: String,
    val keyOwnerFullName: String,
    val keyRecipientEmail: String,
    val keyRecipientFullName: String,
    val status: String
)