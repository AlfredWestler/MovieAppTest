package com.myapplication.ui.splash

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.myapplication.io.models.response.Results
import com.myapplication.ui.MainActivity
import com.myapplication.utils.Mapper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private var popularMovies: List<Results>?=null
    private var popularShows: List<Results>?=null
    private var currentMovies: List<Results>?=null
    private var currentShows: List<Results>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        viewModel.apply { 
            getAllInfo()

            networkState.observe(this@SplashActivity,{ bool->
                println(bool)
                if(!bool){
                    getPopularMoviesEntity().observe(this@SplashActivity,{
                        println("popularMovies: $it")
                        if(it.isNullOrEmpty()){
                            errorAlert("Verifica tu conexión a internet antes de continuar")
                        }else{
                            val list = mutableListOf<Results>()
                            it.forEach { result->
                                list.add(Mapper().transformToResults(result))
                            }
                            this@SplashActivity.popularMovies = list
                            validate()
                        }
                    })
                    getPopularShowsEntity().observe(this@SplashActivity,{
                        println("popularShows: $it")
                        if(it.isNullOrEmpty()){
                            errorAlert("Verifica tu conexión a internet antes de continuar")
                        }else{
                            val list = mutableListOf<Results>()
                            it.forEach { result->
                                list.add(Mapper().transformToResults(result))
                            }
                            this@SplashActivity.popularShows = list
                            validate()
                        }
                    })
                    getCurrentMoviesEntity().observe(this@SplashActivity,{
                        println("currentMovies: $it")
                        if(it.isNullOrEmpty()){
                            errorAlert("Verifica tu conexión a internet antes de continuar")
                        }else{
                            val list = mutableListOf<Results>()
                            it.forEach { result->
                                list.add(Mapper().transformToResults(result))
                            }
                            this@SplashActivity.currentMovies = list
                            validate()
                        }
                    })
                    getCurrentShowsEntity().observe(this@SplashActivity,{
                        println("currentShows: $it")
                        if(it.isNullOrEmpty()){
                            errorAlert("Verifica tu conexión a internet antes de continuar")
                        }else{
                            val list = mutableListOf<Results>()
                            it.forEach { result->
                                list.add(Mapper().transformToResults(result))
                            }
                            this@SplashActivity.currentShows = list
                            validate()
                        }
                    })
                }
            })

            errorMessage.observe(this@SplashActivity, {
                errorAlert(it)
            })
            popularMovies.observe(this@SplashActivity, {
                println("popularMovies: $it")
                this@SplashActivity.popularMovies = it
                validate()
            })
            popularShows.observe(this@SplashActivity, {
                println("popularMovies: $it")
                this@SplashActivity.popularShows = it
                validate()
            })
            currentMovies.observe(this@SplashActivity, {
                println("popularMovies: $it")
                this@SplashActivity.currentMovies = it
                validate()
            })
            currentShows.observe(this@SplashActivity, {
                println("popularMovies: $it")
                this@SplashActivity.currentShows = it
                validate()
            })
        }
    }

    private fun validate() {
        if(!popularMovies.isNullOrEmpty() && !popularShows.isNullOrEmpty() && !currentMovies.isNullOrEmpty() && !currentShows.isNullOrEmpty())
            MainActivity.newInstance(this, popularMovies!!, popularShows!!, currentMovies!!, currentShows!!)
    }

    private fun errorAlert(message: String){
        AlertDialog.Builder(this)
            .setTitle("Error de Conexión")
            .setMessage(message)
            .setPositiveButton("Aceptar"){_,_->this@SplashActivity.finish()}
            .create()
            .show()
    }
}