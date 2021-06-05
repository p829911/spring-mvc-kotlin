package hello.servlet.basic.request

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import hello.servlet.basic.HelloData
import org.springframework.util.StreamUtils
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = ["/request-body-json"])
class RequestBodyJsonServlet : HttpServlet() {

    private val objectMapper = jacksonObjectMapper()

    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {
        val inputStream = request?.inputStream
        val messageBody = StreamUtils.copyToString(inputStream, charset("UTF-8"))

        println("messageBody = $messageBody")

        val helloData = objectMapper.readValue(messageBody, HelloData::class.java)

        println("helloData.username = ${helloData.username}")
        println("helloData.age = ${helloData.age}")

        response?.writer?.write("ok")
    }
}