package com.mostafavi.home.food.Data

class Food {
    var name: String = ""
    var image: String = ""
    var description: String = ""
    var like: Int = 0
    var dateTime: Long = 0
    var liked: Boolean = false
    var user: User? = null


    constructor()


    constructor(name: String, dateTime: Long, image: String, description: String, like: Int, user: User?) {
        this.name = name
        this.image = image
        this.description = description
        this.like = like
        this.dateTime = dateTime
        this.user = user
    }


}