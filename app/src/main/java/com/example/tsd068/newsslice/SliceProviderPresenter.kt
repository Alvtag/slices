package com.example.tsd068.newsslice

import com.example.tsd068.Logg
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by tsd068 on 2018-05-16.
 */
class SliceProviderPresenter(val view:SliceProviderView){


    fun fetchArticles(){

        val observable = ApiService.create().search(/* search params go in here */)
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    result ->
                    Logg.instance.d("Result", "There are ${result.articles.size} stories in this list")
                }, { error ->
                    error.printStackTrace()
                })
    }
}