import org.helloworld.scala.scalatra.MyScalatraServlet
import org.scalatra._
import javax.servlet.ServletContext
import org.helloworld.scala.scalatra.MainScalatraServlet

/**
 * This is the Scalatra bootstrap file. You can use it to mount servlets or
 * filters. It's also a good place to put initialization code which needs to
 * run at application start (e.g. database configurations), and init params.
 */
class Scalatra extends LifeCycle {
  override def init(context: ServletContext) {

    // Mount one or more servlets
    context.mount(new MyScalatraServlet, "/*")
    context.mount(new MainScalatraServlet, "/hello")

    // Set up init params
    // org.scalatra.cors.allowedOrigins = "http://example.com"
  }
}
