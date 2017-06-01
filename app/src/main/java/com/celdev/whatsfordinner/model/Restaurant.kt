package com.celdev.whatsfordinner.model

import com.celdev.whatsfordinner.R
import com.celdev.whatsfordinner.TimeGiver
import com.celdev.whatsfordinner.TimeGiverImpl

class Restaurant(val id: Long,
                 val name: String,
                 val description: String,
                 val restaurantArea: String,
                 val restaurantFoodType: Set<RestaurantFoodType>,
                 val budgetType: Set<BudgetType>,
                 val MapPoint: MapPoint) {
    init {
        if (restaurantFoodType.isEmpty() || budgetType.isEmpty()) {
            throw IllegalArgumentException()
        }
    }

    private val THREE_HOURS = 1000 * 60 * 60 * 3

    private var blockedFor3Hours: Boolean = false
    private var blockedFor3HoursTime: Long = 0
    var blockedForever : Boolean = false
    var favorite : Boolean = false

    fun getIsBlocked(timeGiver: TimeGiver = TimeGiverImpl()) : Boolean {
        if(blockedForever){
            return true
        }
        if(blockedFor3Hours){
            if (checkShouldUnblock(timeGiver.getCurrentTimeMillis())) {
                removeTemporaryBlock()
                return false
            }
            return true
        }
        return false
    }

    private fun checkShouldUnblock(now : Long) : Boolean {
        return blockedFor3HoursTime + THREE_HOURS <= now
    }


    fun removeAllBlocks() {
        removeForeverBlock()
        removeTemporaryBlock()
    }

    fun getAllDrawable() : Array<Int> {
        return restaurantFoodType.map { restaurantFoodType -> restaurantFoodType.image }.toTypedArray()
    }

    fun getAllFoodTypeNames() : Array<String> {
        return restaurantFoodType.map { restaurantFoodType -> restaurantFoodType.foodTypeName }.toTypedArray()
    }

    fun setBlockedFor3Hours(timeGiver : TimeGiver = TimeGiverImpl()){
        blockedFor3Hours = true
        blockedFor3HoursTime = timeGiver.getCurrentTimeMillis()
    }

    fun removeForeverBlock(){
        blockedForever = false
    }

    fun removeTemporaryBlock(){
        blockedFor3Hours = false
        blockedFor3HoursTime = 0
    }

}

class MapPoint(val latitude : Double, val longitude : Double) {}


enum class RestaurantFoodType(val foodTypeName : String, val image: Int){

    THAI("Thai", R.drawable.food_type_thai),
    EUROPEAN("European", R.drawable.food_type_european),
    SWEDISH("Swedish", R.drawable.food_type_swedish),
    ASIAN("Asian", R.drawable.food_type_asian),
    JAPANESE("Japanese", R.drawable.food_type_japanese),
    BUFFET("Buffet", R.drawable.food_type_buffet),
    FAST_FOOD("Fast food", R.drawable.food_type_fast_food),
    STEAK("Steak", R.drawable.food_type_steak)

}

enum class BudgetType(val budgetType : String, val budgetInt : Int){
    CHEAP("Cheap", 0x0000),
    NORMAL("Average", 0x0010),
    EXPENSIVE("Expensive",0x0100),
    VERY_EXPENSIVE("Very expensive",0x1000)
}