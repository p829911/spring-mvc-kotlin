package hello.servlet.basic.request

import org.springframework.util.StreamUtils
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "requestBodyStringServlet", urlPatterns = ["/request-body-string"])
class RequestBodyStringServlet : HttpServlet() {
    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {
        val inputStream = request?.inputStream
        val messageBody = StreamUtils.copyToString(inputStream, charset("UTF-8"))

        println("messageBody = $messageBody")

        response?.writer?.write("ok")
    }
}