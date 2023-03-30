package com.example.texttospeech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    lateinit var etSpeak:EditText
    lateinit var btSpeak:Button
    lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etSpeak= findViewById(R.id.etSpeak)
        btSpeak=findViewById(R.id.btSpeak)

        btSpeak.isEnabled = false //initially it is set to false to check if the text to speech s supported by the device and for all languages
        tts = TextToSpeech(this, this)

        btSpeak.setOnClickListener { speakOut() }

    }

    private fun speakOut() {
        val text = etSpeak.text.toString()
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "") //queue flush means to flush out any remaining txt left in the queue
    }

    override fun onInit(status: Int) {
        if(status==TextToSpeech.SUCCESS){
            val result = tts.setLanguage(Locale.ENGLISH) //if the txt to speech and language is supported by the device then success
            if(result==TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA){
                //Toast
            }
            else{
                btSpeak.isEnabled=true // set the button to true if there is no error is the support of the text to speech with the device then set the button to true again
            }
        }
    }
}