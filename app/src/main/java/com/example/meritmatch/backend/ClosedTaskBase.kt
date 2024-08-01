package com.example.meritmatch.backend

data class ClosedTaskBase(
    val closing_date: String,
    val task_status: String = "CLOSED",
    val taskid: Int
)