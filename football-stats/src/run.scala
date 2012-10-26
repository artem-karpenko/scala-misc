import java.util.Date

/**
 *
 * @author Artem
 */

val p1 = new Player("Dima")
val p2 = new Player("Zhenya")
val p3 = new Player("Pasha")
val p4 = new Player("Maxim")
GameManager.players ++= Set(p1, p2, p3, p4)

val t1 = new Team(p1, p2)
val t2 = new Team(p3, p4)

val t3 = new Team(p1, p3)
val t4 = new Team(p2, p4)

val g1 = new Game(Map(t1 -> 3, t2 -> 2), new Date())
val g2 = new Game(Map(t3 -> 1, t4 ->  2), new Date())
GameManager.games ++= Set(g1, g2)

GameManager.players.foreach((p: Player) => println(p + ": " + GameManager.getStatsPerPlayer(p)))
