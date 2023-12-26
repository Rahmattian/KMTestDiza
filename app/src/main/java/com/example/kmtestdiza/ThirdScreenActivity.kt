package com.example.kmtestdiza

import UserAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kmtestdiza.databinding.ThirdScreenActivityBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ThirdScreenActivity : AppCompatActivity() {
    private lateinit var binding: ThirdScreenActivityBinding
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ThirdScreenActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi RecyclerView dan Adapter
        userAdapter = UserAdapter(this, mutableListOf())
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = userAdapter

        // Mendapatkan data dari API dan menampilkannya dalam RecyclerView
        fetchUserData()

        val back_button = findViewById<ImageButton>(R.id.back_btn)
        back_button.setOnClickListener{
            val intent = Intent(this@ThirdScreenActivity, SecondScreenActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun fetchUserData() {
        val call: Call<UserResponse> = ApiService.api.getUsers(page = 1, perPage = 100)

        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val newUsers = response.body()?.data ?: emptyList()

                    // Gunakan updateUserList untuk memperbarui dataset
                    userAdapter.updateList(newUsers)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                // Handle failure, if needed
            }
        })
    }


}