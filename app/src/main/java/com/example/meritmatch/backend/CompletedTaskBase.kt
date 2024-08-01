package com.example.meritmatch.backend

data class CompletedTaskBase(
    val completion_date: String,
    val task_status: String = "COMPLETED",
    val taskid: Int
)