package my.lovely.testingapp.domain.usecase

import my.lovely.testingapp.domain.repository.HistoryRepository

class GetHistoryUseCase(private val historyRepository: HistoryRepository) {

    fun getHistory(): String{
        return historyRepository.getHistory().toString()
    }

}