package com.example.recipeapp.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import com.example.recipeapp.R
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlinx.android.synthetic.main.fragment_graph.view.*

class GraphFragment : Fragment() {
    lateinit var list: ArrayList<PieEntry>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_graph, container, false) as ViewGroup

        list = ArrayList()
        list.add(PieEntry(25f, "Fats"))
        list.add(PieEntry(35f, "Proteins"))
        list.add(PieEntry(40f, "Carbohydrates"))

        @ColorInt val carbohydrates = 0xFF7F95D1.toInt()
        @ColorInt val fats = 0xFFFFC0BE.toInt()
        @ColorInt val proteins = 0xFFFF82A9.toInt()

        val colorsArray: MutableList<Int> = ArrayList()
        colorsArray.add(fats)
        colorsArray.add(proteins)
        colorsArray.add(carbohydrates)

        val pieDataSet = PieDataSet(list, "Average Diet")
        pieDataSet.colors = colorsArray

        val pieData = PieData(pieDataSet)
        rootView.pie_chart.data = pieData

        return rootView
    }
}