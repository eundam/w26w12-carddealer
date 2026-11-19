package kr.ac.kumoh.s2025115347.w26w12carddealer.repository

import kr.ac.kumoh.s2025115347.w26w12carddealer.model.Card

interface CardRepository {
    val cards: List<Card>
    fun save(card: Card)
    fun deleteAll()
}
