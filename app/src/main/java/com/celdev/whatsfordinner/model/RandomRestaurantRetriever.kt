package com.celdev.whatsfordinner.model

import java.util.*


class RandomRestaurantRetriever(private val restaurants: Set<Restaurant>, private val random : Random = Random()) {
    init {
        if (restaurants.isEmpty()) {
            throw IllegalArgumentException("Restaurants can't be empty")
        }
    }

    fun getRandomRestaurant() : Restaurant {
        val amountOfRestaurants = restaurants.size
        return restaurants.toTypedArray()[random.nextInt(amountOfRestaurants)]
    }

}