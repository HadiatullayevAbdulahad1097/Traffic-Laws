package developer.abdulahad.homework25

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
import developer.abdulahad.homework25.databinding.FragmentAddBinding
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AddFragment : Fragment() {
    lateinit var binding: FragmentAddBinding
    lateinit var myDbHelper: MyDbHelper
    private var absolutePath = ""
    private lateinit var listSpinner: ArrayList<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater)

        myDbHelper = MyDbHelper(binding.root.context)

        val list = myDbHelper.getTraffics()

        listSpinner = ArrayList()

        binding.imageAdd.setOnClickListener {
            getImageContent.launch("image/*")
        }

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        listSpinner.add("Buyuruvchi")
        listSpinner.add("Imtiyozli")
        listSpinner.add("Ogohlantiruvchi")
        listSpinner.add("Taqiqlo'vchi")

        binding.belgiTuri.adapter =
            ArrayAdapter(binding.root.context, android.R.layout.simple_list_item_1, listSpinner)

        binding.save.setOnClickListener {
            val name = binding.edtBelgiNomi.text.toString()
            val image = absolutePath
            val about = binding.edtBelgiHaqida.text.toString()
            val turi = binding.belgiTuri.selectedItemPosition
            val like = MyData.like
            if (name.isNotEmpty() && about.isNotEmpty() && image.isNotBlank()) {
                val traffics = Traffics(
                    name,
                    image,
                    about,
                    turi,
                    like
                )
                list.add(traffics)
                myDbHelper.addTraffics(traffics)
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

//    private fun clearImages() {
//        val filesDir = requireActivity().filesDir
//        if (filesDir.isDirectory) {
//            val listFile = filesDir.listFiles()
//            if (listFile.isEmpty()) {
//                Toast.makeText(context, "Faylar mavjud emas", Toast.LENGTH_SHORT).show()
//                return
//            }
//
//                listFile.forEach {
//                    it.delete()
//            }
//        }
//    }
}