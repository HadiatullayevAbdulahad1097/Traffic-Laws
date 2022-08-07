package developer.abdulahad.homework25

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import developer.abdulahad.homework25.DB.MyDbHelper
import developer.abdulahad.homework25.FragmentTur.BuyuruvchiFragment
import developer.abdulahad.homework25.FragmentTur.ImtiyozliFragment
import developer.abdulahad.homework25.FragmentTur.OgohlantiruvchiFragment
import developer.abdulahad.homework25.FragmentTur.TaqiqlovchFragment
import developer.abdulahad.homework25.Models.MyData
import developer.abdulahad.homework25.adapater.HomePageAdapter
import developer.abdulahad.homework25.databinding.FragmentHomeBinding
import developer.abdulahad.homework25.databinding.ItemTabBinding

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var homePageAdapter: HomePageAdapter
    lateinit var list: ArrayList<String>
    lateinit var myDbHelper: MyDbHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        myDbHelper = MyDbHelper(binding.root.context)

        list = ArrayList()
        list.add("Buyuruvchi")
        list.add("Imtiyozli")
        list.add("Ogohlantiruvchi")
        list.add("Taqiqlovchi")
        homePageAdapter = HomePageAdapter(list,this)

        homePageAdapter.addFragment(BuyuruvchiFragment())
        homePageAdapter.addFragment(ImtiyozliFragment())
        homePageAdapter.addFragment(OgohlantiruvchiFragment())
        homePageAdapter.addFragment(TaqiqlovchFragment())

        binding.viewPager.adapter = homePageAdapter
        MyData.viewPager = binding.viewPager
        tab()

        return binding.root
    }

    fun tab(){
        binding.tabHome.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                var customView = tab?.customView
                customView?.findViewById<LinearLayout>(R.id.tab)?.setBackgroundResource(R.drawable.tab_item_bac2)
                customView?.findViewById<TextView>(R.id.tv_tab)?.setTextColor(Color.parseColor("#005CA1"))
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                var customView = tab?.customView
                customView?.findViewById<LinearLayout>(R.id.tab)?.setBackgroundResource(R.drawable.tab_item_bac)
                customView?.findViewById<TextView>(R.id.tv_tab)?.setTextColor(Color.WHITE)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        TabLayoutMediator(binding.tabHome,binding.viewPager){tab,position ->
            var item = ItemTabBinding.inflate(LayoutInflater.from(binding.root.context),null,false)

            tab.customView = item.root

            item.tvTab.text = list[position]

            binding.viewPager.setCurrentItem(tab.position, true)
        }.attach()

    }

}