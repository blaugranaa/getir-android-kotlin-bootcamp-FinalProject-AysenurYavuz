package com.getir.patika.getir_android_kotlin_bootcamp_finalcase_aysenur_


import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class DataRepository {
    private val client = OkHttpClient()
    private val gson = Gson()


    fun fetchData(url: String): Flow<List<BeveragePack>> = flow {
        val request = Request.Builder().url(url).build()

        try {
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val jsonData = response.body?.string()
                val beveragePacks: List<BeveragePack> = gson.fromJson(jsonData, Array<BeveragePack>::class.java).toList()
                emit(beveragePacks)
            } else {
                throw IOException("Unexpected code $response")
            }
        } catch (e: IOException) {
            Log.e("DataRepository", "Error fetching data: ${e.message}")
            throw IOException("IOException: ${e.message}")
        }
    }.catch { e ->
        throw IOException("IOException: ${e.message}")
    }.flowOn(Dispatchers.IO)

}
