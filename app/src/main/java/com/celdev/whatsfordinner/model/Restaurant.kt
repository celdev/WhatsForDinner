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

    constructor(
            id: Long, name: String, description: String, restaurantArea: String,
            restaurantFoodType: Set<RestaurantFoodType>, budgetType: Set<BudgetType>,
            mapPoint: MapPoint, secBlockedFor3Hours: Boolean, secBlockedForever: Boolean,
            secBlockedFor3HoursTime: Long
    ) : this(id, name, description, restaurantArea, restaurantFoodType, budgetType, mapPoint) {
        blockedFor3Hours = secBlockedFor3Hours
        blockedForever = secBlockedForever
        blockedFor3HoursTime = secBlockedFor3HoursTime
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

    fun getAllFoodTypeNames() : Array<Int> {
        return restaurantFoodType.map { restaurantFoodType -> restaurantFoodType.foodTypeNameRes }.toTypedArray()
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Restaurant

        if (id != other.id) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (restaurantArea != other.restaurantArea) return false
        if (restaurantFoodType != other.restaurantFoodType) return false
        if (budgetType != other.budgetType) return false
        if (MapPoint != other.MapPoint) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + restaurantArea.hashCode()
        result = 31 * result + restaurantFoodType.hashCode()
        result = 31 * result + budgetType.hashCode()
        result = 31 * result + MapPoint.hashCode()
        return result
    }


}

class MapPoint(val latitude : Double, val longitude : Double)


enum class RestaurantFoodType(val foodTypeName : String, val foodTypeNameRes: Int, val image: Int, val checkBoxResId : Int){

    THAI("thai",R.string.food_type_thai, R.drawable.food_type_thai, R.id.checkbox_thai),
    EUROPEAN("european",R.string.food_type_european, R.drawable.food_type_european, R.id.checkbox_european),
    SWEDISH("swedish",R.string.food_type_swedish, R.drawable.food_type_swedish, R.id.checkbox_swedish),
    ASIAN("asian",R.string.food_type_asian, R.drawable.food_type_asian, R.id.checkbox_asian),
    JAPANESE("japanese",R.string.food_type_japanese, R.drawable.food_type_japanese, R.id.checkbox_japanese),
    BUFFET("buffet",R.string.food_type_buffet, R.drawable.food_type_buffet, R.id.checkbox_buffet),
    FAST_FOOD("fast_food",R.string.food_type_fast_food, R.drawable.food_type_fast_food, R.id.checkbox_fast_food),
    STEAK("steak",R.string.food_type_steak, R.drawable.food_type_steak, R.id.checkbox_steak);

    companion object {
        fun fromDbToSet(string : String) : Set<RestaurantFoodType> {
            val set = HashSet<RestaurantFoodType>()
            string.split(",").forEach { s: String ->
                when (s) {
                    THAI.foodTypeName -> set.add(THAI)
                    EUROPEAN.foodTypeName -> set.add(EUROPEAN)
                    SWEDISH.foodTypeName -> set.add(SWEDISH)
                    ASIAN.foodTypeName -> set.add(ASIAN)
                    JAPANESE.foodTypeName -> set.add(JAPANESE)
                    BUFFET.foodTypeName -> set.add(BUFFET)
                    FAST_FOOD.foodTypeName -> set.add(FAST_FOOD)
                    STEAK.foodTypeName -> set.add(STEAK)
                }
            }
            return set
        }
        fun fromSetToDbString(set : Set<RestaurantFoodType>) : String {
            return set.map { restaurantFoodType -> restaurantFoodType.foodTypeName }.joinToString(",")
        }
    }
}

enum class BudgetType(val budgetType : String, val budgetInt : Int, val bugetCheckboxResId : Int){
    CHEAP("Cheap", 0x0000, R.id.checkbox_cheap),
    NORMAL("Average", 0x0010, R.id.checkbox_normal),
    EXPENSIVE("Expensive",0x0100, R.id.checkbox_expensive),
    VERY_EXPENSIVE("Very expensive",0x1000, R.id.checkbox_very_expensive);

    companion object {
        fun fromDbToSet(string : String) : Set<BudgetType>{
            val set = HashSet<BudgetType>()
            string.split(",").forEach { s: String ->
                when(s){
                    CHEAP.budgetType -> set.add(CHEAP)
                    NORMAL.budgetType -> set.add(NORMAL)
                    EXPENSIVE.budgetType -> set.add(EXPENSIVE)
                    VERY_EXPENSIVE.budgetType -> set.add(VERY_EXPENSIVE)
                }
            }
            return set
        }
        fun fromSetToDbString(set : Set<BudgetType>) : String {
            return set.map { budgetType -> budgetType.budgetType }.joinToString(",")
        }
    }

}