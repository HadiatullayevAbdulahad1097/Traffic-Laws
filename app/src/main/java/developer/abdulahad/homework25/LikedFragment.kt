package developer.abdulahad.homework25

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import developer.abdulahad.homework25.DB.MyDbHelper
import developer.abdulahad.homework25.Models.MyData
import developer.abdulahad.homework25.Models.Traffics
import developer.abdulahad.homework25.adapater.RvHomeAdapter
import developer.abdulahad.homework25.databinding.FragmentLikedBinding

class LikedFragment : Fragment() {
    lateinit var binding: FragmentLikedBinding
    lateinit var myDbHelper: MyDbHelper
    lateinit var rvHomeAdapter: RvHomeAdapter
    lateinit var list : ArrayList<Traffics>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLikedBinding.inflate(layoutInflater)

        myDbHelper = MyDbHelper(binding.root.context)

        list = ArrayList()

        for (i in myDbHelper.getTraffics()){
            if (i.like == 1){
                list.add(i)
            }
        }
        rvHomeAdapter = RvHomeAdapter(list,binding.root.context,object : RvHomeAdapter.ClickItem{
            override fun itemClick(traffics: Traffics, position: Int) {
                MyData.position = position
                MyData.list = list
                findNavController().navigate(R.id.malumotFragment)
            }

            override fun deleteClick(traffics: Traffics, position: Int) {
                list.remove(traffics)
                myDbHelper.deleteTraffics(traffics)
                rvHomeAdapter.notifyItemRemoved(position)
            }

            override fun editClick(traffics: Traffics, position: Int) {
                MyData.position2 = list.indexOf(traffics)
                MyData.list2 = list
                findNavController().navigate(R.id.editFragment)
            }
        })

        binding.rvLiked.adapter = rvHomeAdapter

        return binding.root
    }
}