package com.celdev.whatsfordinner

import com.celdev.whatsfordinner.model.Restaurant

class CreateRestaurantPresenter(val view : CreateRestaurantMVP.View) : CreateRestaurantMVP.Presenter {

    override fun createRestaurant() {
        val name = view.getRestaurantName()
        val description = view.getRestaurantDescription()
        val mapPoint = view.getMapPoint()
        val budgetTypeInformation = view.getBudgetTypeInformation()
        val restaurantArea = view.getRestaurantArea()
        val restaurantFoodTypes = view.getRestaurantFoodTypes()
        val restaurant = Restaurant(0, name, description, restaurantArea, restaurantFoodTypes, budgetTypeInformation, mapPoint)
        view.successCreateRestaurant(restaurant.id)
    }
}