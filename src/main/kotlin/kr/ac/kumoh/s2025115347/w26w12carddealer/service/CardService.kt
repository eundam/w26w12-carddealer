package kr.ac.kumoh.s2025115347.w26w12carddealer.service

import kr.ac.kumoh.s2025115347.w26w12carddealer.model.Card
import kr.ac.kumoh.s2025115347.w26w12carddealer.repository.CardRepository
import org.springframework.stereotype.Service

@Service
class CardService(
    private val repository: CardRepository
) {
    fun getCards(): List<Card> = repository.cards

    // 현재 뽑은 카드를 작은 순(오름차순) 또는 큰 순(내림차순)으로 정렬해서 반환
    fun getSortedCards(descending: Boolean): List<Card> {
        val sorted = repository.cards.sortedBy { it.value }
        return if (descending) sorted.reversed() else sorted
    }

    fun dealCards(count: Int = 5) {
        val suits = Card.SUITS
        val ranks = Card.RANKS

        repository.deleteAll()

        // 0~51 숫자를 섞은 뒤
        // count개만 뽑아서
        // 오름차순 정렬
        val cards = (0 until 52)
            .shuffled()
            .take(count)
            .sorted()

        // 뽑힌 숫자(0~51)를 무늬(suit)와 숫자(suit) 계산해서 저장
        cards.forEach { number ->
            val suit = suits[number / ranks.size]
            val rank = ranks[number % ranks.size]
            repository.save(Card(rank, suit))
        }
    }
}