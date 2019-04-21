package com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.most_shared

import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_shared.MostSharedUseCase
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.common.BaseNewsViewModel
import javax.inject.Inject

class MostSharedViewModel @Inject constructor(
//        private val router: MostSharedContract.Router,
    private val mostSharedUseCase: MostSharedUseCase
) : BaseNewsViewModel(), MostSharedContract.ViewModel {

    override fun doLoadData() {

//        disposables.add(mostSharedUseCase.getMostSharedList()
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