package com.example.obrazy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.widget.doOnTextChanged

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var img = findViewById<ImageView>(R.id.imageViewZdj)
        var widok = findViewById<CheckBox>(R.id.checkBoxWidok)
        var lewo = findViewById<ImageButton>(R.id.buttonLeft)
        var prawo = findViewById<ImageButton>(R.id.buttonPrawo)
        var usun = findViewById<ImageButton>(R.id.buttonClear)
        var rotacja = findViewById<EditText>(R.id.editTextRotacja)
        var skala = findViewById<EditText>(R.id.editTextSkala)
        var currentlyselected = 0;
        val zdjecia = arrayOf(R.drawable.musztarda, R.drawable.szef, R.drawable.szefsyn2,R.drawable.zupka)

        widok.setOnCheckedChangeListener { buttonView, isChecked ->
            if(widok.isChecked()){
                img.setVisibility(View.INVISIBLE)
            }else{
                img.setVisibility(View.VISIBLE)
            }
        }

        prawo.setOnClickListener {
            currentlyselected++;
            if(currentlyselected==4){
                currentlyselected = 0;
            }
            img.setImageResource(zdjecia[currentlyselected])
        }

        lewo.setOnClickListener {
            currentlyselected--;
            if(currentlyselected==-1){
                currentlyselected = 3;
            }
            img.setImageResource(zdjecia[currentlyselected])
        }

        /*usun.setOnClickListener{
            zdjecia[currentlyselected] = null ;
        }*/

        rotacja.doOnTextChanged { text, start, before, count ->
            var wartosc = rotacja.text.toString()
            if(wartosc.isNotEmpty()) {
                var floatwartosc = wartosc.toFloat()
                img.rotation = floatwartosc
            }
        }

        skala.doOnTextChanged { text, start, before, count ->
            var wartosc = skala.text.toString()
            if(wartosc.isNotEmpty()) {
                var floatwartosc = wartosc.toFloat()
                img.scaleX = floatwartosc
                img.scaleY = floatwartosc
            }
        }


    }

}