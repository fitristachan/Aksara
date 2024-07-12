package com.aksara.ui.component

data class RulesData(
    val id: Int,
    val text: String
)

val rulesItems = listOf(
    RulesData(1, "This is the first long text that should be expandable. This is the first long text that should be expandable. This is the first long text that should be expandable. This is the first long text that should be expandable."),
    RulesData(2, "This is the second long text that should be expandable."),
    RulesData(3, "This is the third long text that should be expandable."),
    RulesData(4, "This is the fourth long text that should be expandable."),
    RulesData(5, "This is the fifth long text that should be expandable."),
    RulesData(6, "This is the sixth long text that should be expandable."),
    RulesData(7, "This is the seventh long text that should be expandable."),
    RulesData(8, "This is the eighth long text that should be expandable.")
)
