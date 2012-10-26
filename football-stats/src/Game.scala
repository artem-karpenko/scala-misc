import collection.immutable.HashSet
import java.util.Date

/**
 * Game between two teams and a result.
 * One game usually consists of several matches and therefore denotes how many matches each team won.
 * Resulting goals are not gathered.
 *
 * @author Artem
 */
class Game(val results: Map[Team, Int], date: Date) {
   def getPlayerTeam(player: Player) = {
      results.keys.find(_.includes(player))
   }

   def getWonMatches(team: Team) = {
      results.get(team)
   }

   def getTotalMatches() = {
      results.values.sum
   }
}
