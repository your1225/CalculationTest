package com.example.calculationtest

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import java.util.*

class MyViewModel(application: Application, var savedStateHandle: SavedStateHandle) :
    AndroidViewModel(application) {

    //    private lateinit var handle: SavedStateHandle
//    private lateinit var app: Application
    private val KEY_HIGH_SCORE = "key_high_score"
    private val KEY_LEFT_NUMBER = "key_left_number"
    private val KEY_RIGHT_NUMBER = "key_right_number"
    private val KEY_OPERATOR = "key_operator"
    private val KEY_ANSWER = "key_answer"
    private val SAVE_SHP_DATA_NAME = "save_shp_data_name"
    private val KEY_CURRENT_SCORE = "key_current_score"

    var win_flag: Boolean = false

//    companion object {
//
//    }

    init {
        if (!savedStateHandle.contains(KEY_HIGH_SCORE)) {
//        if (!application.getSharedPreferences(SAVE_SHP_DATA_NAME, Context.MODE_PRIVATE).contains(KEY_HIGH_SCORE)) {
            var shp: SharedPreferences =
                application.getSharedPreferences(SAVE_SHP_DATA_NAME, Context.MODE_PRIVATE)
            savedStateHandle.set(KEY_HIGH_SCORE, shp.getInt(KEY_HIGH_SCORE, 0))
            savedStateHandle.set(KEY_LEFT_NUMBER, 0)
            savedStateHandle.set(KEY_RIGHT_NUMBER, 0)
            savedStateHandle.set(KEY_OPERATOR, "+")
            savedStateHandle.set(KEY_ANSWER, 0)
            savedStateHandle.set(KEY_CURRENT_SCORE, 0)

//            Toast.makeText(
//                application.applicationContext,
//                savedStateHandle.get<Int>(KEY_CURRENT_SCORE).toString(),
//                Toast.LENGTH_LONG
//            ).show()
        }

//        this.handle = savedStateHandle
//        this.app = application
    }

    fun generator() {
        val LEVEL: Int = 50
        val random: Random = Random()
        val x: Int = random.nextInt(LEVEL) + 1
        val y: Int = random.nextInt(LEVEL) + 1

        if (x % 2 == 0) {
            operator.value = "+"
            if (x > y) {
                keyAnswer.value = x
                leftNumber.value = y
                rightNumber.value = x - y
            } else {
                keyAnswer.value = y
                leftNumber.value = x
                rightNumber.value = y - x
//                savedStateHandle.set(KEY_ANSWER, y)
            }
        } else {
            operator.value = "-"
            if (x > y) {
                keyAnswer.value = x - y
                leftNumber.value = x
                rightNumber.value = y
            } else {
                keyAnswer.value = y - x
                leftNumber.value = y
                rightNumber.value = x
            }
        }
    }

    fun save() {
        var shp: SharedPreferences = getApplication<Application>().getSharedPreferences(
            SAVE_SHP_DATA_NAME,
            Context.MODE_PRIVATE
        )
        var editor: SharedPreferences.Editor = shp.edit()
        editor.putInt(KEY_HIGH_SCORE, highScore.value!!)
        editor.apply()
    }

    fun answerCorrect() {
//        currentScore.postValue(currentScore.value!! + 1)

        currentScore.value = currentScore.value!!.plus(1)

        if (currentScore.value!! > highScore.value!!) {
            highScore.value = currentScore.value
            win_flag = true
        }
        generator()
    }

//    private var _left_numer = MutableLiveData<Int>().apply {
//        value = savedStateHandle.getLiveData<Int>(KEY_LEFT_NUMBER).value
//    }
//    var leftNumber: MutableLiveData<Int> = _left_numer

    var leftNumber = MutableLiveData<Int>().apply {
        value = savedStateHandle.getLiveData<Int>(KEY_LEFT_NUMBER).value
    }
    var rightNumber = MutableLiveData<Int>().apply {
        value = savedStateHandle.getLiveData<Int>(KEY_RIGHT_NUMBER).value
    }
    var highScore = MutableLiveData<Int>().apply {
        value = savedStateHandle.getLiveData<Int>(KEY_HIGH_SCORE).value
    }
    var currentScore = MutableLiveData<Int>().apply {
        value = savedStateHandle.getLiveData<Int>(KEY_CURRENT_SCORE).value
    }
    var keyAnswer = MutableLiveData<Int>().apply {
        value = savedStateHandle.getLiveData<Int>(KEY_ANSWER).value
    }
    var operator = MutableLiveData<String>().apply {
        value = savedStateHandle.getLiveData<String>(KEY_OPERATOR).value
    }
}