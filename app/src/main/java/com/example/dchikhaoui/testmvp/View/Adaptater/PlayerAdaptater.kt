package com.example.dchikhaoui.testmvp.View.Adaptater

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.dchikhaoui.testmvp.Model.Player
import com.example.dchikhaoui.testmvp.R
import com.example.dchikhaoui.testmvp.databinding.ItemPlayerAdaptaterBinding
import java.util.*


class PlayerAdaptater : RecyclerView.Adapter<PlayerAdaptater.PlayerHolder>() {

    var mAllPlayer: MutableList<Player> = ArrayList()


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PlayerHolder {
        val layoutInflater = LayoutInflater.from(p0!!.context)
        val binding: ItemPlayerAdaptaterBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_player_adaptater, p0, false)
        return PlayerHolder(binding)
    }


    override fun onBindViewHolder(p0: PlayerHolder, p1: Int) {
        p0!!.feeditemBinding.player = mAllPlayer[p1]

    }


    override fun getItemCount(): Int {
        return mAllPlayer.size
    }


    fun addItem(data: MutableList<Player>) {
        mAllPlayer = data
    }

    class PlayerHolder(feeditemBinding: ItemPlayerAdaptaterBinding) : RecyclerView.ViewHolder(feeditemBinding.root) {
        val feeditemBinding: ItemPlayerAdaptaterBinding = feeditemBinding;
    }


}