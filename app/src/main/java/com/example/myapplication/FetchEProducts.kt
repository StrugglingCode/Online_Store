package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_fetch_eproducts.*

/* In this activity we are fetching products accoording to the tappedbrand by user
* created a ArrayList which holds all the items of selected brand */

class FetchEProducts : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_eproducts)



        val selectedBrand:String = intent.getStringExtra("BRAND")  //tapped brand passed here
         FetchProducts_textView.text = "Products of $selectedBrand"
        var productsList = ArrayList<EProduct>()
        //val eProductUrl = "http://192.168.1.4/online_store/fetch_eproducts.php?Brand="+selectedBrand
        val productURL = "http://192.168.1.4/online_store/fetch_eproducts.php?Brand=$selectedBrand"
        val requestQ = Volley.newRequestQueue(this)
        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET,productURL,null,Response.Listener { response ->

            for(productJsonObjectIndex in 0.until(response.length()))
            {
                productsList.add(EProduct(response.getJSONObject(productJsonObjectIndex).getInt("id"),
                    response.getJSONObject(productJsonObjectIndex).getString("Name"),
                    response.getJSONObject(productJsonObjectIndex).getInt("Price"),
                    response.getJSONObject(productJsonObjectIndex).getString("pictures")))
                //adding products to ArrayList

            }

            val pAdapter = EProduct_Adapter(this@FetchEProducts,productsList) //creating object of EProduct_Adapter Class
            fetchProductsRV.layoutManager = LinearLayoutManager(this@FetchEProducts)
            fetchProductsRV.adapter = pAdapter   //inflating our recycler view inside fetch_adapter.xml




        },



            Response.ErrorListener { error ->

                Toast.makeText(this,error.message,Toast.LENGTH_SHORT).show()
            })

        requestQ.add(jsonArrayRequest)
    }
}
