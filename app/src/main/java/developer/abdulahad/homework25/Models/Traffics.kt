package developer.abdulahad.homework25.Models

import android.net.Uri
import java.net.URI

class Traffics {
    var id = 0
    var name = ""
    var image : String? = null
    var about = ""
    var turi = 0
    var like = 0

    constructor()

    constructor(id: Int, name: String, image: String?, about: String, turi: Int, like: Int) {
        this.id = id
        this.name = name
        this.image = image
        this.about = about
        this.turi = turi
        this.like = like
    }

    constructor(name: String, image: String?, about: String, turi: Int, like: Int) {
        this.name = name
        this.image = image
        this.about = about
        this.turi = turi
        this.like = like
    }
}