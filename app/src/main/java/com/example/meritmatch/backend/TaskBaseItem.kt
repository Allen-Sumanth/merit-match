package com.example.meritmatch.backend

data class TaskBaseItem(
    val assign_date: String,
    val closing_date: Any,
    val completion_date: Any,
    val karma: Int,
    val reserved_date: String,
    val reservedby: String,
    val rmrks: Any,
    val task_desc: String,
    val task_status: String,
    val task_title: String,
    val taskid: Int,
    val username: String
)