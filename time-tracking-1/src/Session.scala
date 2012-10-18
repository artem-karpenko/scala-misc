/**
 * @author Artem
 */
class Session {
   val userManager = new UserManager
   var currentUser: Option[User] = None

   def login(login: String, password: String) = {
      if (currentUser.isDefined) {
         throw new AlreadyLoggedInException
      }

      def isUserValid(u: User) = u.login == login && u.password == password

      if (userManager.existingUsers.exists(isUserValid)) {
         currentUser = userManager.existingUsers.find(isUserValid)
      }
      else {
         throw new InvalidCredentialsException
      }
   }

   def logout() = {
      if (!currentUser.isDefined) {
         throw new NotLoggedInException
      }
      currentUser.get.taskManager.stopAllTasksIfAny()
      currentUser = None
   }

   def isLoggedIn() = {
      currentUser.isDefined
   }
}

class SecurityException(message: String) extends Exception(message)
class AlreadyLoggedInException extends SecurityException("You are already logged in. Please log out first.")
class InvalidCredentialsException extends SecurityException("User does not exist or password is incorrect")
class NotLoggedInException extends SecurityException("Please log in first")