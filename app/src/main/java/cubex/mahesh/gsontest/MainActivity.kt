package cubex.mahesh.gsontest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ListView
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    var et1:EditText? = null
    var et2:EditText? = null
    var et3:EditText? = null
    var et4:EditText? = null
    var lview:ListView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et1 = findViewById(R.id.et1)
        et2 = findViewById(R.id.et2)
        et3 = findViewById(R.id.et3)
        et4 = findViewById(R.id.et4)
        lview = findViewById(R.id.lview)
    }

    fun insert(v:View){

        var e = Employee(et1?.text.toString().toInt(),
                            et2?.text.toString(),et3?.text.toString(),
                            et4?.text.toString())
        var list = mutableListOf<Employee>()
        list.add(e)
        var emps =Employees(list)
        var gson = Gson( )
        var json_resp = gson.toJson(emps)
    }
    fun read(v: View){

    }
}
