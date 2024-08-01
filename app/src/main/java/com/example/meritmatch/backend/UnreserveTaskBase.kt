package com.example.meritmatch.backend

data class UnreserveTaskBase(
    val task_status: String = "NEW",
    val taskid: Int
)