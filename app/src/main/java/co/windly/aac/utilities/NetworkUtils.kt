package co.windly.aac.utilities

import android.content.Context
import android.net.ConnectivityManager

class NetworkUtils private constructor() {

  companion object {

    fun isNetworkConnected(context: Context): Boolean {
      val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
      val activeNetwork = connectivityManager.activeNetworkInfo
      return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
  }
}
