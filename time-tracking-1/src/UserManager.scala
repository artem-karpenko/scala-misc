/**
 * @author Artem
 */
class UserManager {
   val existingUsers: Set[User] = Set[User](
      new User("user1", "1"),
      new User("user2", "2")
   )

   def countTotalTime() = {
      existingUsers.foldLeft(0L)((total, u) => total + u.taskManager.countTotalTime())
   }
}
