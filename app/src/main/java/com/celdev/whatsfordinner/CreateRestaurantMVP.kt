package com.celdev.whatsfordinner

import com.celdev.whatsfordinner.model.BudgetType
import com.celdev.whatsfordinner.model.MapPoint
import com.celdev.whatsfordinner.model.Restaurant
import com.celdev.whatsfordinner.model.RestaurantFoodType


interface CreateRestaurantMVP {

    interface View {
        fun successCreateRestaurant(id : Long)
        fun getBudgetTypeInformation() : Set<BudgetType>
        fun getRestaurantArea() : String
        fun getMapPoint() : MapPoint
        fun getRestaurantName() : String
        fun getRestaurantDescription() : String
        fun getRestaurantFoodTypes() : Set<RestaurantFoodType>
    }

    interface Presenter {
        fun createRestaurant()
    }

}