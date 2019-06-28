package com.example.myapplication

class EProduct {

    var id:Int
    val Name:String
    var Price:Int
    //var brand:String
    var pictureName:String

    constructor(id:Int,name:String,price:Int,pictures:String)
    {
        this.id = id
        this.Name = name
        this.Price = price
        this.pictureName = pictures
    }
}