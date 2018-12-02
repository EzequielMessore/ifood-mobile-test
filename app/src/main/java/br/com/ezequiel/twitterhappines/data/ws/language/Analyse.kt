package br.com.ezequiel.twitterhappines.data.ws.language

data class Analyse(
    val document: AnalyseDocument,
    val encodingType: String = "UTF8"
)

data class AnalyseDocument(
    val content: String,
    val type: String = "PLAIN-TEXT"
)
