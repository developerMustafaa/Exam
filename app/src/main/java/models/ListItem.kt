package models

import android.graphics.drawable.Drawable

class ListItem(private val image: Int, private var label: String) {
    fun getImage(): Int {
        return image
    }

    fun getLabel(): String {
        return label
    }
}