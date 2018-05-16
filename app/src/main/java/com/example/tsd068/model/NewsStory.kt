package com.example.tsd068.model

data class NewsStory(
        val author: String,
        val title: String,
        val urlToImage: String,
        val description: String
)

data class Result (val totalResults: Int, val status: String, val articles: List<NewsStory>)
