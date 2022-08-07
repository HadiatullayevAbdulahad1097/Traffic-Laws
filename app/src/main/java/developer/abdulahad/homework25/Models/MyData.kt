package developer.abdulahad.homework25.Models

import android.content.Context
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import developer.abdulahad.homework25.DB.MyDbHelper
import developer.abdulahad.homework25.adapater.HomePageAdapter

object MyData {
    var like = 0
    var position = 0
    var position2 = 0
    var click = 0
    var list = ArrayList<Traffics>()
    var list2 = ArrayList<Traffics>()
    var adapter : HomePageAdapter? = null
    var viewPager : ViewPager2? = null
}