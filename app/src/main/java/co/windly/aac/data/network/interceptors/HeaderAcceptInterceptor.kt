package co.windly.aac.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class HeaderAcceptInterceptor(private val contentType: String) : Interceptor {

  companion object {
    private val ACCEPT_HEADER = "Accept"
  }

  override fun intercept(chain: Interceptor.Chain): Response {
    val original = chain.request()
    val intercepted = original.newBuilder().header(ACCEPT_HEADER, contentType).build()
    return chain.proceed(intercepted)
  }
}
