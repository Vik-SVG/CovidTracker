package com.lessons.covidtracker

import android.graphics.RectF
import com.robinhood.spark.SparkAdapter

class CovidSparkAdapter(private val dailyData: List<CovidData>): SparkAdapter() {

    var  metric = Metric.POSITIVE
    var daysAgo = TimeScale.MAX

    override fun getCount() = dailyData.size

    override fun getItem(index: Int) = dailyData[index]

    override fun getY(index: Int): Float {
        val chosenDayData = dailyData[index]
        return when(metric){
            Metric.NEGATIVE -> chosenDayData.negativeIncrease.toFloat()
            Metric.POSITIVE -> chosenDayData.positiveIncrease.toFloat()
            Metric.DEATH -> chosenDayData.deathIncrease.toFloat()
        }
      //  return chosenDayData.positiveIncrease.toFloat()
    }

    override fun getDataBounds(): RectF {
        val bounds:RectF = super.getDataBounds()

       if (daysAgo != TimeScale.MAX) {
           bounds.left = count.toFloat() - daysAgo.numDays.toFloat()
       }

        //    val countDays:Float = count.toFloat() - 30
        //    val countBounds:Float = count.toFloat() - 7

     //   bounds.set(270F,  bounds.top, bounds.right, bounds.bottom)
     //   bounds.inset(bounds.width() /10, bounds.height() / 10);

        return bounds
    }
}
