package kr.ac.kumoh.s2025115347.w26w12carddealer.model

data class Card(
    val rank: String,
    val suit: String,
) {
    val imageName: String
        get() = when (rank) {
            "jack", "queen", "king" -> "${rank}_of_${suit}2.png"
            else -> "${rank}_of_${suit}.png"
        }

    // 정렬 기준 값 (0~51): 무늬(spades < diamonds < hearts < clubs) 먼저, 같은 무늬면 2 < ... < king < ace
    val value: Int
        get() = SUITS.indexOf(suit) * RANKS.size + RANKS.indexOf(rank)

    companion object {
        val SUITS = listOf("spades", "diamonds", "hearts", "clubs")
        val RANKS = listOf("2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace")
    }
}