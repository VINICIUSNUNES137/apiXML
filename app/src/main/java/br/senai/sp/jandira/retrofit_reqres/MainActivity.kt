package br.senai.sp.jandira.retrofit_reqres

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonObject
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiService = RetrofitHelper.getInstance().create(ApiService::class.java)

        //AÇÃO DO BOTÃO GET

        findViewById<Button>(R.id.btnGet).setOnClickListener {
            getUserById()
        }

        //AÇÃO DO BOTÃO POST

        findViewById<Button>(R.id.btnPost).setOnClickListener {
            createUser()
        }

        //AÇÃO DO BOTÃO PUT

        findViewById<Button>(R.id.btnPut).setOnClickListener {
            updateUser()
        }

        //AÇÃO DO BOTÃO DELETE

        findViewById<Button>(R.id.btnDelete).setOnClickListener {
            deleteUser()
        }
    }

    private fun deleteUser() {

        lifecycleScope.launch() {
            val result = apiService.deleteUser(id = "3")

            if (result.isSuccessful) {
                Log.e("DELETEDATA", "${result.code()}")
            } else {
                Log.e("DELETEDATA", "${result.message()}")
            }
        }

    }

    private fun updateUser() {
        lifecycleScope.launch {
            val body = JsonObject().apply {
                addProperty("name", "Mauricio")
                addProperty("job", "Backend Developer")
            }

            val result = apiService.updateUser(id = "3", body = body)

            if (result.isSuccessful) {
                Log.e("UPDATEDATA", "${result.body()}")
            } else {
                Log.e("UPDATEDATA", "${result.message()}")
            }
        }
    }

    private fun createUser() {

        lifecycleScope.launch {

            val body = JsonObject().apply {
                addProperty("name", "Vinicius")
                addProperty("job", "Desenvolvedor Web")
            }

            val result = apiService.createUser(body)

            if (result.isSuccessful) {
                Log.e("CREATEDATA", "${result.body()}")
            } else {
                Log.e("CREATEDATA", "${result.message()}")
            }
        }


    }

    private fun getUserById() {
        lifecycleScope.launch {
            //Chamada para o endpoint
            val result = apiService.getUserById("2")

            if (result.isSuccessful) {
                Log.e("GETDATA", "${result.body()?.data}")
            } else {
                Log.e("GETDATA", "${result.message()}")
            }
        }
    }
}