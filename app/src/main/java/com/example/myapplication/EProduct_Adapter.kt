package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.eproduct_row.view.*

class EProduct_Adapter(var context:Context,var arrayList:ArrayList<EProduct>):RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {

        val productView = LayoutInflater.from(context).inflate(R.layout.eproduct_row,p0,false)
        return productViewHolder(productView)

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int)
    {
        (p0 as productViewHolder).initialiseRowUIComponents(arrayList.get(p1).id,arrayList.get(p1).Name,arrayList.get(p1).Price,arrayList.get(p1).pictureName)
    }

    inner class productViewHolder(pview:View):RecyclerView.ViewHolder(pview)
    {
        fun initialiseRowUIComponents(id:Int,name:String,price:Int,picture:String)
        {
            itemView.textid.text = id.toString()
            itemView.textname.text = name
            itemView.textprice.text = price.toString()

            var picUrl = "http://192.168.1.4/online_store/pictures/"
            picUrl = picUrl.replace(" ","%20")          //20 replace any empty space with%20
            Picasso.get().load(picUrl + picture).into(itemView.imgProduct)

             itemView.EproductRow_Add.setOnClickListener {

                 var amount_fragment = AmountFragment()
                 var fragment_manager = (itemView.context as Activity).fragmentManager
                 //amount_fragment.show(amount_fragment,"TAG")

             }

        }
    }
}