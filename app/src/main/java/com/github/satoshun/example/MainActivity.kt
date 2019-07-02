package com.github.satoshun.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.github.satoshun.example.databinding.MainActBinding
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET

class MainActivity : AppCompatActivity() {
  private lateinit var binding: MainActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.main_act)
  }
}

interface DogService {
  @GET("breeds/image/random")
  suspend fun getDog(): Dog
}

@Serializable
data class Dog(
  @SerialName("message") val url: String,
  @SerialName("status") val status: String
)

fun getDogService(): DogService {
  val retrofit = Retrofit.Builder()
    .baseUrl("https://dog.ceo/api/")
    .addConverterFactory(
      Json.asConverterFactory("application/json".toMediaType())
    )
    .build()
  return retrofit.create()
}
