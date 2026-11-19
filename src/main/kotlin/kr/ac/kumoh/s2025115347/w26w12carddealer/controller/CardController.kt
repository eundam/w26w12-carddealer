package kr.ac.kumoh.s2025115347.w26w12carddealer.controller

import kr.ac.kumoh.s2025115347.w26w12carddealer.service.CardService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class CardController(
    private val service: CardService
) {
    // 새로 뽑기
    @GetMapping("/cards/random")
    fun getRandomCards(model: Model): String {
        service.dealCards()

        val cards = service.getCards()

        model.addAttribute(
            "cards",
            cards.map { it.imageName }
        )
        return "cards"
    }

    // 지금 뽑은 카드를 정렬해서 보기 (order = asc: 작은 순, desc: 큰 순)
    @GetMapping("/cards/sort")
    fun getSortedCards(
        @RequestParam("order", defaultValue = "asc") order: String,
        model: Model
    ): String {
        // 아직 뽑은 카드가 없으면 먼저 뽑는다
        if (service.getCards().isEmpty()) {
            service.dealCards()
        }

        val cards = service.getSortedCards(descending = order == "desc")

        model.addAttribute(
            "cards",
            cards.map { it.imageName }
        )
        model.addAttribute("order", order)
        return "cards"
    }
}