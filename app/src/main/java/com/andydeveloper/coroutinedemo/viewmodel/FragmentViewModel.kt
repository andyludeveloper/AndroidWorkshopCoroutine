package com.andydeveloper.coroutinedemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andydeveloper.coroutinedemo.model.User
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {

    @GET("users/{user}")
    fun getUserInfo(@Path("user") user: String): Call<User>

    @GET("users/{user}")
    suspend fun getUserInfoFromCoroutine(@Path("user") user: String): User
}

class FragmentViewModel : ViewModel() {

    private val gitHubApi: GitHubApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        gitHubApi = retrofit.create(GitHubApi::class.java)
    }

    private var _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading

    private var _info: MutableLiveData<String> = MutableLiveData()
    val info: LiveData<String>
        get() = _info

    //
//    fun getGithubUser(user: String) {
//        val call = gitHubApi.getUserInfo(user)
//        call.enqueue(object : Callback<User> {
//            override fun onResponse(call: Call<User>, response: Response<User>) {
//                if (response.isSuccessful) {
//                    _info.value = response.body()!!.toString()
//                }
////                onSuccess(response.body()?.toString() ?: "")
//            }
//
//            override fun onFailure(call: Call<User>, t: Throwable) {
//
//            }
//        })
//    }
    private lateinit var job: Job

//    fun getGithubUser(user: String) {
//        viewModelScope.launch {
//            _loading.value = true
//            val user = gitHubApi.getUserInfoFromCoroutine(user)
//            _info.value = user.toString()
//            _loading.value = false
//        }
//    }

    fun getGithubUser(user: String) {
        viewModelScope.launch {
            _loading.value = true
            val user = gitHubApi.getUserInfoFromCoroutine(user)
            _info.value = user.toString()

            job = launch {
                delay(5000)
                _loading.value = false
            }
            println("done")
        }
    }

    fun cancelJob(){
        job.cancel()
    }

//    fun cancelJob() {
//        viewModelScope.cancel()
//        _loading.value = false
//    }


}