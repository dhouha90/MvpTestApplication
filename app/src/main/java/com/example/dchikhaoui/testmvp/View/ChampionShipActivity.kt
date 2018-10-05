package com.example.dchikhaoui.testmvp.View

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import com.example.dchikhaoui.testmvp.Model.leagues
import com.example.dchikhaoui.testmvp.Presenter.ChampionShipPresenter
import com.example.dchikhaoui.testmvp.R
import com.example.dchikhaoui.testmvp.Utils.StringUtils
import com.example.dchikhaoui.testmvp.View.Adaptater.ChampionShipAdaptater
import com.example.dchikhaoui.testmvp.databinding.ActivityChampionshipBinding


class ChampionShipActivity : AppCompatActivity(), ChampionShipPresenter.InterfaceView {


    lateinit var mActivity: ActivityChampionshipBinding
    var adapter: ChampionShipAdaptater = ChampionShipAdaptater()
    var TAG: String = PlayerActivity::class.java.getSimpleName()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = DataBindingUtil.setContentView(this, R.layout.activity_championship)
        mActivity.search.setActivated(true)
        mActivity.search.setQueryHint(getResources().getString(R.string.championShip_Placeholder))
        mActivity.search.onActionViewExpanded();
        mActivity.search.setIconified(false)
        mActivity.search.clearFocus()
        var searchView: SearchView = findViewById(R.id.search)
        ChampionShipPresenter(this).getListChampion()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            @RequiresApi(Build.VERSION_CODES.M)
            override fun onQueryTextChange(newText: String): Boolean {
                searchChampshionShip(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

        })
    }

    fun searchChampshionShip(newText: String) {
        adapter.getFilter().filter(newText)
        mActivity.listView.setAdapter(adapter)
        mActivity.listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(applicationContext, TeamActivity::class.java)
            val bundle = Bundle()
            bundle.putString(StringUtils.CHAMPIONSHIP_NAME, adapter.mData[position])
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    override fun displayListChampionship(mChampionship: leagues) {
        adapter.addData(mChampionship)
        adapter.notifyDataSetChanged()
    }

}