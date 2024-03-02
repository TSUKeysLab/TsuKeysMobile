package com.example.tsukeysmobile.Requests.Requests

data class Requests(
    val pagination: Pagination,
    val requests: List<RequestDataItem>
)