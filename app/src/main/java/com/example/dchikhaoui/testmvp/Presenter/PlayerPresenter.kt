package com.example.dchikhaoui.testmvp.Presenter

import com.example.dchikhaoui.testmvp.Model.AllPlayer
import com.example.dchikhaoui.testmvp.Model.Player
import com.example.dchikhaoui.testmvp.Network.HttpService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PlayerPresenter constructor(private val interfaceView: InterfaceView) {
    fun getListPlayer(mClubName: String) {
        HttpService().getClient()
                .searchPlayer(mClubName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(object : Observer<AllPlayer> {
                    override fun onSubscribe(d: Disposable?) {
                    }

                    override fun onComplete() {

                    }

                    override fun onError(throwable: Throwable) {}
                    override fun onNext(players: AllPlayer) {
                        var mAllPlayer: MutableList<Player> = players.player
                        interfaceView.displayListPlayer(mAllPlayer)
                    }
                })

    }

    interface InterfaceView {
        fun displayListPlayer(player: MutableList<Player>)
    }
}