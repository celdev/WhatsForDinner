package com.celdev.whatsfordinner

import com.celdev.whatsfordinner.model.*
import com.github.javafaker.Faker
import org.junit.Before
import org.junit.BeforeClass

class TestObjectCreator {

    companion object {
        private val faker : Faker = Faker()

        fun createXamountOfRandomRestaurants(amountOfRestaurants : Int = 2) : List<Restaurant> {
            var i = 0
            val restaurants = ArrayList<Restaurant>()
            while (i < amountOfRestaurants){
                restaurants.add(createRestaurant(i.toLong()))
                i++
            }
            return restaurants
        }


        fun createRestaurant(id : Long = 0) : Restaurant {
            return Restaurant(
                    id, faker.company().name(),
                    faker.lorem().characters(20,80),
                    RestaurantArea.values()[faker.random().nextInt(RestaurantArea.values().size)],
                    getRandomRestaurantFoodType(3),
                    setOf(BudgetType.values()[faker.random().nextInt(BudgetType.values().size)]),
                    MapPoint(10.0,10.0)
            )
        }

        private fun getRandomRestaurantFoodType(amount : Int): Set<RestaurantFoodType> {
            val foodSize = RestaurantFoodType.values().size
            val useSize : Int
            if(amount > foodSize) {
                useSize = foodSize
            } else{
                useSize = amount
            }
            val foodTypes = HashSet<RestaurantFoodType>()
            var i = 0
            while (i >= 0 && foodTypes.size < useSize){
                foodTypes.add(RestaurantFoodType.values()[faker.random().nextInt(foodSize)])
                i--
            }
            return foodTypes
        }



    }




}
