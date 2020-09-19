package com.mi17.myweather.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object Common {

    fun isConnectedIntener(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (connectivityManager != null) {
            val info = connectivityManager.allNetworkInfo
            if (info != null) {
                var i = 0
                while (1 < info.size) {
                    if (info[i].state == NetworkInfo.State.CONNECTED)
                        return true
                    i++
                }
            }
        }
        return false
    }
}