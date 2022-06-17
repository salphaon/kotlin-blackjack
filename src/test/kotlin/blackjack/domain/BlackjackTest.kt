package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class BlackjackTest {

    @Test
    fun `blackjack은 player와 deck을 가진다`() {
        val blackjack = Blackjack(listOf(Player("player")))

        assertThat(blackjack.players).hasSize(1)
    }

    @Test
    fun `player는 게임 시작 시 카드 0장을 가진다`() {
        val player = Player("player")
        val blackjack = Blackjack(listOf(player))

        assertThat(blackjack.players).hasSize(1)
        assertThat(blackjack.players.first().cards).hasSize(0)
    }

    @Test
    fun `player는 카드를 받을 수 있다`() {
        val player = Player("player")
        val blackjack = Blackjack(listOf(player))
        blackjack.drawCard(player)

        assertThat(blackjack.players).hasSize(1)
        assertThat(blackjack.players.first().cards).hasSize(1)
    }

    @Test
    fun `player는 21점 이상이면 카드를 받을 수 없다`() {
        val player = Player("player")
        val blackjack = Blackjack(listOf(player))

        player.addCards(
            listOf(
                Card(Suit.SPADE, Denomination.ACE),
                Card(Suit.SPADE, Denomination.TEN)
            )
        )

        assertThrows<java.lang.IllegalArgumentException> { blackjack.drawCard(player) }
    }

    @Test
    fun `player는 게임 시작 후 카드 2장을 받을 수 있다`() {
        val player = Player("player")
        val blackjack = Blackjack(listOf(player))
        blackjack.drawFirstCards()

        assertThat(blackjack.players).hasSize(1)
        assertThat(blackjack.players.first().cards).hasSize(2)
    }
}
