package developer.abdulahad.homework25

import android.R
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import developer.abdulahad.homework25.DB.MyDbHelper
import developer.abdulahad.homework25.Models.MyData
import developer.abdulahad.homework25.Models.Traffics
import developer.abdulahad.homework25.databinding.FragmentEditBinding
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class EditFragment : Fragment() {
    lateinit var binding: FragmentEditBinding
    lateinit var listSpinner: ArrayList<String>
    private var absolutePath: String = ""
    lateinit var myDbHelper: MyDbHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditBinding.inflate(layoutInflater)

        binding.edtBelgiNomi.setText(MyData.list2[MyData.position2].name)
        binding.edtBelgiHaqida.setText(MyData.list2[MyData.position2].about)

        myDbHelper = MyDbHelper(binding.root.context)

        listSpinner = ArrayList()

        listSpinner.add("Buyuruvchi")
        listSpinner.add("Imtiyozli")
        listSpinner.add("Ogohlantiruvchi")
        listSpinner.add("Taqiqlo'vchi")

        binding.belgiTuri.adapter = ArrayAdapter(binding.root.context, R.layout.simple_list_item_1, listSpinner)

        binding.imageAdd.setImageURI(Uri.parse(MyData.list2[MyData.position2].image))

        binding.imageAdd.setOnClickListener {
            getImageContent.launch("image/*")
            MyData.click = 1
        }
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.save.setOnClickListener {
            val name = binding.edtBelgiNomi.text.toString()
            var image = MyData.list2[MyData.position2].image
            if (MyData.click == 1){
                image = absolutePath
            }
            val about = binding.edtBelgiHaqida.text.toString()
            val turi = binding.belgiTuri.selectedItemPosition
            if (name.isNotEmpty() && about.isNotEmpty()) {
                val traffics = MyData.list2[MyData.position2]
                traffics.name = name
                traffics.image = image
                traffics.about = about
                traffics.turi = turi
                traffics.like = MyData.like
                myDbHelper.updateTraffics(traffics)
                findNavController().popBackStack()
            }
        }

        return binding.root
    }

    private val getImageContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            it ?: return@registerForActivityResult
            binding.imageAdd.setImageURI(it)
            val inputStream = requireActivity().contentResolver.openInputStream(it)
            val title = SimpleDateFormat("yyyyMMdd_hhmmss").format(Date())
            val file = File(requireActivity().filesDir, "$title.jpg")
            val fileOutputStream = FileOutputStream(file)
            inputStream?.copyTo(fileOutputStream)
            inputStream?.close()
            fileOutputStream.close()
            absolutePath = file.absolutePath
            Toast.makeText(context, "$absolutePath", Toast.LENGTH_SHORT).show()
        }
}