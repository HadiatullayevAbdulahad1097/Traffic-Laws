package developer.abdulahad.homework25

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.flow.asFlow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
// vararg text : String
///**
//    var nul: String? = null
//    var a = nul?.length ?: -1
//    println(a)
//*/