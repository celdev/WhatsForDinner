package com.celdev.whatsfordinner

import android.graphics.Paint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_create_restaurant.*

class CreateRestaurantActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_restaurant)
        initImage()
    }

    fun initImage(){
        restaurantNameBlackPlaceholder.paint.strokeWidth = 5.0f
        restaurantNameBlackPlaceholder.paint.style = Paint.Style.STROKE
        restaurantNameField.addTextChangedListener(RestaurantTextOnChangeListener(restaurantNameWhitePlaceholder,restaurantNameBlackPlaceholder))
    }

}
