package com.example.dchikhaoui.testmvp.Presenter

import com.example.dchikhaoui.testmvp.Model.AllTeam
import com.example.dchikhaoui.testmvp.Model.Team
import com.example.dchikhaoui.testmvp.Network.HttpService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TeamPresenter constructor(private val interfaceView: InterfaceView) {

    fun getListTeam(mTeamName: String) {
        HttpService().getClient()
                .searchTeam(mTeamName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(object : Observer<AllTeam> {
                    override fun onSubscribe(d: Disposable?) {
                    }

                    override fun onComplete() {

                    }

                    override fun onError(throwable: Throwable) {}
                    override fun onNext(team: AllTeam) {
                        var mAllTeam: MutableList<Team> = team.teams
                        interfaceView.displayListTeam(mAllTeam)
                    }
                })


    }

    interface InterfaceView {
        fun displayListTeam(team: MutableList<Team>)
    }
}