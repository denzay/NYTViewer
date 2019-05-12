package com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.most_viewed

import com.gmail.dp.denzay.nytviewer_mvvm.domain.most_viewed.MostViewedUseCase
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.activity_main.news.common.BaseNewsViewModel
import com.gmail.dp.denzay.nytviewer_mvvm.presentation.common.models.NewsItem
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MostViewedViewModel @Inject constructor(
//        private val router: MostViewedContract.Router,
    private val mostViewedUseCase: MostViewedUseCase
) : BaseNewsViewModel(), MostViewedContract.ViewModel {

    override fun doLoadData() {
        disposables.add(mostViewedUseCase.getMostViewedList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.value = true }
                .flatMap {
                    val list = mutableListOf<NewsItem>()
                    it.map {mostViewedModel ->
                        val newsItem = NewsItem(mostViewedModel.id!!, mostViewedModel.url!!, mostViewedModel.title!!, mostViewedModel.shortDesc!!, mostViewedModel.getFirstImageInfo()?.url)
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

