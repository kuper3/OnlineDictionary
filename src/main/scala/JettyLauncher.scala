/*
 * Starts jetty for scalatra programatically
 *
 */
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet._
import org.eclipse.jetty.webapp._
import com.github.kuper3.onlinedictionary.MainScalatraServlet

object JettyLauncher {
  def main(args: Array[String]) {
    val port = if(System.getenv("PORT") != null) System.getenv("PORT").toInt else 8080

    val server = new Server(port)
    //val context = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS)

    //context.addServlet(classOf[MainScalatraServlet], "/");
    val context = new WebAppContext()
    context.setContextPath("/")
    context.setResourceBase("src/main/webapp")
    context.addServlet(classOf[MainScalatraServlet], "/");

    server.setHandler(context);

    server.start
    server.join
  }

}
