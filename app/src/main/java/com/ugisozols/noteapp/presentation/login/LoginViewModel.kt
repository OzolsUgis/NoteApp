package com.ugisozols.noteapp.presentation.login

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugisozols.noteapp.data.remote.BasicAuthInterceptor
import com.ugisozols.noteapp.repository.AuthRepository
import com.ugisozols.noteapp.utitilies.Constants.EMPTY_FIELD_ERROR
import com.ugisozols.noteapp.utitilies.Constants.KEY_EMAIL
import com.ugisozols.noteapp.utitilies.Constants.KEY_PASSWORD
import com.ugisozols.noteapp.utitilies.Constants.NO_EMAIL
import com.ugisozols.noteapp.utitilies.Constants.NO_PASSWORD
import com.ugisozols.noteapp.utitilies.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val authInterceptor: BasicAuthInterceptor,
    private val sharedPreferences: SharedPreferences
): ViewModel() {


    private val _email = MutableLiveData("")
    val email : LiveData<String> = _email

    fun onEmailChange(newEmail : String){
        _email.value = newEmail
    }


    private val _password = MutableLiveData("")
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
    val loggedInEmail = sharedPreferences.getString(KEY_EMAIL, NO_EMAIL)
    val loggedInPassword = sharedPreferences.getString(KEY_PASSWORD, NO_PASSWORD)



    fun setSharedPreferencesEmailAndPassword(email: String, password: String){
        sharedPreferences.edit().putString(KEY_EMAIL,email).apply()
        sharedPreferences.edit().putString(KEY_PASSWORD, password).apply()
    }
}