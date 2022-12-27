package my.lovely.testingapp.domain.repository

interface HistoryRepository {

    fun saveHistory(number: String)

    fun getHistory() : String?

}