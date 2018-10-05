package com.example.dchikhaoui.testmvp.Presenter

import com.example.dchikhaoui.testmvp.Model.leagues
import com.example.dchikhaoui.testmvp.Network.HttpService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class ChampionShipPresenter constructor(private val interfaceView: InterfaceView) {
    fun getListChampion() {
        HttpService().getClient()
                .getListChampion()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .flatMapIterable { items -> items.leagues }
                .subscribe(object : Observer<leagues> {
                    override fun onSubscribe(d: Disposable?) {
                    }

                    override fun onComplete() {

                    }

                    override fun onError(throwable: Throwable) {
                    }

                    override fun onNext(championShips: leagues) {
                        interfaceView.displayListChampionship(championShips)
                    }
                })

    }


    interface InterfaceView {
        fun displayListChampionship(mLeagues: leagues)
    }

}