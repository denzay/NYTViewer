package com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.most_viewed

import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_viewed.MostViewedUseCase
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.common.BaseNewsViewModel
import javax.inject.Inject

class MostViewedViewModel @Inject constructor(
//        private val router: MostViewedContract.Router,
    private val mostViewedUseCase: MostViewedUseCase
) : BaseNewsViewModel(), MostViewedContract.ViewModel {

    override fun doLoadData() {
//        disposables.add(mostViewedUseCase.getMostViewedList()
//                .flatMap {
//                    val list = mutableListOf<NewsItem>()
//                    it.map {
//                        val newsItem = NewsItem(it.id!!, it.url!!, it.title!!, it.shortDesc!!, "")
//                        list.add(newsItem)
//                    }
//                    return@flatMap Single.just(list)
//                }
//                .subscribeBy(onSuccess = {
//                    newsList.postValue(it)
//                }))
    }

}

