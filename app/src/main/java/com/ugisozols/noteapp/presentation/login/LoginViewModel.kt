package com.ugisozols.noteapp.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugisozols.noteapp.data.remote.BasicAuthInterceptor
import com.ugisozols.noteapp.repository.AuthRepository
import com.ugisozols.noteapp.utitilies.Constants
import com.ugisozols.noteapp.utitilies.Constants.EMPTY_FIELD_ERROR
import com.ugisozols.noteapp.utitilies.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val authInterceptor: BasicAuthInterceptor
): ViewModel() {


    private val _email = MutableLiveData<String>("")
    val email : LiveData<String> = _email

    fun onEmailChange(newEmail : String){
        _email.value = newEmail
    }


    private val _password = MutableLiveData<String>("")
    val password : LiveData<String> = _password

    fun onPasswordChange(newPassword: String){
        _password.value = newPassword
    }

    fun authenticate(email: String, password: String){
        authInterceptor.email = email
        authInterceptor.password = password
    }

    private val _login = MutableLiveData<Resource<String>>()
    val login : LiveData<Resource<String>> = _login

    fun login(email : String, password: String){
        _login.postValue(Resource.Loading(null))
        if(email.isEmpty()||password.isEmpty()){
            _login.postValue( Resource.Error(EMPTY_FIELD_ERROR))
        }
        viewModelScope.launch {
            _login.postValue(repository.login(email, password))
        }
    }
}