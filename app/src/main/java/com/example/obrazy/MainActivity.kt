package com.example.obrazy

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.widget.doOnTextChanged
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.LightingColorFilter
import android.graphics.PorterDuff
import android.provider.MediaStore
import android.widget.ImageView
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    lateinit var zrobzdjecie: ImageButton
    lateinit var lewo: ImageButton
    lateinit var prawo: ImageButton
    lateinit var usun: ImageButton
    private lateinit var img : ImageView
    lateinit var rotacjaX : SeekBar
    lateinit var rotacjaY : SeekBar
    lateinit var przezroczystosc : SeekBar
    lateinit var widok : CheckBox
    lateinit var red : Button
    lateinit var green : Button
    lateinit var blue : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        zrobzdjecie = findViewById(R.id.buttonKamera)
        img = findViewById(R.id.imageViewZdj)
        widok = findViewById(R.id.checkBoxWidok)
        lewo = findViewById(R.id.buttonLeft)
        prawo = findViewById(R.id.buttonPrawo)
        usun = findViewById(R.id.buttonClear)
        rotacjaX = findViewById(R.id.seekBarRotacjaX)
        rotacjaY = findViewById(R.id.seekBarRotacjaY)
        przezroczystosc = findViewById(R.id.seekBarPrzezroczystosc)
        red = findViewById(R.id.buttonFiltrRed)
        green = findViewById(R.id.buttonFiltrGreen)
        blue = findViewById(R.id.buttonFiltrBlue)

        var currentlyselected = 0;
        val zdjecia =
            arrayOf(R.drawable.musztarda, R.drawable.szef, R.drawable.szefsyn2, R.drawable.zupka)

        widok.setOnCheckedChangeListener { buttonView, isChecked ->
            if (widok.isChecked) {
                img.setVisibility(View.INVISIBLE)
            } else {
                img.setVisibility(View.VISIBLE)
            }
        }

        prawo.setOnClickListener {
            currentlyselected++;
            if (currentlyselected == 4) {
                currentlyselected = 0;
            }
            img.setImageResource(zdjecia[currentlyselected])
        }

        lewo.setOnClickListener {
            currentlyselected--;
            if (currentlyselected == -1) {
                currentlyselected = 3;
            }
            img.setImageResource(zdjecia[currentlyselected])
        }

        usun.setOnClickListener{
            img.setImageBitmap(null)
        }

        rotacjaX.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    var zmienna = rotacjaX.getProgress().toFloat()
                    img.rotationX = zmienna
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {

                }

            }
        )

        rotacjaY.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    var zmienna = rotacjaY.getProgress().toFloat()
                    img.rotationY = zmienna
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {

                }

            }
        )

        przezroczystosc.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    var zmienna = przezroczystosc.getProgress()
                    img.imageAlpha = zmienna
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }

            }
        )

        red.setOnClickListener{
            val colorFilter = LightingColorFilter(Color.WHITE, Color.RED)
            img.setColorFilter(colorFilter)
        }

        green.setOnClickListener{
            val colorFilter = LightingColorFilter(Color.WHITE, Color.GREEN)
            img.setColorFilter(colorFilter)
        }

        blue.setOnClickListener{
            val colorFilter = LightingColorFilter(Color.WHITE, Color.BLUE)
            img.setColorFilter(colorFilter)
        }


        zrobzdjecie.isEnabled = false
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),111)
        }
        else
            zrobzdjecie.isEnabled = true
        zrobzdjecie.setOnClickListener {
            val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i, 101)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 101)
        {
            val pic:Bitmap? = data?.getParcelableExtra<Bitmap>("data")
            img.setImageBitmap(pic)
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            zrobzdjecie.isEnabled = true
        }
    }
}