package com.ugisozols.noteapp.utitilies

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities
import android.os.Build


fun checkInternetConnectivity(context: Context): Boolean {
    val connectivityManager = context.getSystemService(
        Context.CONNECTIVITY_SERVICE
    )as ConnectivityManager

    val activeNetwork = connectivityManager.activeNetwork ?: return false
    val network = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
    return network.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    return false
}