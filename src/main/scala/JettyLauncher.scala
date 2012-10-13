/*
 * Starts jetty for scalatra programatically
 *
 * Replace YourApplicationEndpointFilter with the filter in your application
 */
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet._
import com.github.kuper3.onlinedictionary.MainScalatraServlet
import java.util.EnumSet
import javax.servlet.DispatcherType

object JettyLauncher {
  def main(args: Array[String]) {
    val port = if(System.getenv("PORT") != null) System.getenv("PORT").toInt else 8080

    val server = new Server(port)
    val context = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS)

    //context.addFilter(classOf[MainScalatraServlet], "/*", 0)
    context.addFilter(classOf[MainScalatraServlet], "/*", EnumSet.of(DispatcherType.INCLUDE))
    context.addServlet(classOf[DefaultServlet], "/");
    context.setResourceBase("src/main/scala")

    server.start
    server.join
  }

}
