package com.example.meritmatch.backend

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface MeritMarchApi {
    @GET("/check-account")
    suspend fun checkAccount(@Query("username") username: String): Response<AccCheckResponse>

    @POST("/create-account")
    suspend fun createAccount(@Body account: AccountBase): Response<CreateAccResponse>

    @POST("/login")
    suspend fun login(@Body account: AccountBase): Response<LoginResponse>

    @GET("/fetchkarma/{username}")
    suspend fun fetchKarma(@Path("username") username: String): Response<KarmaResponse>

    @GET("/fetchreserved/{username}")
    suspend fun fetchReservedTasks(@Path("username") username: String) : Response<TaskBase>

    @GET("/fetchnewtasks/{username}")
    suspend fun fetchNewTasks(@Path("username") username: String) : Response<TaskBase>

    @POST("/addnewtask")
    suspend fun addNewTask(@Body task: NewTaskBase)

    @PUT("/updatetaskstatus/{username}")
    suspend fun reserveTask(@Path("username") username: String, @Body task: ReserveTaskBase): Response<updateTaskStatusResponse>

    @PUT("/updatetaskstatus/{username}")
    suspend fun unreserveTask(@Path("username") username: String, @Body task: UnreserveTaskBase): Response<updateTaskStatusResponse>

    @PUT("/updatetaskstatus/{username}")
    suspend fun completeTask(@Path("username") username: String, @Body task: CompletedTaskBase): Response<updateTaskStatusResponse>

    @PUT("/updatetaskstatus/{username}")
    suspend fun closeTask(@Path("username") username: String, @Body task: ClosedTaskBase): Response<updateTaskStatusResponse>

    @GET("/fetchusertasks/{username}")
    suspend fun fetchUserTasks(@Path("username") username: String) : Response<TaskBase>

    @PUT("/deductkarma/{username}/{karma}")
    suspend fun deductKarma(@Path("username") username: String, @Path("karma") karma: Int): Response<KarmaResponse>
}