package com.getir.patika.getir_android_kotlin_bootcamp_finalcase_aysenur_


import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var cardSum:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cardSum = findViewById(R.id.cardSum)
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val fragment = HomeFragment()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()


        cardSum.setOnClickListener {





        }

    }







}
