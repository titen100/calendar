package com.example.calendar
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.YearMonth
import java.time.ZoneId
import java.time.temporal.ChronoUnit

class CalendarLogic {
    @RequiresApi(Build.VERSION_CODES.O)
    fun monthName(monthNo: Int = LocalDate.now(ZoneId.systemDefault()).monthValue): String{
        return when (monthNo) {
            1 -> "January"
            2 -> "February"
            3 -> "March"
            4 -> "April"
            5 -> "May"
            6 -> "June"
            7 -> "July"
            8 -> "August"
            9 -> "September"
            10 -> "October"
            11 -> "November"
            else -> "December"
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun daysInMonth(year:Int = LocalDate.now(ZoneId.systemDefault()).year,
                    month: Int = LocalDate.now(ZoneId.systemDefault()).monthValue): Int {
        val daysInCurrent: Int
        if (month != 2) {
            daysInCurrent = if (month % 2 == 0) 30 else 31
        }
        else {
            daysInCurrent = if (year % 4 == 0 || year % 400 == 0) 28 else 29
        }
        return daysInCurrent
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private var currentMonth = YearMonth.now()

    @RequiresApi(Build.VERSION_CODES.O)
    fun nextMonth() {
        currentMonth = currentMonth.plusMonths(1)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun previousMonth() {
        currentMonth = currentMonth.minusMonths(1)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentMonth(): YearMonth {
        return currentMonth
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun daysSinceJanuaryFirst(): Long {
        val januaryFirst = LocalDate.of(currentMonth.year, 1, 1)
        return ChronoUnit.DAYS.between(januaryFirst, LocalDate.now())
    }

}