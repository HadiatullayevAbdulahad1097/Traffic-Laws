package developer.abdulahad.homework25

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import developer.abdulahad.homework25.adapater.BasicPagerAdapter
import developer.abdulahad.homework25.databinding.FragmentAsosiyBinding
import developer.abdulahad.homework25.databinding.ItemTabBinding

class AsosiyFragment : Fragment() {
    lateinit var binding: FragmentAsosiyBinding
    lateinit var basicPagerAdapter: BasicPagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAsosiyBinding.inflate(layoutInflater)

        basicPagerAdapter = BasicPagerAdapter(this)

        basicPagerAdapter.addFragment(HomeFragment())
        basicPagerAdapter.addFragment(LikedFragment())
        basicPagerAdapter.addFragment(AboutFragment())

        binding.viewPagerBasic.adapter = basicPagerAdapter

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 ->{
                        binding.add.visibility = View.VISIBLE
                    }
                    1 ->{
                        binding.add.visibility = View.INVISIBLE
                    }
                    2 ->{
                        binding.add.visibility = View.INVISIBLE
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        binding.add.setOnClickListener {
            findNavController().navigate(R.id.addFragment)
        }

        var array = arrayOf(R.drawable.home,R.drawable.like,R.drawable.about)

        TabLayoutMediator(binding.tabLayout,binding.viewPagerBasic){tab,position->
            tab.setIcon(array[position])


            binding.viewPagerBasic.setCurrentItem(tab.position, true)
        }.attach()

        return binding.root
    }
}
