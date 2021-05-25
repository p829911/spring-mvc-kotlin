package hello.servlet.basic

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "helloServlet", urlPatterns = ["/hello"])
class HelloServlet : HttpServlet() {
    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        println("HelloServlet.service")
        println("request = [${request}], response = [${response}]")

        val userName: String? = request.getParameter("username")
        println("userName: $userName")

        response.contentType = "text/plain"
        response.characterEncoding = "utf-8"
        response.writer.write("Hello $userName")
    }
}

