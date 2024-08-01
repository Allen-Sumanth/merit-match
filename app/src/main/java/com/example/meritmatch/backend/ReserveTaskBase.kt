package com.example.meritmatch.backend

data class ReserveTaskBase(
    val reserved_date: String,
    val task_status: String = "RESERVED",
    val taskid: Int
)