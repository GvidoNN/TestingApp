package my.lovely.testingapp.data.repository

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import my.lovely.testingapp.domain.models.CardModel
import my.lovely.testingapp.domain.repository.CardRepository
import org.json.JSONObject

class CardRepositoryImpl(private val context: Context): CardRepository {

    override suspend fun requestJson(number : String) {
        val url = "https://lookup.binlist.net/" + number
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                    result -> parseCardData(result)
            },
            {
                    error -> Log.d("MyLog","$error")
            }
        )
        queue.add(request)
    }

    override fun parseCardData(result: String){
        val mainObject = JSONObject(result)
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
            mainObject.getJSONObject("bank").getString("name"),
            mainObject.getJSONObject("bank").getString("url"),
            mainObject.getJSONObject("bank").getString("phone"),
            mainObject.getJSONObject("bank").getString("city"),)
        Log.d("MyLog", "sheme : ${card.scheme} ")
        Log.d("MyLog", "type : ${card.type} ")
        Log.d("MyLog", "prepaid : ${card.prepaid} ")

    }
}