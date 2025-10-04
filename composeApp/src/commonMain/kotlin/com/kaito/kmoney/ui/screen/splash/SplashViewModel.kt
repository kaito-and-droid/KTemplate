package com.kaito.kmoney.ui.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaito.kmoney.data.model.entity.User
import com.kaito.kmoney.data.repository.IUserRepo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SplashViewModel(
    private val userRepo: IUserRepo
): ViewModel() {

    val users = userRepo.getAllUsers()
        .stateIn(
            scope = viewModelScope,
            initialValue = emptyList(),
            started = SharingStarted.WhileSubscribed(500)
        )

    fun addUser(user: User) = viewModelScope.launch {
        userRepo.addUser(user)
    }

    fun deleteUser(user: User) = viewModelScope.launch {
        userRepo.deleteUser(user)
    }
}