package com.example.dchikhaoui.testmvp.Network

import com.example.dchikhaoui.testmvp.Model.AllLeagues
import com.example.dchikhaoui.testmvp.Model.AllPlayer
import com.example.dchikhaoui.testmvp.Model.AllTeam
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface HttpInterface {
    @GET("search_all_teams.php")
    fun searchTeam(@Query("l") teamName: String): Observable<AllTeam>

    @GET("searchplayers.php")
    fun searchPlayer(@Query("t") eventName: String): Observable<AllPlayer>

    @GET("all_leagues.php")
    fun getListChampion(): Observable<AllLeagues>
}