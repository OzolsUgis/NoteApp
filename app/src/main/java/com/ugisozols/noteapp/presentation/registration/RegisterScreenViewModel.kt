package com.ugisozols.noteapp.presentation.registration


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugisozols.noteapp.repository.AuthRepository
import com.ugisozols.noteapp.utitilies.Constants.EMPTY_FIELD_ERROR
import com.ugisozols.noteapp.utitilies.Constants.MIN_PASSWORD_LENGTH
import com.ugisozols.noteapp.utitilies.Constants.PASSWORDS_DO_NOT_MATCH_ERROR
import com.ugisozols.noteapp.utitilies.Constants.TOO_SHORT_PASSWORD_ERROR
import com.ugisozols.noteapp.utitilies.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    private val repository : AuthRepository
): ViewModel() {

    private val _email = MutableLiveData("")
    val email : LiveData<String> = _email

    fun onEmailChange(newEmail : String){
        _email.value = newEmail
    }


    private val _password = MutableLiveData("")
    val password : LiveData<String> = _password

    fun onPasswordChange(newPassword : String) {
        _password.value = newPassword
    }

    private val _confirmedPassword = MutableLiveData("")
    val confirmedPassword : LiveData<String> = _confirmedPassword

    fun onConfirmedPasswordChange(newConfirmedPassword : String) {
        _confirmedPassword.value = newConfirmedPassword
    }


    private val _register = MutableLiveData<Resource<String>>()
    val register :LiveData<Resource<String>> = _register

    fun registerUser(email : String, password : String, confirmedPassword : String){
        _register.postValue(Resource.Loading(null))
        if(email.isEmpty()||password.isEmpty()||confirmedPassword.isEmpty()){
            _register.postValue(Resource.Error(EMPTY_FIELD_ERROR))
            return
        }
        if(password.length < MIN_PASSWORD_LENGTH){
            _register.postValue(Resource.Error(TOO_SHORT_PASSWORD_ERROR))
            return
        }
        if(password != confirmedPassword){
            _register.postValue(Resource.Error(PASSWORDS_DO_NOT_MATCH_ERROR))
            return
        }
        viewModelScope.launch {
            _register.postValue(repository.register(email,password))
        }
    }
}