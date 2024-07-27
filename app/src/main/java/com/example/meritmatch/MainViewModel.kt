package com.example.meritmatch

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.meritmatch.backend.MeritMarchApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    meritMarchApi: MeritMarchApi
) : ViewModel() {
    var selectedNavBarIndex by mutableIntStateOf(1)
    var taskActive by mutableStateOf(true)
}