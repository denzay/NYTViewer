package com.gmail.dp.denzay.nytviewer_mvvm.domain.most_shared

import com.gmail.dp.denzay.nytviewer_mvvm.domain.common.BaseUsecase
import io.reactivex.Single

interface MostSharedUseCase: BaseUsecase {

    fun getMostSharedList(): Single<List<MostSharedEntity>>
    
}