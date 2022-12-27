package my.lovely.testingapp.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import my.lovely.testingapp.R
import my.lovely.testingapp.data.repository.CardRepositoryImpl
import my.lovely.testingapp.data.repository.HistoryRepositoryImpl
import my.lovely.testingapp.domain.usecase.GetHistoryUseCase
import my.lovely.testingapp.domain.usecase.SaveHistoryUseCase

class MainFragment : Fragment(R.layout.main_fragment) {

    lateinit var tvScheme: TextView
    lateinit var tvType: TextView
    lateinit var tvBrand: TextView
    lateinit var tvPrepaid: TextView
    lateinit var tvLength: TextView
    lateinit var tvLuhn: TextView
    lateinit var tvAlpha2: TextView
    lateinit var tvCountryName: TextView
    lateinit var tvLatitude: TextView
    lateinit var tvLongitude: TextView
    lateinit var tvCurrency: TextView
    lateinit var tvBankName: TextView
    lateinit var tvUrl: TextView
    lateinit var tvPhone: TextView
    lateinit var tvCity: TextView
    lateinit var btDone: Button
    lateinit var edCardNumber: EditText
    lateinit var tvHistory: TextView
    private val model: MainFragmentViewModel by activityViewModels()
    private val cardRepository by lazy {
        CardRepositoryImpl(
            context = requireContext(),
            model = model
        )
    }
    private val historyRepository by lazy { HistoryRepositoryImpl(context = requireContext()) }
    private val getHistoryUseCase by lazy { GetHistoryUseCase(historyRepository = historyRepository) }
    private val saveHistoryUseCase by lazy { SaveHistoryUseCase(historyRepository = historyRepository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        OnClickButton()
        findViews()
        tvHistory.text = getHistoryUseCase.getHistory()
    }

    private fun OnClickButton() {
        btDone = requireView().findViewById(R.id.btDone)
        btDone.setOnClickListener {
            lifecycleScope.launch {
                val number = edCardNumber.text.toString()
                if(number.isNotEmpty()){
                    saveHistoryUseCase.saveHistory(number = edCardNumber.text.toString())
                    tvHistory.text = getHistoryUseCase.getHistory()
                    cardRepository.requestJson(number, edCardNumber)
                    updateCurrentCard()
                }
            }
        }
    }

    private fun updateCurrentCard() {
        model.liveDataCurrent.observe(viewLifecycleOwner) {
            val gmmIntentUri = Uri.parse("google.streetview:cbll=${it.latitude},${it.longitude}")
            tvScheme.text = it.scheme
            tvType.text = it.type
            tvBrand.text = it.brand
            if (it.prepaid.toString() == "true") tvPrepaid.text =
                getString(R.string.yes) else tvPrepaid.text = getString(R.string.no)
            tvLength.text = it.length.toString()
            if (it.luhn.toString() == "true") tvLuhn.text =
                getString(R.string.yes) else tvLuhn.text = getString(R.string.no)
            tvAlpha2.text = it.alpha2
            tvCountryName.text = it.countryName
            tvLatitude.text = it.latitude.toString()
            tvLatitude.setOnClickListener {
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                startActivity(mapIntent)
            }
            tvLongitude.text = it.longitude.toString()
            tvLongitude.setOnClickListener {
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                startActivity(mapIntent)
            }
            tvCurrency.text = it.currency
            tvBankName.text = it.bankName
            tvUrl.text = it.url
            if (it.phone == "kotlin.Unit") tvPhone.text = getString(R.string.no) else tvPhone.text =
                it.phone
            if (it.city == "kotlin.Unit") tvCity.text = getString(R.string.no) else tvCity.text =
                it.city
            tvCity.setOnClickListener {
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                startActivity(mapIntent)
            }
        }
    }

    private fun findViews() {
        tvHistory = requireView().findViewById(R.id.tvHistory)
        tvScheme = requireView().findViewById(R.id.tvScheme)
        tvType = requireView().findViewById(R.id.tvType)
        tvBrand = requireView().findViewById(R.id.tvBrand)
        tvPrepaid = requireView().findViewById(R.id.tvPrepaid)
        tvLength = requireView().findViewById(R.id.tvLength)
        tvLuhn = requireView().findViewById(R.id.tvLuhn)
        tvAlpha2 = requireView().findViewById(R.id.tvAlpha2)
        tvCountryName = requireView().findViewById(R.id.tvCountryName)
        tvLatitude = requireView().findViewById(R.id.tvLatitude)
        tvLongitude = requireView().findViewById(R.id.tvLongitude)
        tvCurrency = requireView().findViewById(R.id.tvCurrency)
        tvBankName = requireView().findViewById(R.id.tvBankName)
        tvUrl = requireView().findViewById(R.id.tvUrl)
        tvPhone = requireView().findViewById(R.id.tvPhone)
        tvCity = requireView().findViewById(R.id.tvCity)
        edCardNumber = requireView().findViewById(R.id.edCardNumber)
    }
    //    45717360

}