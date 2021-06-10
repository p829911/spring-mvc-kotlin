package hello.servlet.basic.response

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import hello.servlet.basic.HelloData
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "responseJsonServlet", urlPatterns = ["/response-json"])
class ResponseJsonServlet : HttpServlet() {

    private val objectMapper = jacksonObjectMapper()

    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {
        // Content-Type: application/json
        response?.contentType = "application/json"
        response?.characterEncoding = "utf-8"

        val helloData = HelloData("kim", 20)

        // {"username": "kim", "age": 20}

        val result = objectMapper.writeValueAsString(helloData)
        val writer = response?.writer
        writer?.write(result)
    }
}