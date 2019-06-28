package com.example.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.RequestFuture
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_sign_up.*

//SIGN UP ACTIVITY USER SING UP USING THIS ACTIVITY
//IF SIGNUP SUCCESSFUL USER REDRICTED TO MAIN ACTIVITY i.e. LOGIN ACTIVITY
class SignUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        signupBtnSignup.setOnClickListener {

            if(signupPassword.text.toString().equals(signupConformPassword.text.toString()))
            {
                val signupUrl ="http://192.168.1.4/online_store/signup_users.php?email="+
                        signupEmail.text.toString()+
                        "&username="+signupUserName.text.toString()+
                        "&password="+signupPassword.text.toString()

                val requestQ = Volley.newRequestQueue(this@SignUp)
                val stringRequest = StringRequest(Request.Method.GET,signupUrl,Response.Listener { response ->

                    if(response.equals("user with same email already exists"))
                    {
                        val alert = AlertDialog.Builder(this)
                        alert.setTitle("Message")
                        alert.setMessage(response)
                        alert.create().show()



                    }
                    else
                    {
//                        val alert = AlertDialog.Builder(this)
//                        alert.setTitle("Message")
//                        alert.setMessage(response)
//                        alert.create().show()
                        Person.email = signupEmail.text.toString()
                        Toast.makeText(this@SignUp,response,Toast.LENGTH_SHORT).show()

                        val homeIntent = Intent(this@SignUp,MainActivity::class.java)
                        startActivity(homeIntent)



                    }
                },


                    Response.ErrorListener { error ->

                        val alert = AlertDialog.Builder(this)
                        alert.setTitle("Message")
                        alert.setMessage(error.message)
                        alert.create().show()

                    })
                requestQ.add(stringRequest)
            }

            else
            {
                Toast.makeText(this,"Password Mismatch",Toast.LENGTH_SHORT).show()
            }


            }

    signupBtnlogin.setOnClickListener {
       finish()
   }
        }



    }

