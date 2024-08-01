package com.example.meritmatch.backend

data class NewTaskBase(
    val assign_date: String,
    val karma: Int,
    val task_desc: String,
    val task_status: String,
    val task_title: String,
    val username: String
)