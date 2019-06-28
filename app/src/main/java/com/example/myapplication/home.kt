package com.example.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home.*


/* In this Activity we are fetching the distinct brand names as JSON OBJECT from our table
 * adding those distinct brand name to an ArrayList named brandList inside for-loop
 * after adding the brand names to the ArrayList we are using ArrayAdapter to inflate our textView inside itemTextView.xml with the brand names
 * after inflating our textView we are inflating our listView inside home.xml by the items present in the itemTextView
  *
  * after inflating we are creating on click listener
  * inside onclickListener we are creating a variable which stores the tapped brand name
  * after clicking on the brand name we are fetching the product of tapped brand name from server inside FetchEProduct class*/


class home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val brandsUrl = "http://192.168.1.4/online_store/fetch_brands.php"
        var brandList:ArrayList<String> = ArrayList()
        var requestQ = Volley.newRequestQueue(this)
        var jasonArrayRequest = JsonArrayRequest(Request.Method.GET,brandsUrl,null,Response.Listener { response ->

        for(jsonObject in 0.until(response.length())) {
            brandList.add(response.getJSONObject(jsonObject).getString("Brand"))   //Brand = Key value of json objects  .//populating brand list
        }
            var brandsListAdapter = ArrayAdapter(this@home,R.layout.itemtextview,brandList) //putting the values in user created row inside itemtextView.xml
            Brands_listView.adapter = brandsListAdapter       //to fill our listView of home.xml with items present inside itemTextView.xml

        },Response.ErrorListener { error ->

            Toast.makeText(this,error.message,Toast.LENGTH_SHORT).show()
        })
        requestQ.add(jasonArrayRequest)

        Brands_listView.setOnItemClickListener { parent, view, position, id ->              //created on click listener for every distinct brand present

            val tappedBrand =  brandList.get(position)
            val intent = Intent(this@home,FetchEProducts::class.java)
            intent.putExtra("BRAND",tappedBrand)                                  //passing key i.e BRAND and Name of the brand
            startActivity(intent)
        }


    }
}
