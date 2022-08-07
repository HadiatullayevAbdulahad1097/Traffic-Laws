package developer.abdulahad.homework25.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import developer.abdulahad.homework25.Models.Traffics

class MyDbHelper (context: Context) : SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION), MyDbInterFace {
    companion object{
        const val DB_NAME = "myTraffics"

        const val DB_VERSION = 1

        const val TABLE_NAME = "Qoidalar"

        const val ID = "nyid"

        const val NAME = "Myname"

        const val MYIMAGE = "myimage"

        const val MYABOUT = "myabout"

        const val TURI = "myturi"

        const val MYLIKE = "myike"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE $TABLE_NAME($ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, $NAME TEXT NOT NULL, $MYIMAGE TEXT NOT NULL, $MYABOUT TEXT NOT NULL, $TURI INTEGER NOT NULL, $MYLIKE INTEGER NOT NULL)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    override fun addTraffics(traffics: Traffics) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME,traffics.name)
        contentValues.put(MYIMAGE,traffics.image)
        contentValues.put(MYABOUT,traffics.about)
        contentValues.put(TURI,traffics.turi)
        contentValues.put(MYLIKE,traffics.like)
        database.insert(TABLE_NAME,null,contentValues)
        database.close()
    }

    override fun getTraffics(): ArrayList<Traffics>{
        val list = ArrayList<Traffics>()
        val database = this.readableDatabase
        val query = "select * from $TABLE_NAME"
        val cursor = database.rawQuery(query,null)

        if (cursor.moveToFirst()){
            do {

                val traffics = Traffics(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getInt(5)
                )

                list.add(traffics)

            } while (cursor.moveToNext())
        }
        return list
    }

    override fun updateTraffics(traffics: Traffics): Int {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID,traffics.id)
        contentValues.put(NAME,traffics.name)
        contentValues.put(MYIMAGE,traffics.image)
        contentValues.put(MYABOUT,traffics.about)
        contentValues.put(TURI,traffics.turi)
        contentValues.put(MYLIKE,traffics.like)
        return database.update(TABLE_NAME,contentValues,"$ID = ?", arrayOf(traffics.id.toString()))
    }

    override fun deleteTraffics(traffics: Traffics) {
        val database = this.writableDatabase
        database.delete(TABLE_NAME,"$ID = ?", arrayOf(traffics.id.toString()))
        database.close()
    }

}