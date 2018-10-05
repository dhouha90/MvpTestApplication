package com.example.dchikhaoui.testmvp.View

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import com.example.dchikhaoui.testmvp.Model.Player
import com.example.dchikhaoui.testmvp.Presenter.PlayerPresenter
import com.example.dchikhaoui.testmvp.R
import com.example.dchikhaoui.testmvp.Utils.StringUtils
import com.example.dchikhaoui.testmvp.View.Adaptater.PlayerAdaptater


class PlayerActivity : AppCompatActivity(), PlayerPresenter.InterfaceView {
    var TAG: String = PlayerActivity::class.java.getSimpleName()
    var mPlayerAdaptater = PlayerAdaptater()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        var mClubName: String = getIntent().getExtras().getString(StringUtils.Team_NAME)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()!!.setTitle(mClubName)
        val rv: RecyclerView = findViewById(R.id.list_player)
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = mPlayerAdaptater
        PlayerPresenter(this).getListPlayer(mClubName)
    }

    override fun displayListPlayer(mPlayer: MutableList<Player>) {
        mPlayerAdaptater.addItem(mPlayer)
        mPlayerAdaptater.notifyDataSetChanged()
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