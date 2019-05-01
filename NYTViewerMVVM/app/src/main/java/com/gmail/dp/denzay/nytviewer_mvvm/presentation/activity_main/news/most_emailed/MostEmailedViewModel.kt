package com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.most_emailed

import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_emailed.MostEmailedUseCase
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.common.BaseNewsViewModel
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.common.models.NewsItem
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MostEmailedViewModel @Inject constructor(
//    private val router: MostEmailedContract.Router,
    private val mostEmailedUseCase: MostEmailedUseCase
) : BaseNewsViewModel(), MostEmailedContract.ViewModel {

    override fun doLoadData() {
        disposables.add(mostEmailedUseCase.getMostEmailedList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { isLoading.value = true }
                .flatMap {
                    val list = mutableListOf<NewsItem>()
                    it.map { mostEmailedModel->
                        val newsItem = NewsItem(mostEmailedModel.id!!, mostEmailedModel.url!!, mostEmailedModel.title!!, mostEmailedModel.shortDesc!!, mostEmailedModel.getFirstImageInfo()?.url)
                        list.add(newsItem)
                    }
                    return@flatMap Single.just(list)
                }
                .doFinally { isLoading.value = false }
                .subscribeBy(onSuccess = {
                    newsList.postValue(it)
                },
                    onError = this::processResponseError
                )
        )
    }

}