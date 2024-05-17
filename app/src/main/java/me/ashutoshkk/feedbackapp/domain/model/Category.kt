package me.ashutoshkk.feedbackapp.domain.model

enum class Category(val value: String) {
    CONFIDENCE("Confidence"),
    GRAMMAR("Grammar"),
    FLUENCY_AND_VOCABULARY("Fluency And Vocabulary"),
    PRONUNCIATION("Pronunciation"),
    OTHER("Other"),
    UNSPECIFIED("Unspecified"),
}

fun getCategoryFromString(value: String): Category {
    for (category in Category.entries) {
        if (category.value.equals(value, ignoreCase = true)) {
            return category
        }
    }
    return Category.UNSPECIFIED
}