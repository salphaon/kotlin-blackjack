package blackjack

import blackjack.ResultView.names

fun main() {
    val deck = buildDeck()

    val players = UserInput.StringList("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)").answer()
        .map { Player.Person(it) }

    val game = BlackJackGame(players, deck)

    game.prepareDraw {
        println()
        ResultView.prepare(it)
        println()
    }

    game.draw({ name ->
        drawQuestion(name)
    }) {
        println("${it.name}카드: ${it.cards.names()}")
    }

    ResultView.result(game)
}

private fun drawQuestion(name: String): Boolean {
    return when (UserInput.Char("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)").answer()) {
        'y' -> true
        'n' -> false
        else -> throw IllegalArgumentException()
    }
}

private fun buildDeck(): Blackjack {
    return blackjack {
        ace()
        normal(2..10)
        jack()
        queen()
        king()
    }.build()
}