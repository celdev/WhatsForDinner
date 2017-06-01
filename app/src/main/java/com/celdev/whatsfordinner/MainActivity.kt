package com.celdev.whatsfordinner

import android.graphics.Paint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import com.celdev.whatsfordinner.model.*
import kotlinx.android.synthetic.main.activity_main.*;


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setViewState()


        var restaurant = Restaurant(
                0,
                "May's Favorite restaurant",
                "Cheap and tasty Thai food",
                "Patong",
                setOf(RestaurantFoodType.THAI),
                setOf(BudgetType.CHEAP),
                MapPoint(18.0,19.0)
        )
        restaurant.favorite = true
        restaurant = Restaurant(
                1,
                "Cheap steak restaurant",
                "Cheap steak, pasta and thai food",
                "Patong",
                setOf(RestaurantFoodType.EUROPEAN,RestaurantFoodType.THAI),
                setOf(BudgetType.CHEAP, BudgetType.NORMAL),
                MapPoint(18.0,19.0)
        )

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
        restaurantImage.setImageDrawable(ResourcesCompat.getDrawable(resources,restaurant.getAllDrawable()[0], null))
        if(restaurant.favorite){
            favoriteButton.text = getString(R.string.remove_from_favorite)
            favoriteButton.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_remove, 0, 0)
        }
    }


}
