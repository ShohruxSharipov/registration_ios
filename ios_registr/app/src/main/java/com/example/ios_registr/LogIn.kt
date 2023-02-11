package com.example.ios_registr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LogIn : AppCompatActivity() {
    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var text: TextView
    lateinit var btn: Button
    var isok = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        text = findViewById(R.id.signup)
        btn = findViewById(R.id.create)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)

        val type = object : TypeToken<List<User>>() {}.type
        val gson = Gson()

        var list = mutableListOf<User>()

        text.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        btn.setOnClickListener {
            if (username.text.isEmpty() || password.text.isEmpty()) {
                Toast.makeText(this, "Ma'lumotlarni to'ldiring!!!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val cache = getSharedPreferences("CASHE", MODE_PRIVATE)
                var str = cache.getString("users", "")
                list = gson.fromJson(str, type)

                for (i in list.indices) {
                    if (list[i].log == username.text.toString() && list[i].pass == password.text.toString()) {
                        var intent = Intent(this, Test::class.java)
                        startActivity(intent)
                        isok = true
                    }
                }
                if (!isok) {
                    Toast.makeText(
                        this,
                        "Login yoki parol noto'g'ri kiritilgan!!",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }

    }
}