package br.com.ezequiel.twitterhappines.data.ws.language

class Analyse(
    val document: AnalyseDocument,
    val encodingType: String = "UTF8"
)

class AnalyseDocument(
    val content: String,
    val type: String = "PLAIN-TEXT"
)
