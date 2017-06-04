package com.celdev.whatsfordinner.db

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.celdev.whatsfordinner.model.BudgetType
import com.celdev.whatsfordinner.model.MapPoint
import com.celdev.whatsfordinner.model.Restaurant
import com.celdev.whatsfordinner.model.RestaurantFoodType

private val DATABASE_VERSION = 1
private val DATABASE_NAME = "restaurantManager"

class RestaurantDatabaseHandler(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION), RestaurantCrud {

    private val tableName = "restaurants"

    private val restaurantIdCol = "id"
    private val restaurantNameCol = "name"
    private val restaurantAreaNameCol = "area_name"
    private val restaurantDescription = "description"
    private val restaurantFoodType = "food_type"
    private val restaurantBudgetType = "budget_type"
    private val restaurantMapPointX = "map_point_x"
    private val restaurantMapPointY = "map_point_y"
    private val restaurantTempBlocked = "temp_blocked"
    private val restaurantPermBlocked = "perm_blocked"
    private val restaurantTempBlockedTime = "temp_blocked_time"

    private val allCols = arrayOf(
            restaurantIdCol,restaurantNameCol,restaurantDescription,
            restaurantAreaNameCol, restaurantFoodType, restaurantBudgetType,
            restaurantMapPointX,restaurantMapPointY)


    override fun onUpgrade(db: SQLiteDatabase?, oldVers: Int, newVers: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + tableName)
        onCreate(db)
    }


    override fun onCreate(db: SQLiteDatabase?) {
        val createQuery = "CREATE TABLE " + tableName + " ( " +
                restaurantIdCol + " INTEGER PRIMARY KEY, " +
                restaurantNameCol + " TEXT, " +
                restaurantAreaNameCol + " TEXT, " +
                restaurantDescription + " TEXT, " +
                restaurantFoodType + " TEXT, " +
                restaurantBudgetType + " TEXT, " +
                restaurantMapPointX + " TEXT, " +
                restaurantMapPointY + " TEXT, " +
                restaurantTempBlockedTime + " INTEGER, " +
                restaurantTempBlocked + " INTEGER, " +
                restaurantPermBlocked + " INTEGER, " +
                " )"
        db?.execSQL(createQuery)
    }

    override fun addRestaurant(restaurant: Restaurant) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRestaurantById(id: Int): Restaurant? {
        val db = this.readableDatabase
        val cursor = db.query(tableName, allCols, restaurantIdCol + "=?", arrayOf(id.toString()), null, null, null, null)
        val restaurant = cursorToRestaurant(cursor)
        cursor.close()
        return restaurant
    }

    fun cursorToRestaurant(cursor: Cursor?) : Restaurant? {
        if(cursor != null && cursor.moveToFirst()){
            val restaurant =  Restaurant(
                    cursor.getInt(0).toLong(),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    RestaurantFoodType.fromDbToSet(cursor.getString(4)),
                    BudgetType.fromDbToSet(cursor.getString(5)),
                    MapPoint(cursor.getString(6).toDouble(),cursor.getString(7).toDouble()),
                    intToBoolean(cursor.getInt(9)),intToBoolean(cursor.getInt(10)),cursor.getLong(8)
            )
            return restaurant
        }
        return null
    }

    fun intToBoolean(number : Int) : Boolean {
        if (number == 1) {
            return true
        }
        return false
    }

    override fun getAllRestaurants(): List<Restaurant> {
        val list = ArrayList<Restaurant>()

        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM " + tableName,null)

        cursor.moveToFirst()

        while (cursor.isAfterLast == false) {
            val restaurant = cursorToRestaurant(cursor)
            if (restaurant != null) {
                list.add(restaurant)
            }
        }
        cursor.close()
        return list
    }

    override fun getRestaurantCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateRestaurant(restaurant: Restaurant) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteRestaurant(restaurant: Restaurant) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

