package com.example.kmtestdiza

//import ThirdScreenActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        val receivedValue = intent.getStringExtra("keyNameValue")
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("USERNAME_KEY", "")
//        val valueFromThirdPage = intent.getStringExtra("USERNAME_KEY")
        val sharedPreferencesThirdScreen = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val usernameThirdScreen = sharedPreferences.getString("USERNAME_API_KEY", "")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_screen_activity)
        //menampilkan value dari page sebelumnya
        val firstScreenTextView = findViewById<TextView>(R.id.name)
        firstScreenTextView.text = username

        //menampilkan value dari page third screen

        if (usernameThirdScreen != null) {
            if(usernameThirdScreen.isNotEmpty()){
                val thirdScreenTextView = findViewById<TextView>(R.id.select_username)
                thirdScreenTextView.text = usernameThirdScreen
            }
        }


        val back_btn = findViewById<ImageButton>(R.id.back_btn)
        back_btn.setOnClickListener{
            val intent = Intent(this@SecondScreenActivity, FirstScreenActivity::class.java)
            startActivity(intent)
        }

        val choose_user_btn = findViewById<Button>(R.id.choose_btn)
        choose_user_btn.setOnClickListener{
            val intent = Intent(this@SecondScreenActivity, ThirdScreenActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}
