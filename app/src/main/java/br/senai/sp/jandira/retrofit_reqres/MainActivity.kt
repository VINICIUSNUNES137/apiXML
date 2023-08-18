package br.senai.sp.jandira.retrofit_reqres

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiService = RetrofitHelper.getInstance().create(ApiService::class.java)

        //AÇÃO DO BOTÃO GET

        var teste = findViewById<Button>(R.id.btnGet).setOnClickListener{
            getUserById()
        }
    }

    private fun getUserById() {
        lifecycleScope.launch {
            //Chamada para o endpoint
            val result = apiService.getUserById("2")

            if(result.isSuccessful){
                Log.e("GETDATA", "${result.body()?.data}")
            }else{
                Log.e("GETDATA", "${result.message()}")
            }
        }
    }
}