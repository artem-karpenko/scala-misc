import collection.mutable

/**
 *
 * @author Artem
 */
object GameManager {
   val players: mutable.Set[Player] = new mutable.HashSet[Player]()
   val games: mutable.Set[Game] = new mutable.HashSet[Game]()

   /**
    *
    * @param player player to count stats for
    * @return a tuple (Int, Int) where _1 is total number of matches, _2 - number of won matches
    */
   def getStatsPerPlayer(player: Player) = {
      games.foldLeft((0, 0))((stats: (Int, Int), game: Game) => {
         val team: Option[Team] = game.getPlayerTeam(player)
         if (team.isDefined) {
            (stats._1 + game.getTotalMatches, stats._2 + game.getWonMatches(team.get).get)
         }
         else {
            (0, 0)
         }
      })
   }
}
