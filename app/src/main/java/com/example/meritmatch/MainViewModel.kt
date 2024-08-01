package com.example.meritmatch

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meritmatch.backend.AccountBase
import com.example.meritmatch.backend.ClosedTaskBase
import com.example.meritmatch.backend.CompletedTaskBase
import com.example.meritmatch.backend.MeritMarchApi
import com.example.meritmatch.backend.NewTaskBase
import com.example.meritmatch.backend.ReserveTaskBase
import com.example.meritmatch.backend.TaskBaseItem
import com.example.meritmatch.backend.UnreserveTaskBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class MainViewModel @Inject constructor(
    val meritMarchApi: MeritMarchApi,
) : ViewModel() {
    var isNewUser by mutableStateOf(false)
    var currentUser by mutableStateOf("")
    var karma by mutableIntStateOf(0)
    var reservedTasks by mutableStateOf<MutableList<TaskBaseItem>>(mutableListOf())
    var newTasks by mutableStateOf<MutableList<TaskBaseItem>>(mutableListOf())

    val time = Calendar.getInstance().time
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val currentDate = formatter.format(time)

    fun verifyUserName(userName: String) {
        viewModelScope.launch(context = Dispatchers.IO) {
            val response = meritMarchApi.checkAccount(userName)
//            try {
//
//            } catch (t: Throwable) {
//                null
//            }
            if (response != null && response.isSuccessful) {
                isNewUser = response.body()!!.exists == "false"
            }
        }
    }

    suspend fun createNewAccount(newAccount: AccountBase): Boolean {
        var accountCreationSuccess: Boolean = false
        val response = meritMarchApi.createAccount(newAccount)
//            try {
//
//            } catch (t: Throwable) {
//                null
//            }

        if (response != null && response.isSuccessful) {
            accountCreationSuccess = true
        } else {
            accountCreationSuccess = false
        }

        return accountCreationSuccess
    }

    suspend fun userLogin(accountDetails: AccountBase): String {
        var loginStatus = ""
        val checkResponse = meritMarchApi.checkAccount(currentUser)
//            try {
//
//            } catch (t: Throwable) {
//                null
//            }
        if (checkResponse != null && checkResponse.isSuccessful && checkResponse.body()!!.exists == "true") {
            val loginResponse = meritMarchApi.login(accountDetails)

            if (loginResponse != null && loginResponse.isSuccessful) {
                loginStatus = loginResponse.body()!!.login
            } else {
                loginStatus = "error"
            }
        } else if (checkResponse.body()!!.exists == "false") {
            loginStatus = "no account"
        }

        return loginStatus
    }

    fun fetchKarma() {
        viewModelScope.launch(context = Dispatchers.IO) {
            val response = meritMarchApi.fetchKarma(currentUser)
            if (response != null && response.isSuccessful) {
                karma = response.body()!!.karma
            }
        }
    }

    fun fetchReservedTasks(){
        viewModelScope.launch(context = Dispatchers.IO) {
            val response = meritMarchApi.fetchReservedTasks(currentUser)
            println("boohoo $response")
            if (response != null && response.isSuccessful) {
                reservedTasks = response.body()!!
            }
        }
    }

    fun fetchNewTasks() {
        viewModelScope.launch(context = Dispatchers.IO) {
            val response = meritMarchApi.fetchNewTasks(currentUser)
            if (response != null && response.isSuccessful) {
                newTasks = response.body()!!
            }
        }
    }

    fun addNewTask(newTask: NewTaskBase) {
        viewModelScope.launch(context = Dispatchers.IO) {
            val response = meritMarchApi.addNewTask(newTask)
        }
    }

    fun fetchUserTasks() {
        viewModelScope.launch(context = Dispatchers.IO) {
            val response = meritMarchApi.fetchUserTasks(currentUser)
            if (response != null && response.isSuccessful) {
                reservedTasks = response.body()!!
            }
        }
    }

    fun reserveTask(taskId: Int) {
        viewModelScope.launch(context = Dispatchers.IO) {
            val task = ReserveTaskBase(taskid = taskId, reserved_date = currentDate)
            val response = meritMarchApi.reserveTask(currentUser, task = task)

            if (response != null && response.isSuccessful) {
                fetchReservedTasks()
            }
        }
    }

    fun unreserveTask(taskId: Int) {
        viewModelScope.launch(context = Dispatchers.IO) {
            val task = UnreserveTaskBase(taskid = taskId)
            val response = meritMarchApi.unreserveTask(currentUser, task = task)
        }
    }

    fun completeTask(taskId: Int) {
        viewModelScope.launch(context = Dispatchers.IO) {
            val task = CompletedTaskBase(taskid = taskId, completion_date = currentDate)
            val response = meritMarchApi.completeTask(currentUser, task = task)
        }
    }

    fun closeTask(taskId: Int) {
        viewModelScope.launch(context = Dispatchers.IO) {
            val task = ClosedTaskBase(taskid = taskId, closing_date = currentDate)
            val response = meritMarchApi.closeTask(currentUser, task = task)
        }
    }

    fun deductKarma(username: String, karma: Int) {
        viewModelScope.launch(context = Dispatchers.IO) {
            val response = meritMarchApi.deductKarma(username, karma)
        }
    }

    var selectedNavBarIndex by mutableIntStateOf(1)
}