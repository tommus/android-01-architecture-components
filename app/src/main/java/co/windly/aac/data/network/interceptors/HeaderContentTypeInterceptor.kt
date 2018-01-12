package co.windly.aac.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class HeaderContentTypeInterceptor(private val contentType: String) : Interceptor {

  companion object {
    private val CONTENT_TYPE_HEADER = "Content-Type"
  }

  override fun intercept(chain: Interceptor.Chain): Response {
    val original = chain.request()
    val intercepted = original.newBuilder().header(CONTENT_TYPE_HEADER, contentType).build()
    return chain.proceed(intercepted)
  }
}
