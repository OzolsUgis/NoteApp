package com.ugisozols.noteapp.presentation.registration

import androidx.lifecycle.ViewModel
import com.ugisozols.noteapp.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    private val repository : AuthRepository
): ViewModel() {
}