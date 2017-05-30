package com.celdev.whatsfordinner

class TimeGiverImpl : TimeGiver {
    override fun getCurrentTimeMillis(): Long {
        return System.currentTimeMillis()
    }
}