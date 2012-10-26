import collection.mutable

/**
 * Team of players
 * @author Artem
 */
class Team(val player1: Player, val player2: Player) {
   def includes(player: Player) = {
      player == player1 || player == player2
   }
}
