package com.celdev.whatsfordinner.model

import com.celdev.whatsfordinner.R

class Restaurant(val id: Long,
                 val name: String,
                 val description: String,
                 val restaurantArea: RestaurantArea,
                 val restaurantFoodType: Set<RestaurantFoodType>,
                 val budgetType: Set<BudgetType>,
                 val MapPoint: MapPoint) {
    init {
        if (restaurantFoodType.isEmpty() || budgetType.isEmpty()) {
            throw IllegalArgumentException()
        }
    }

    var blockedFor3Hours: Boolean = false
    var blockedFor3HoursTime: Long = 0
    var blockedForever : Boolean = false
    var favorite : Boolean = false

    fun getAllDrawable() : Array<Int> {
        return restaurantFoodType.map { restaurantFoodType -> restaurantFoodType.image }.toTypedArray()
    }

    fun getAllNames() : Array<String> {
        return restaurantFoodType.map { restaurantFoodType -> restaurantFoodType.foodTypeName }.toTypedArray()
    }

    fun setBlockedFor3Hours(){
        blockedFor3Hours = true
        blockedFor3HoursTime = System.currentTimeMillis()
    }

    fun removeForeverBlock(){
        blockedForever = false
        blockedFor3Hours = false
        blockedFor3HoursTime = 0
    }

    fun removeTemporaryBlock(){
        blockedFor3Hours = false
        blockedFor3HoursTime = 0
    }

}

class MapPoint(val latitude : Double, val longitude : Double) {}

enum class RestaurantArea(val areaName : String) {

    KAMALA("Kamala"),
    PATONG("Patong"),
    KATHU("Kathu"),
    KARON("Karon"),
    BANG_TAO("Bang Tao"),
    PHUKET_TOWN("Phuket Town")

}

enum class RestaurantFoodType(val foodTypeName : String, val image: Int){

    THAI("Thai", R.drawable.food_type_thai),
    EUROPEAN("European", R.drawable.food_type_european),
    SWEDISH("Swedish", R.drawable.food_type_swedish),
    ASIAN("Asian", R.drawable.food_type_asian),
    JAPANESE("Japanese", R.drawable.food_type_japanese),
    BUFFET("Buffet", R.drawable.food_type_buffet),
    FAST_FOOD("Fast food", R.drawable.food_type_fast_food)

}

enum class BudgetType(val budgetType : String, val budgetInt : Int){
    CHEAP("Cheap", 0x0000),
    NORMAL("Average", 0x0010),
    EXPENSIVE("Expensive",0x0100),
    VERY_EXPENSIVE("Very expensive",0x1000)
}