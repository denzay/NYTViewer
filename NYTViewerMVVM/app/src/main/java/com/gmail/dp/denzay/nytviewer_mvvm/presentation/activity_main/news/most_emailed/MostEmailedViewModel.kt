package com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.most_emailed

import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_emailed.MostEmailedUseCase
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.common.BaseNewsViewModel
import javax.inject.Inject

class MostEmailedViewModel @Inject constructor(
      //  private val router: MostEmailedContract.Router
    private val mostEmailedUseCase: MostEmailedUseCase
) : BaseNewsViewModel(), MostEmailedContract.ViewModel {

    override fun doLoadData() {

//        disposables.add(mostEmailedUseCase.getMostEmailedList()
//                .doOnSubscribe { _isLoading.value = true }
//                .flatMap {
//                    val list = mutableListOf<NewsItem>()
//                    it.map {
//                        val newsItem = NewsItem(it.id!!, it.url!!, it.title!!, it.shortDesc!!, "")
//                        list.add(newsItem)
//                    }
//                    return@flatMap Single.just(list)
//                }
//                .doFinally { _isLoading.value = false }
//                .subscribeBy(onSuccess = {
//                    newsList.postValue(it)
//                }))
    }

}