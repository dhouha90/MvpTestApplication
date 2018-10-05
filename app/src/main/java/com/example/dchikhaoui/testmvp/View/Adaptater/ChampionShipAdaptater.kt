package com.example.dchikhaoui.testmvp.View.Adaptater

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import com.example.dchikhaoui.testmvp.Model.leagues
import com.example.dchikhaoui.testmvp.R
import com.example.dchikhaoui.testmvp.databinding.ItemChampionshipAdaptaterBinding


class ChampionShipAdaptater : BaseAdapter(), Filterable {
    val allLeagues: MutableList<leagues> = ArrayList()
    var mData: MutableList<String> = ArrayList()
    val mStringFilterList: MutableList<String> = ArrayList()
    var valueFilter: ValueFilter = ValueFilter()
    private lateinit var inflater: LayoutInflater


    fun addData(data: leagues) {
        allLeagues.add(data)
        mData.add(data.strLeague)
        mStringFilterList.add(data.strLeague)
    }

    override fun getItem(position: Int): Any {
        return mData[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mData.size
    }

    override fun getView(p0: Int, p1: View?, parent: ViewGroup?): View {
        inflater = parent!!.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val itemChampionshipAdaptaterBinding: ItemChampionshipAdaptaterBinding = DataBindingUtil.inflate(inflater, R.layout.item_championship_adaptater, parent, false)
        itemChampionshipAdaptaterBinding.leagues = allLeagues.get(p0)
        return itemChampionshipAdaptaterBinding.root
    }


    override fun getFilter(): Filter {
        return valueFilter
    }


    inner class ValueFilter : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val results = FilterResults()

            if (constraint != null && constraint.length > 0) {
                val filterList: java.util.ArrayList<String> = java.util.ArrayList()
                for (i in mStringFilterList.indices) {
                    if (mStringFilterList.get(i).toUpperCase().contains(constraint.toString().toUpperCase())) {
                        filterList.add(mStringFilterList.get(i))
                    }
                }
                results.count = filterList.size
                results.values = filterList
            } else {
                results.count = mStringFilterList.size
                results.values = mStringFilterList
            }
            return results

        }

        override fun publishResults(constraint: CharSequence,
                                    results: FilterResults) {
            mData = results.values as ArrayList<String>
            notifyDataSetChanged()
        }

    }
}