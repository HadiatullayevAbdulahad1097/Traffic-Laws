package developer.abdulahad.homework25

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import developer.abdulahad.homework25.DB.MyDbHelper
import developer.abdulahad.homework25.Models.MyData
import developer.abdulahad.homework25.databinding.FragmentMalumotBinding

class MalumotFragment : Fragment() {
    lateinit var binding : FragmentMalumotBinding
    lateinit var myDbHelper: MyDbHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMalumotBinding.inflate(layoutInflater)

        myDbHelper = MyDbHelper(binding.root.context)

        binding.image.setImageURI(Uri.parse(MyData.list[MyData.position].image))
        binding.name.text = MyData.list[MyData.position].name
        binding.about.text = MyData.list[MyData.position].about

        return binding.root
    }
}