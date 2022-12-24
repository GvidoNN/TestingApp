package my.lovely.testingapp.domain.repository

interface CardRepository {

    suspend fun requestJson(number : String)

    fun parseCardData(result: String)
}