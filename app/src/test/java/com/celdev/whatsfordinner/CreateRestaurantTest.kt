package com.celdev.whatsfordinner

import com.celdev.whatsfordinner.model.*
import org.junit.Assert.*
import org.junit.Test

class CreateRestaurantTest {

    @Test
    fun testCreateRestaurant(){
        val view = object : CreateRestaurantMVP.View {
            override fun successCreateRestaurant(id: Long) {
                assertEquals(id,0)
            }

            override fun getBudgetTypeInformation(): Set<BudgetType> {
                return setOf(BudgetType.CHEAP,BudgetType.EXPENSIVE)
            }

            override fun getRestaurantArea(): String {
                return "Patong"
            }

            override fun getMapPoint(): MapPoint {
                return MapPoint(10.0,10.0)
            }

            override fun getRestaurantName(): String {
                return "Only Cheese"
            }

            override fun getRestaurantDescription(): String {
                return "Very cheezy restaurant description"
            }

            override fun getRestaurantFoodTypes(): Set<RestaurantFoodType> {
                return setOf(RestaurantFoodType.ASIAN, RestaurantFoodType.FAST_FOOD, RestaurantFoodType.STEAK)
            }
        }
        val presenter = CreateRestaurantPresenter(view)
        presenter.createRestaurant()
    }

}