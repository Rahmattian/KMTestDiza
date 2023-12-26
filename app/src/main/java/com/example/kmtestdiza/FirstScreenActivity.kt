package com.example.kmtestdiza

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class FirstScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_screen_activity)
        //listener untuk btn check
        val btn_check = findViewById<Button>(R.id.check_btn)
        btn_check.setOnClickListener{
            val palindrome = findViewById<EditText>(R.id.input_text_palindrome).text.toString()
            val isPalindrome = isPalindrome(palindrome)
            val resultMessage = if(isPalindrome) "isPalindrome" else "not Palindrome"
            showResultDialog(resultMessage)
        }

        //listener untuk next button
        val btn_next = findViewById<Button>(R.id.next_btn)

        btn_next.setOnClickListener{
            val name = findViewById<EditText>(R.id.input_text).text.toString()
            if (name.isNotEmpty())
            {
                val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("USERNAME_KEY", name)
                editor.apply()
                val intent = Intent(this@FirstScreenActivity, SecondScreenActivity::class.java)
//                intent.putExtra("keyNameValue", name)
                startActivity(intent)
            }
            else
            {
                nameInputCheck()
            }
        }
    }
    private fun isPalindrome(palindrome: String): Boolean {
        val cleanSentence = palindrome.replace("\\s".toRegex(), "").toLowerCase()
        return cleanSentence == cleanSentence.reversed()
    }
    private fun showResultDialog(message: String) {
        val alertDialog = AlertDialog.Builder(this@FirstScreenActivity)
            .setTitle("Palindrome Check Result")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()
        alertDialog.show()
    }
    private fun nameInputCheck() {
        val alertDialog = AlertDialog.Builder(this@FirstScreenActivity)
            .setTitle("Warning!!!")
            .setMessage("Field Name Harus Diisi!")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()
        alertDialog.show()
    }
}