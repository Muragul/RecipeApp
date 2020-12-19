package com.example.recipeapp.ui.fragment.statistics

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recipeapp.R
import com.example.recipeapp.data.model.ColorConst
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlinx.android.synthetic.main.fragment_graph.view.*

class GraphFragment : Fragment() {
    private var list: ArrayList<PieEntry> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.fragment_graph, container, false) as ViewGroup
        list.add(PieEntry(25f, "Fats"))
        list.add(PieEntry(35f, "Proteins"))
        list.add(PieEntry(40f, "Carbohydrates"))
        val pieDataSet = PieDataSet(list, "Average Diet")
        pieDataSet.colors = ColorConst.colorsArray

        val pieData = PieData(pieDataSet)
        pieData.setValueTextSize(15f)
        pieData.setValueTextColor(Color.DKGRAY)
        rootView.pie_chart.setUsePercentValues(true)
        rootView.pie_chart.centerText = "Average"
        rootView.pie_chart.setCenterTextColor(Color.DKGRAY)
        rootView.pie_chart.setHoleColor(ColorConst.background)
        rootView.pie_chart.setCenterTextSize(20f)
        rootView.pie_chart.holeRadius = 40f
        rootView.pie_chart.transparentCircleRadius = 50f
        rootView.pie_chart.description.isEnabled = false
        rootView.pie_chart.data = pieData

        return rootView
    }
}