package com.example.dchikhaoui.testmvp.View.Adaptater

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dchikhaoui.testmvp.Model.Team
import com.example.dchikhaoui.testmvp.R
import com.example.dchikhaoui.testmvp.Utils.StringUtils
import com.example.dchikhaoui.testmvp.View.PlayerActivity
import com.example.dchikhaoui.testmvp.databinding.ItemTeamAdaptaterBinding


class TeamAdaptater : RecyclerView.Adapter<TeamAdaptater.TeamHolder>() {

    var mAllTeam: MutableList<Team> = ArrayList()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TeamHolder {
        val layoutInflater = LayoutInflater.from(p0!!.context)
        val binding: ItemTeamAdaptaterBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_team_adaptater, p0, false)
        return TeamHolder(binding)
    }

    override fun onBindViewHolder(p0: TeamHolder, p1: Int) {
        p0!!.feeditemBinding.team = mAllTeam[p1]
        p0.item.setOnClickListener(View.OnClickListener {
            val intent = Intent(it.context, PlayerActivity::class.java)
            val bundle = Bundle()
            bundle.putString(StringUtils.Team_NAME, mAllTeam[p1].strTeam)
            intent.putExtras(bundle)
            it.context.startActivity(intent)
        })
    }


    override fun getItemCount(): Int {
        return mAllTeam.size
    }

    fun addItem(data: MutableList<Team>) {
        mAllTeam = data
    }

    class TeamHolder(feeditemBinding: ItemTeamAdaptaterBinding) : RecyclerView.ViewHolder(feeditemBinding.root) {
        val feeditemBinding: ItemTeamAdaptaterBinding = feeditemBinding;
        val item: View = feeditemBinding.root
    }


}