package com.kamatiaakash.text_to_speech_using_jetpack_compose

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.util.*

class MainViewModel:ViewModel() {

    private val _state = mutableStateOf(MainScreenState())
    val state: State<MainScreenState> = _state
    private  var  textToSpeech:TextToSpeech? = null


    fun onTextFieldValueChange(text:String){
        _state.value = state.value.copy(
            text = text
        )
    }

    fun textToSpeech(context: Context){
        _state.value = state.value.copy(
            isButtonEnabled = false
        )
       textToSpeech = TextToSpeech(
            context
        ) {
            if (it == TextToSpeech.SUCCESS) {
                textToSpeech?.let { txtToSpeech ->
                    txtToSpeech.language = Locale.US
                    txtToSpeech.setSpeechRate(1.0f)
                    txtToSpeech.speak(
                        _state.value.text,
                        TextToSpeech.QUEUE_ADD,
                        null,
                        null
                    )
                }
            }
           _state.value = state.value.copy(
               isButtonEnabled = true
           )
        }
    }
}