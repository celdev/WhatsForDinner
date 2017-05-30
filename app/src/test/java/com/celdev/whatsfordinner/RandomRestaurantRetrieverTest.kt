package com.celdev.whatsfordinner

import com.celdev.whatsfordinner.model.*
import org.junit.Assert.*
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.util.*


class RandomRestaurantRetrieverTest {


    @Test
    fun testGetRandom(){
        val restaurants = TestObjectCreator.createXamountOfRandomRestaurants(10)
        val rand = RandomRestaurantRetriever(restaurants.toSet()).getRandomRestaurant()
        assertTrue(restaurants.contains(rand))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testIllegalRandom(){
        RandomRestaurantRetriever(setOf())
    }

    @Test
    fun mockRandomizer(){
        val amountOfRestaurants = 5
        val randomResponse = 2
        val random = mock(Random::class.java)
        `when`(random.nextInt(amountOfRestaurants)).thenReturn(randomResponse)
        val restaurants = TestObjectCreator.createXamountOfRandomRestaurants(amountOfRestaurants)
        val rand = RandomRestaurantRetriever(restaurants.toSet(), random).getRandomRestaurant()
        assertEquals(rand, restaurants[randomResponse])
    }


}