package br.com.ezequiel.twitterhappines.presentation.tweet

enum class Humor(val emoji: String) {
    HAPPY_EMOTION (String(Character.toChars(0x1F603))),
    NEUTRAL_EMOTION(String(Character.toChars(0x1F610))),
    SAD_EMOTION(String(Character.toChars(0x1F614)))
}