package com.example.ios_registr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {
    lateinit var usern:EditText
    lateinit var email:EditText
    lateinit var passw:EditText
    lateinit var create:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usern = findViewById(R.id.username)
        email = findViewById(R.id.email)
        passw = findViewById(R.id.password)
        create = findViewById(R.id.create)

        var list = mutableListOf<User>()

        val cache = getSharedPreferences("CASHE", MODE_PRIVATE)
        val edit = cache.edit()

        val type = object : TypeToken<List<User>>(){}.type
        val gson = Gson()

        create.setOnClickListener{
            var log = usern.text
            var emai = email.text
            var pass = passw.text


        }
    }
}