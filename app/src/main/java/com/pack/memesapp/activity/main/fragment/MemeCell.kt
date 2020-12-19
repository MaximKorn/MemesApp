package com.pack.memesapp.activity.main.fragment

import com.pack.memesapp.network.models.MemeData

class MemeCell(var id: String, var photoUrl: String,
               var title: String, var isFavorite: Boolean,
               var description: String, var createdDate: Number) {

    constructor(meme: MemeData) : this(
        meme.id,
        meme.photoUrl,
        meme.title,
        meme.isFavorite,
        meme.description,
        meme.createdDate
    ) {}
}