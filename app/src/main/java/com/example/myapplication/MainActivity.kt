package com.example.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*


//LOGIN ACTIVITY
//IF LOGIN SUCCESSFUL USER REDRIECTED TO HOME ACTIVITY WHERE IT SELECTS A BRAND NAME
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSignup.setOnClickListener {
            var intent = Intent(this@MainActivity,SignUp::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {

            val loginUrl ="http://192.168.1.4/online_store/login_user.php?email="+
                    emailLoginActivity.text.toString()+
                    "&password="+passwordLoginActivity.text.toString()

            val requestQ = Volley.newRequestQueue(this@MainActivity)
            val stringRequest = StringRequest(Request.Method.GET,loginUrl,Response.Listener { response ->

                if(response.equals("user exists"))
                {
                    Person.email = emailLoginActivity.text.toString()
                    //Toast.makeText(this,response,Toast.LENGTH_SHORT).show()
                    val homeIntent = Intent(this@MainActivity,home::class.java)
                    startActivity(homeIntent)
                }
                else
                {
                    val alert = AlertDialog.Builder(this)
                    alert.setTitle("Message")
                    alert.setMessage(response)
                    alert.create().show()
                }



            }, Response.ErrorListener { error ->

                val alert = AlertDialog.Builder(this)
                alert.setTitle("Message")
                alert.setMessage(error.message)
                alert.create().show()

                



            })

            requestQ.add(stringRequest)
        }
    }
}
