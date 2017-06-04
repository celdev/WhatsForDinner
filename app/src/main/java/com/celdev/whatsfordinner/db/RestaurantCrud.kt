package com.celdev.whatsfordinner.db

import com.celdev.whatsfordinner.model.Restaurant

interface RestaurantCrud {

    fun addRestaurant(restaurant: Restaurant)

    fun getRestaurantById(id: Int) : Restaurant?

    fun getAllRestaurants() : List<Restaurant>

    fun getRestaurantCount() : Int

    fun updateRestaurant(restaurant : Restaurant)

    fun deleteRestaurant(restaurant: Restaurant)

}