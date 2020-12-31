package com.example.hero_workout

import android.os.Bundle
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern
import kotlin.math.log


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var passWordEditText = findViewById(R.id.pwd) as EditText
        var hideShowButton = findViewById(R.id.hide_show) as ImageButton
        var show = true
        hideShowButton.setOnClickListener {
            if(show){
                passWordEditText.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else{
                passWordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
            }
            show = !show
        }

        var emailEditText = findViewById(R.id.emailId) as EditText
        var einlogenButton = findViewById(R.id.einlogen) as Button
        var text_input_layout = findViewById(R.id.text_input_layout) as TextInputLayout
        einlogenButton.setOnClickListener {

//            Toast.makeText(this, isValidPassword(passWordEditText.text.toString()).toString(), Toast.LENGTH_SHORT).show()
            if (!emailEditText.text.toString().isEmailValid()) {
                text_input_layout.error = "Bitte gib eine gültige E-mail Adresse ein"
            }else{
                text_input_layout.isErrorEnabled = false
                if (isValidPassword(passWordEditText.text.toString())){
                    showAlertDialog()
                }
            }
        }

    var lightMode = true
    var lightmodeButton = findViewById(R.id.lightmode) as Button

        lightmodeButton.setOnClickListener {
            if (!lightMode){
                AppCompatDelegate
                    .setDefaultNightMode(
                        AppCompatDelegate
                            .MODE_NIGHT_NO)
            }


            lightMode = !lightMode
        }


        var darkModeButton = findViewById(R.id.darkmode) as Button

        darkModeButton.setOnClickListener {
            if (lightMode){
                AppCompatDelegate
                    .setDefaultNightMode(
                        AppCompatDelegate
                            .MODE_NIGHT_YES)
            }

            lightMode = !lightMode
        }


    }


    private fun showAlertDialog() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle("Login Successfull")
        alertDialog.setMessage("click yes to close this popup")
        alertDialog.setPositiveButton(
            "yes"
        ) { _, _ ->

        }
        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }
    fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    fun isValidPassword(str :String): Boolean {

        var valid4 = true
        var valid1 = true
        var valid2 = true
        var valid3 = true


        if (str.length < 12) {
            valid1 = false
        }

        var exp = ".*[0-9].*"
        var pattern = Pattern.compile(exp, Pattern.CASE_INSENSITIVE)
        var matcher = pattern.matcher(str)
        if (!matcher.matches()) {
            valid2 = false
        }

        exp = ".*[A-Z].*"
        pattern = Pattern.compile(exp)
        matcher = pattern.matcher(str)
        if (!matcher.matches()) {
            valid3 = false
        }

        exp = ".*[~!@#\$%\\^&*()\\-_=+\\|\\[{\\]};:'\",<.>/?].*"
        pattern = Pattern.compile(exp)
        matcher = pattern.matcher(str)
        if (!matcher.matches()) {
            valid4 = false
        }

        return valid1 && valid4 && valid2 && valid3
    }





}
