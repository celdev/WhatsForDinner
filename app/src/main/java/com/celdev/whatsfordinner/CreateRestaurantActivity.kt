package com.celdev.whatsfordinner

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.celdev.whatsfordinner.model.RestaurantArea
import kotlinx.android.synthetic.main.activity_create_restaurant.*

class CreateRestaurantActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_restaurant)
        initSpinner()
        initImage()
    }

    fun initImage(){

        restaurantNameField.addTextChangedListener(RestaurantTextOnChangeListener())
        restaurantNameBlackPlaceholder
        restaurantNameWhitePlaceholder

    }

    fun initSpinner() {
        val arrayAdapter = ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                RestaurantArea.values().mapIndexed { _, restaurantArea -> restaurantArea.areaName }.toTypedArray())
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        restaurantAreaSpinner.adapter = arrayAdapter
    }
}
