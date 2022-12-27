package my.lovely.testingapp.domain.repository

import android.widget.EditText

interface CardRepository {

    suspend fun requestJson(number : String, editText: EditText)

    fun parseCardData(result: String)
}