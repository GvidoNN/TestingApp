package my.lovely.testingapp.domain.models

class CardModel(
    val scheme: String,
    val type: String,
    val brand: String,
    val prepaid: Boolean,
    val length: Int,
    val luhn: Boolean,
    val alpha2: String,
    val countryName: String,
    val latitude: Int,
    val longitude: Int,
    val currency: String,
    val bankName: String,
    val url: String,
    val phone: String,
    val city: String
    )