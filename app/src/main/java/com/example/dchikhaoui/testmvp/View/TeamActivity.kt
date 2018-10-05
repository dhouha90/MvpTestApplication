package com.example.dchikhaoui.testmvp.View

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import com.example.dchikhaoui.testmvp.Model.Team
import com.example.dchikhaoui.testmvp.Presenter.TeamPresenter
import com.example.dchikhaoui.testmvp.R
import com.example.dchikhaoui.testmvp.Utils.StringUtils
import com.example.dchikhaoui.testmvp.View.Adaptater.TeamAdaptater

class TeamActivity : AppCompatActivity(), TeamPresenter.InterfaceView {


    var TAG: String = PlayerActivity::class.java.getSimpleName()
    var mTeamAdaptater = TeamAdaptater()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)
        var mChampionShipName: String = getIntent().getExtras().getString(StringUtils.CHAMPIONSHIP_NAME)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()!!.setTitle(mChampionShipName)

        val rv: RecyclerView = findViewById(R.id.list_team)
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = mTeamAdaptater
        TeamPresenter(this).getListTeam(mChampionShipName)
    }

    override fun displayListTeam(team: MutableList<Team>) {
        mTeamAdaptater.addItem(team)
        mTeamAdaptater.notifyDataSetChanged()

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
