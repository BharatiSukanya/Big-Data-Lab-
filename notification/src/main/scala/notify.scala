abstract class Notification
case class Email(sender: String, title: String, body: String) extends Notification
case class SMS(caller: String, message: String) extends Notification

object notify {
  def showNotification (notify : Notification) : String = {
    notify match {
      case Email(sender, title, _) =>
        s"You got an email from $sender \nTitle:\n\t '$title' \n"
      case SMS(caller, msg) =>
        s"\nYou got an SMS from '$caller'\n Message: \n\t'$msg'\n\n"
    }
  }
  def main(args: Array[String]): Unit = {
    var email=Email("sukanya.bharati@gmail.com","Hello","How are you?")
    var sms=SMS("+918005706953","Hello, How are You?")
    println(showNotification(email))
    println(showNotification(sms))
  }
}
