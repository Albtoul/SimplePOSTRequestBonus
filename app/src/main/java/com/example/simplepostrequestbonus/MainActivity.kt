package com.example.simplepostrequestbonus

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {
    lateinit var ed:EditText
    lateinit var bt:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ed=findViewById(R.id.ed)
        bt= findViewById(R.id.button)

        bt.setOnClickListener {

            addUser()


        }
    }


    private fun addUser() {

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setMessage("Please wait")
        progressDialog.show()
        if(apiInterface!=null){

            apiInterface.addUser(Custompeople.CustompeopleItem(ed.text.toString(), 0)).enqueue(object : Callback<Custompeople?> {
                override fun onResponse(
                    call: Call<Custompeople?>,
                    response: Response<Custompeople?>
                ) {

                    progressDialog.dismiss()

                }

                override fun onFailure(call: Call<Custompeople?>, t: Throwable) {
                    Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss()

                }
            })











        }

    }


}