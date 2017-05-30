package com.celdev.whatsfordinner

import com.celdev.whatsfordinner.model.*
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ModelTest {

    @Test
    fun testRestaurantModel(){
        val restaurant = Restaurant(0, "Best-aurant",
                "Cheap beer and expensive steak", RestaurantArea.PATONG,
                setOf(RestaurantFoodType.THAI, RestaurantFoodType.BUFFET), setOf(BudgetType.CHEAP, BudgetType.EXPENSIVE),
                MapPoint(18.12312,79.231231))
        assertEquals(restaurant.id, 0)
        assertEquals(restaurant.name, "Best-aurant")
        assertEquals(restaurant.restaurantArea, RestaurantArea.PATONG)
        assertArrayEquals(restaurant.restaurantFoodType.toTypedArray(), arrayOf(RestaurantFoodType.THAI, RestaurantFoodType.BUFFET))
        assertArrayEquals(restaurant.budgetType.toTypedArray(), arrayOf(BudgetType.CHEAP, BudgetType.EXPENSIVE))
        assertEquals(restaurant.MapPoint.latitude,18.12312,0.01)
        assertEquals(restaurant.MapPoint.longitude,79.231231,0.01)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testIllegalRestaurantFoodType(){
        Restaurant(0, "Best-aurant",
                "Cheap beer and expensive steak", RestaurantArea.PATONG,
                setOf(), setOf(BudgetType.CHEAP, BudgetType.EXPENSIVE),
                MapPoint(18.12312, 79.231231))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testIllegalRestaurantBudgetType(){
        Restaurant(0, "Best-aurant",
                "Cheap beer and expensive steak", RestaurantArea.PATONG,
                setOf(RestaurantFoodType.THAI, RestaurantFoodType.BUFFET), setOf(),
                MapPoint(18.12312, 79.231231))
    }

    @Test
    fun getDrawableTest(){
        val restaurant = Restaurant(0, "Best-aurant",
                "Cheap beer and expensive steak", RestaurantArea.PATONG,
                setOf(RestaurantFoodType.THAI, RestaurantFoodType.BUFFET), setOf(BudgetType.CHEAP, BudgetType.EXPENSIVE),
                MapPoint(18.12312,79.231231))
        val drawables = restaurant.getAllDrawable()
        assertEquals(drawables.size,2)
        assertArrayEquals(drawables, arrayOf(RestaurantFoodType.THAI.image, RestaurantFoodType.BUFFET.image))
    }




}