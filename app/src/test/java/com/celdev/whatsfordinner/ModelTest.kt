package com.celdev.whatsfordinner

import com.celdev.whatsfordinner.model.*
import org.junit.Assert.*
import org.junit.Test

class ModelTest {

    @Test
    fun testRestaurantModel(){
        val restaurant = Restaurant(0, "Best-aurant",
                "Cheap beer and expensive steak", "Patong",
                setOf(RestaurantFoodType.THAI, RestaurantFoodType.BUFFET), setOf(BudgetType.CHEAP, BudgetType.EXPENSIVE),
                MapPoint(18.12312,79.231231))
        assertEquals(restaurant.id, 0)
        assertEquals(restaurant.name, "Best-aurant")
        assertEquals(restaurant.restaurantArea, "Patong")
        assertArrayEquals(restaurant.restaurantFoodType.toTypedArray(), arrayOf(RestaurantFoodType.THAI, RestaurantFoodType.BUFFET))
        assertArrayEquals(restaurant.budgetType.toTypedArray(), arrayOf(BudgetType.CHEAP, BudgetType.EXPENSIVE))
        assertEquals(restaurant.MapPoint.latitude,18.12312,0.01)
        assertEquals(restaurant.MapPoint.longitude,79.231231,0.01)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testIllegalRestaurantFoodType(){
        Restaurant(0, "Best-aurant",
                "Cheap beer and expensive steak", "Patong",
                setOf(), setOf(BudgetType.CHEAP, BudgetType.EXPENSIVE),
                MapPoint(18.12312, 79.231231))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testIllegalRestaurantBudgetType(){
        Restaurant(0, "Best-aurant",
                "Cheap beer and expensive steak", "Patong",
                setOf(RestaurantFoodType.THAI, RestaurantFoodType.BUFFET), setOf(),
                MapPoint(18.12312, 79.231231))
    }

    @Test
    fun getDrawableTest(){
        val restaurant = Restaurant(0, "Best-aurant",
                "Cheap beer and expensive steak", "Patong",
                setOf(RestaurantFoodType.THAI, RestaurantFoodType.BUFFET), setOf(BudgetType.CHEAP, BudgetType.EXPENSIVE),
                MapPoint(18.12312,79.231231))
        val drawables = restaurant.getAllDrawable()
        assertEquals(drawables.size,2)
        assertArrayEquals(drawables, arrayOf(RestaurantFoodType.THAI.image, RestaurantFoodType.BUFFET.image))
    }


    @Test
    fun blockTestSetBlocked() {
        val restaurant = TestObjectCreator.createRestaurant()
        restaurant.setBlockedFor3Hours()
        assertTrue(restaurant.getIsBlocked())
    }

    @Test
    fun blockTestSetBlocked3HoursPassed() {
        val restaurant = TestObjectCreator.createRestaurant()
        restaurant.setBlockedFor3Hours(object : TimeGiver{
            override fun getCurrentTimeMillis(): Long {
                return 0
            }
        })
        assertTrue(restaurant.getIsBlocked(object : TimeGiver{
            override fun getCurrentTimeMillis(): Long {
                return 0
            }
        }))
        assertFalse(restaurant.getIsBlocked(object : TimeGiver{
            override fun getCurrentTimeMillis(): Long {
                return 60 * 60 * 1000 * 3
            }
        }))
    }

    @Test
    fun testForeverBlocked(){
        val restaurant = TestObjectCreator.createRestaurant()
        restaurant.blockedForever = true
        assertTrue(restaurant.getIsBlocked())
        restaurant.setBlockedFor3Hours()
        assertTrue(restaurant.getIsBlocked())
        restaurant.removeForeverBlock()
        assertTrue(restaurant.getIsBlocked())
        restaurant.removeTemporaryBlock()
        assertFalse(restaurant.getIsBlocked())
    }


    @Test
    fun testRemoveAllBlocks(){
        val restaurant = TestObjectCreator.createRestaurant()
        restaurant.blockedForever = true
        assertTrue(restaurant.getIsBlocked())
        restaurant.setBlockedFor3Hours()
        assertTrue(restaurant.getIsBlocked())
        restaurant.removeAllBlocks()
        assertFalse(restaurant.getIsBlocked())
    }

    @Test
    fun setFavorite(){
        val restaurant = TestObjectCreator.createRestaurant()
        restaurant.favorite = true
        assertTrue(restaurant.favorite)
    }

    @Test
    fun setUnSetFavorite(){
        val restaurant = TestObjectCreator.createRestaurant()
        restaurant.favorite = true
        assertTrue(restaurant.favorite)
        restaurant.favorite = false
        assertFalse(restaurant.favorite)
    }

    @Test
    fun testGetAllNames(){
        val restaurant = Restaurant(
                0,"test","test","Kamala", setOf(RestaurantFoodType.ASIAN,RestaurantFoodType.EUROPEAN),
                setOf(BudgetType.CHEAP), MapPoint(0.0,0.0))
        val allFoodTypeNames = restaurant.getAllFoodTypeNames()
        val toTypedArray = setOf<RestaurantFoodType>(RestaurantFoodType.ASIAN, RestaurantFoodType.EUROPEAN).map { restaurantFoodType -> restaurantFoodType.foodTypeNameRes }.toTypedArray()
        assertArrayEquals(toTypedArray,allFoodTypeNames)
    }

    @Test
    fun getDescription(){
        val restaurant = Restaurant(
                0,"test","test","Kamala", setOf(RestaurantFoodType.ASIAN,RestaurantFoodType.EUROPEAN),
                setOf(BudgetType.CHEAP), MapPoint(0.0,0.0))
        assertEquals("test", restaurant.description)
    }


    @Test
    fun testCreateRestaurantWithBlockedInfo(){
        val restaurant = TestObjectCreator.createRestaurant(0)
        restaurant.blockedForever = true
        val timeGiver = object : TimeGiver{
            override fun getCurrentTimeMillis(): Long {
                return 0L
            }
        }
        restaurant.setBlockedFor3Hours(timeGiver)
        val restaurant2 = Restaurant(
                restaurant.id,restaurant.name,restaurant.description,restaurant.restaurantArea,
                restaurant.restaurantFoodType,restaurant.budgetType,restaurant.MapPoint,
                true,true,0
        )
        assertEquals(restaurant, restaurant2)
        assertEquals(restaurant.blockedForever, restaurant2.blockedForever)
        assertEquals(restaurant.getIsBlocked(timeGiver), restaurant2.getIsBlocked(timeGiver))
        assertEquals(restaurant.getIsBlocked(), restaurant2.getIsBlocked())
    }






}