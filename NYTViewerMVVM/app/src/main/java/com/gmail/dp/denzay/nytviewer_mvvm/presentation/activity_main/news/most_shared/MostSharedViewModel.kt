package com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.most_shared

import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_shared.MostSharedUseCase
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.common.BaseNewsViewModel
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.common.models.NewsItem
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MostSharedViewModel @Inject constructor(
//        private val router: MostSharedContract.Router,
    private val mostSharedUseCase: MostSharedUseCase
) : BaseNewsViewModel(), MostSharedContract.ViewModel {

    override fun doLoadData() {
        disposables.add(mostSharedUseCase.getMostSharedList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
                .flatMap {
                    val list = mutableListOf<NewsItem>()
                    it.map {mostSharedModel ->
                        val newsItem = NewsItem(mostSharedModel.id!!, mostSharedModel.url!!, mostSharedModel.title!!, mostSharedModel.shortDesc!!, mostSharedModel.getFirstImageInfo()?.url)
                        list.add(newsItem)
                    }
                    return@flatMap Single.just(list)
                }
                .subscribeBy(onSuccess = {
                    newsList.postValue(it)
                },
                    onError = this::processResponseError
                )
        )
    }
}