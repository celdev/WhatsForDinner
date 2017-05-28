package com.celdev.whatsfordinner

import android.graphics.Paint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*;

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setViewState()

        val restaurant = Restaurant("May's Favorite restaurant", "Cheap and tasty Thai food")
        setViewInformation(restaurant)
    }

    private fun setViewState() {
        restaurantNameBlack.paint.strokeWidth = 5.0f
        restaurantNameBlack.paint.style = Paint.Style.STROKE
    }


    fun setViewInformation(restaurant: Restaurant){
        restaurantNameBlack.text = restaurant.name
        restaurantNameWhite.text = restaurant.name
        descriptionText.text = restaurant.description
    }
}
