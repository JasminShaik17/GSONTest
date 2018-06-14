package cubex.mahesh.gsontest

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import com.google.gson.Gson
import java.io.InputStreamReader

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
        var fis =    openFileInput("employees.json")
        var gson = Gson( )
        var reader:InputStreamReader? = null
        var emps:Employees? = null
        var list: MutableList<Employee>? = null
        if(fis!=null) {
            reader = InputStreamReader(fis)
            emps = gson.fromJson<Employees>(reader,
                    Employees::class.java)
            list = emps.employees
        }else{
            list = mutableListOf<Employee>()
            emps =Employees(list)
        }
        var e = Employee(et1?.text.toString().toInt(),
                            et2?.text.toString(),et3?.text.toString(),
                            et4?.text.toString())
        list.add(e)
       var json_resp = gson.toJson(emps)
      var fos =  openFileOutput("employees.json",
              Context.MODE_PRIVATE)
        fos.write(json_resp.toByteArray())
        fos.flush()
        fos.close()
    }
    fun read(v: View){

    var fis =    openFileInput("employees.json")
    var gson = Gson( )
    var reader = InputStreamReader(fis)
    var emps = gson.fromJson<Employees>(reader,
           Employees::class.java )
    var list =  emps.employees
     var temp_list = mutableListOf<String>()
     for(e in list){
         temp_list.add(e.id.toString()+
           "\t"+e.name+"\n"+e.desig+"\t"+e.dept)
     }
    var myadapter = ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_single_choice,
            temp_list)
     lview?.adapter = myadapter
    }
}
