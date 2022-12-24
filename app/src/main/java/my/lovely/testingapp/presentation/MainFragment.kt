package my.lovely.testingapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.launch
import my.lovely.testingapp.R
import my.lovely.testingapp.data.repository.CardRepositoryImpl
import my.lovely.testingapp.domain.models.CardModel
import my.lovely.testingapp.domain.repository.CardRepository
import org.json.JSONObject

class MainFragment: Fragment(R.layout.main_fragment) {

    private lateinit var btDone: Button
    private val cardRepository by lazy { CardRepositoryImpl(context = requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        OnClickButton()
    }

    private fun OnClickButton(){
        btDone = requireView().findViewById(R.id.btDone)
        btDone.setOnClickListener{
            lifecycleScope.launch{
                cardRepository.requestJson("45717360")
            }

        }


    }
    //    45717360

//    private fun parseCardData(result: String){
//        val mainObject = JSONObject(result)
//        val card = CardModel(
//            mainObject.getString("scheme"),
//            mainObject.getString("type"),
//            mainObject.getString("brand"),
//            mainObject.getBoolean("prepaid"),
//            mainObject.getJSONObject("number").getInt("length"),
//            mainObject.getJSONObject("number").getBoolean("luhn"),
//            mainObject.getJSONObject("country").getString("alpha2"),
//            mainObject.getJSONObject("country").getString("name"),
//            mainObject.getJSONObject("country").getInt("latitude"),
//            mainObject.getJSONObject("country").getInt("longitude"),
//            mainObject.getJSONObject("country").getString("currency"),
//            mainObject.getJSONObject("bank").getString("name"),
//            mainObject.getJSONObject("bank").getString("url"),
//            mainObject.getJSONObject("bank").getString("phone"),
//            mainObject.getJSONObject("bank").getString("city"),)
//        Log.d("MyLog", "sheme : ${card.scheme} ")
//        Log.d("MyLog", "type : ${card.type} ")
//        Log.d("MyLog", "prepaid : ${card.prepaid} ")
//
//    }
}