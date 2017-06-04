package com.celdev.whatsfordinner

import android.graphics.Paint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import com.celdev.whatsfordinner.model.BudgetType
import com.celdev.whatsfordinner.model.MapPoint
import com.celdev.whatsfordinner.model.RestaurantFoodType
import kotlinx.android.synthetic.main.activity_create_restaurant.*

class CreateRestaurantActivity : AppCompatActivity(), CreateRestaurantMVP.View {

    private val restaurantPresenter = CreateRestaurantPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_restaurant)
        initImage()
        initButton()
    }

    private fun initButton() {
        createRestaurantButton.setOnClickListener { restaurantPresenter.createRestaurant()}
    }

    fun initImage(){
        restaurantNameBlackPlaceholder.paint.strokeWidth = 5.0f
        restaurantNameBlackPlaceholder.paint.style = Paint.Style.STROKE
        restaurantNameField.addTextChangedListener(RestaurantTextOnChangeListener(restaurantNameWhitePlaceholder,restaurantNameBlackPlaceholder))
    }


    override fun successCreateRestaurant(id: Long) {
        Log.d(this.localClassName,"created restaurant with id = $id")
    }

    override fun getBudgetTypeInformation(): Set<BudgetType> {
        return BudgetType.values().filter { budgetType -> (findViewById(budgetType.bugetCheckboxResId) as CheckBox).isChecked }.toSet()
    }

    override fun getRestaurantArea(): String {
        return "Patong"
        //TODO
    }

    override fun getMapPoint(): MapPoint {
        return MapPoint(10.0,10.0)
        //todo
    }

    override fun getRestaurantName(): String {
        return restaurantNameField.text.toString()
    }

    override fun getRestaurantDescription(): String {
        return restaurantDescription.text.toString()
    }

    override fun getRestaurantFoodTypes(): Set<RestaurantFoodType> {
        return RestaurantFoodType.values().filter { restaurantFoodType -> (findViewById(restaurantFoodType.checkBoxResId) as CheckBox).isChecked }.toSet()
    }
}
