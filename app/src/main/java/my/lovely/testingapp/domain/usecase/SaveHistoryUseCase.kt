package my.lovely.testingapp.domain.usecase

import my.lovely.testingapp.domain.repository.HistoryRepository

class SaveHistoryUseCase(private val historyRepository: HistoryRepository) {

    fun saveHistory(number: String){
        val result = historyRepository.saveHistory(number = number)
        return result
    }

}