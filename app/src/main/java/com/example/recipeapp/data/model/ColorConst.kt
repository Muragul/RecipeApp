package com.example.recipeapp.data.model

import androidx.annotation.ColorInt

object ColorConst {

    val colorsArray: MutableList<Int> = ArrayList()

    @ColorInt
    val carbohydrates = 0xFF7F95D1.toInt()

    @ColorInt
    val fats = 0xFFFFC0BE.toInt()

    @ColorInt
    val proteins = 0xFFFF82A9.toInt()

    @ColorInt
    val background = 0xFFF6DED4.toInt()

    init {
        colorsArray.add(fats)
        colorsArray.add(proteins)
        colorsArray.add(carbohydrates)
    }

}