package com.celdev.whatsfordinner

import com.celdev.whatsfordinner.RestaurantDBTests.FoodTypeTests
import com.celdev.whatsfordinner.model.BudgetType
import com.celdev.whatsfordinner.model.BudgetType.*
import com.celdev.whatsfordinner.model.RestaurantFoodType
import com.celdev.whatsfordinner.model.RestaurantFoodType.*
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith


class RestaurantDBTests{

    class FoodTypeTests{

        @Test
        fun testToDbStringOne() {
            val set = setOf(THAI)
            val expectedRes = THAI.foodTypeName
            assertEquals(expectedRes,RestaurantFoodType.fromSetToDbString(set))
        }

        @Test
        fun testToDbStringOneDuplicates() {
            val set = setOf(THAI, THAI)
            val expectedRes = THAI.foodTypeName
            assertEquals(expectedRes,RestaurantFoodType.fromSetToDbString(set))
        }

        @Test
        fun testToDbStringTwo() {
            val set = setOf(THAI,SWEDISH)
            val expectedRes = THAI.foodTypeName + "," + SWEDISH.foodTypeName
            assertEquals(expectedRes,RestaurantFoodType.fromSetToDbString(set))
        }

        @Test
        fun testToDbStringTwoDuplicates() {
            val set = setOf(THAI,SWEDISH, THAI)
            val expectedRes = THAI.foodTypeName + "," + SWEDISH.foodTypeName
            assertEquals(expectedRes,RestaurantFoodType.fromSetToDbString(set))
        }



        @Test
        fun testToDbStringFour() {
            val set = setOf(THAI,SWEDISH,BUFFET,ASIAN)
            val expectedRes = THAI.foodTypeName + "," + SWEDISH.foodTypeName + "," + BUFFET.foodTypeName + "," + ASIAN.foodTypeName
            assertEquals(expectedRes,RestaurantFoodType.fromSetToDbString(set))
        }

        @Test
        fun testToDbStringAll() {
            val set = setOf(THAI, SWEDISH, BUFFET, ASIAN, EUROPEAN, FAST_FOOD, JAPANESE, STEAK)
            val expectedRes = THAI.foodTypeName + "," + SWEDISH.foodTypeName + "," + BUFFET.foodTypeName + "," + ASIAN.foodTypeName +
                    "," + EUROPEAN.foodTypeName + "," + FAST_FOOD.foodTypeName + ","  + JAPANESE.foodTypeName + "," + STEAK.foodTypeName
            assertEquals(expectedRes,RestaurantFoodType.fromSetToDbString(set))
        }

        @Test
        fun testToDBStringEmptySet(){
            val set = setOf<RestaurantFoodType>()
            val expectedRes = ""
            assertEquals(expectedRes,RestaurantFoodType.fromSetToDbString(set))
        }

        @Test
        fun testFromDbToSetOne(){
            val string = THAI.foodTypeName
            val expectedRes = setOf(THAI)
            assertEquals(expectedRes,RestaurantFoodType.fromDbToSet(string))
        }

        @Test
        fun testFromDbToSetTwo(){
            val string = THAI.foodTypeName + "," + ASIAN.foodTypeName
            val expectedRes = setOf(THAI,ASIAN)
            assertEquals(expectedRes,RestaurantFoodType.fromDbToSet(string))
        }

        @Test
        fun testFromDbToSetFour(){
            val string = THAI.foodTypeName + "," + ASIAN.foodTypeName + "," + SWEDISH.foodTypeName + "," + BUFFET.foodTypeName
            val expectedRes = setOf(THAI, ASIAN, SWEDISH, BUFFET)
            assertEquals(expectedRes,RestaurantFoodType.fromDbToSet(string))
        }

        @Test
        fun testFromDbToSetAll(){
            val string = THAI.foodTypeName + "," + SWEDISH.foodTypeName + "," + BUFFET.foodTypeName + "," + ASIAN.foodTypeName +
                    "," + EUROPEAN.foodTypeName + "," + FAST_FOOD.foodTypeName + ","  + JAPANESE.foodTypeName + "," + STEAK.foodTypeName
            val expectedRes = setOf(THAI, SWEDISH, BUFFET, ASIAN, EUROPEAN, FAST_FOOD, JAPANESE, STEAK)

            assertEquals(expectedRes,RestaurantFoodType.fromDbToSet(string))
        }
    }

    class BudgetTypeTests {


        @Test
        fun testOneBudgetTypeToDb() {
            val set = setOf(CHEAP)
            val expectedRes = CHEAP.budgetType
            assertEquals(expectedRes,BudgetType.fromSetToDbString(set))
        }

        @Test
        fun testThreeBudgetTypeToDb() {
            val set = setOf(CHEAP, NORMAL, VERY_EXPENSIVE)
            val expectedRes = CHEAP.budgetType + "," + NORMAL.budgetType + "," + VERY_EXPENSIVE.budgetType
            assertEquals(expectedRes,BudgetType.fromSetToDbString(set))
        }

        @Test
        fun testAllBudgetTypeToDb() {
            val set = setOf(CHEAP, NORMAL,EXPENSIVE,VERY_EXPENSIVE)
            val expectedRes = CHEAP.budgetType + "," + NORMAL.budgetType + "," + EXPENSIVE.budgetType + "," + VERY_EXPENSIVE.budgetType
            assertEquals(expectedRes,BudgetType.fromSetToDbString(set))
        }

        @Test
        fun testNullBudgetTypeToDb() {
            val set = setOf<BudgetType>()
            val expectedRes = ""
            assertEquals(expectedRes,BudgetType.fromSetToDbString(set))
        }

        @Test
        fun testFromDbToStringOne(){
            val string = NORMAL.budgetType
            val expectedRes = setOf(NORMAL)
            assertEquals(expectedRes,BudgetType.fromDbToSet(string))
        }

        @Test
        fun testFromDbToStringAll(){
            val string = NORMAL.budgetType + "," + CHEAP.budgetType + "," + EXPENSIVE.budgetType + "," + VERY_EXPENSIVE.budgetType
            val expectedRes = setOf(NORMAL, CHEAP, EXPENSIVE, VERY_EXPENSIVE)
            assertEquals(expectedRes,BudgetType.fromDbToSet(string))
        }

        @Test
        fun testFromDbToStringNull(){
            val expectedRes = setOf<BudgetType>()
            assertEquals(expectedRes,BudgetType.fromDbToSet(""))
        }


    }


}
