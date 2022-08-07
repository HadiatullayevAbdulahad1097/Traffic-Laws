package developer.abdulahad.homework25.adapater

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import developer.abdulahad.homework25.FragmentTur.BuyuruvchiFragment
import developer.abdulahad.homework25.FragmentTur.ImtiyozliFragment
import developer.abdulahad.homework25.FragmentTur.OgohlantiruvchiFragment
import developer.abdulahad.homework25.FragmentTur.TaqiqlovchFragment
import developer.abdulahad.homework25.MalumotFragment

class HomePageAdapter(list: List<String>,fragment: Fragment) : FragmentStateAdapter(fragment) {
    var fragmentList = ArrayList<Fragment>()
    fun addFragment(fragment: Fragment){
        fragmentList.add(fragment)
    }
    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}