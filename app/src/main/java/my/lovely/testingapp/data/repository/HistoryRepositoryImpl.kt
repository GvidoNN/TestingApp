package my.lovely.testingapp.data.repository

import android.content.Context
import my.lovely.testingapp.domain.repository.HistoryRepository

private const val SHARED_PREFS_HISTORY = "HISTORY"

class HistoryRepositoryImpl(context: Context) : HistoryRepository {

    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_HISTORY, Context.MODE_PRIVATE)
    var historyList = arrayListOf<String>()

    override fun saveHistory(number: String) {
        historyList.add(number)
        if (historyList.size < 5) {
            sharedPreferences.edit().putString(SHARED_PREFS_HISTORY, historyList.toString()).apply()
        } else {
            historyList.removeAt(0)
            sharedPreferences.edit().putString(SHARED_PREFS_HISTORY, historyList.toString()).apply()
        }
    }

    override fun getHistory(): String? {

        val history = sharedPreferences.getString(SHARED_PREFS_HISTORY, "")
        return history
    }


}