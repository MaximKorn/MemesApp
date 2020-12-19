package com.pack.memesapp.activity.meme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.pack.memesapp.R
import com.pack.memesapp.activity.main.fragment.MemeCell
import java.util.*

class DetailedMemeActivity : AppCompatActivity() {

    lateinit var memeCell: MemeCell

    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var memeImage: ImageView
    private lateinit var isFavoriteButton: ImageButton
    private lateinit var createdDate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_meme)

        memeCell = Gson().fromJson(intent.getStringExtra("memeCell"), MemeCell::class.java)

        title = findViewById(R.id.memeTitle)
        title.text = memeCell.title

        description = findViewById(R.id.memeDescription)
        description.text = memeCell.description

        memeImage = findViewById(R.id.memeImage)
        Glide.with(this).load(memeCell.photoUrl).into(memeImage)

        isFavoriteButton = findViewById(R.id.buttonAddToFav2)
        isFavoriteButton.isSelected = memeCell.isFavorite

        createdDate = findViewById(R.id.createdDateText)
        val days: Long = (Calendar.getInstance().timeInMillis / 1000 - memeCell.createdDate.toLong()) / 60 / 60 / 24
        createdDate.text = getString(R.string.days_ago, days)
    }

    fun returnBack(view: View) {
        finish()
    }
}