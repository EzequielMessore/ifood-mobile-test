package br.com.ezequiel.twitterhappines.data.ws.language

import com.google.gson.annotations.SerializedName

class Result(
    @SerializedName("documentSentiment") val document: ResultDocument
)

class ResultDocument(val score: Double)