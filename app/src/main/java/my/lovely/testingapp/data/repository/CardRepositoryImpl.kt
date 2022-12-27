package my.lovely.testingapp.data.repository

import android.content.Context
import android.util.Log
import android.widget.EditText
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import my.lovely.testingapp.R
import my.lovely.testingapp.domain.models.CardModel
import my.lovely.testingapp.domain.repository.CardRepository
import my.lovely.testingapp.presentation.MainFragmentViewModel
import org.json.JSONObject
import java.lang.Exception

class CardRepositoryImpl(private val context: Context, private val model: MainFragmentViewModel) :
    CardRepository {

    override suspend fun requestJson(number: String, editText: EditText) {
        val url = "https://lookup.binlist.net/" + number
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET,
            url,
            { result ->
                parseCardData(result)
                val cardInfo = parseCardData(result)
            },
            { error ->
                editText.setError(context.getString(R.string.error))
            }
        )
        queue.add(request)
    }

    override fun parseCardData(result: String) {
        val mainObject = JSONObject(result)
        try {
            val card = CardModel(
                mainObject.getString("scheme"),
                mainObject.getString("type"),
                mainObject.getString("brand"),
                mainObject.getBoolean("prepaid"),
                mainObject.getJSONObject("number").getInt("length"),
                mainObject.getJSONObject("number").getBoolean("luhn"),
                mainObject.getJSONObject("country").getString("alpha2"),
                mainObject.getJSONObject("country").getString("name"),
                mainObject.getJSONObject("country").getInt("latitude"),
                mainObject.getJSONObject("country").getInt("longitude"),
                mainObject.getJSONObject("country").getString("currency"),
                (if (mainObject.getJSONObject("bank").has("name")) {
                    (mainObject.getJSONObject("bank").getString("name"))
                } else {
                }).toString(),
                (if (mainObject.getJSONObject("bank").has("url")) {
                    (mainObject.getJSONObject("bank").getString("url"))
                } else {
                }).toString(),
                (if (mainObject.getJSONObject("bank").has("phone")) {
                    (mainObject.getJSONObject("bank").getString("phone"))
                } else {
                }).toString(),
                (if (mainObject.getJSONObject("bank").has("city")) {
                    (mainObject.getJSONObject("bank").getString("city"))
                } else {
                }).toString()
            )
            model.liveDataCurrent.value = card
        } catch (e: Exception) {
            Log.d("MyLog", "Exeption")
        }
    }
}