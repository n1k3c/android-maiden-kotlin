package mutiny.codes.maidenkotlin.util

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by nikola on 01.03.18..
 */

class CheckInternetConnection() {
    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}

