package hello.servlet.basic.request

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "requestHeaderServlet", urlPatterns = ["/request-header"])
class RequestHeaderServlet : HttpServlet() {
    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {
        printStartLine(request)
        printHeaders(request)
        printHeaderUtils(request)
    }

    private fun printStartLine(request: HttpServletRequest?) {
        println("--- REQUEST-LINE - start ---")

        println("request.method = ${request?.method}") // GET
        println("request.protocol = ${request?.protocol}") // HTTP/1.1
        println("request.scheme = ${request?.scheme}") // http
        // http://localhost:8080/request-header
        println("request.requestURL = ${request?.requestURL}")
        // /request-test
        println("request.requestURI = ${request?.requestURI}")
        // username=hi
        println("request.queryString = ${request?.queryString}")
        println("request.isSecure = ${request?.isSecure}")
        println("--- REQUEST-LINE - end ---")
        println()
    }

    private fun printHeaders(request: HttpServletRequest?) {
        request?.headerNames?.asIterator()
            ?.forEachRemaining { println("${it}: $it") }
        println("--- Headers - end ---")
        println()
    }

    private fun printHeaderUtils(request: HttpServletRequest?) {
        println("--- Header 편의 조회 start ---")
        println("[Host 편의 조회]")
        println("request.serverName = ${request?.serverName}") // Host 헤더
        println("request.serverPort = ${request?.serverPort}") // Host 헤더
        println()
        println("[Accept-Language 편의 조회]")
        request?.locales?.asIterator()
            ?.forEachRemaining { println("locale = $it") }
        println("request.locale = ${request?.locale}")
        println()
        println("[cookie 편의 조회]")
        if (request?.cookies != null) for (cookie in request.cookies) {
            println("${cookie.name}: ${cookie.value}")
        }
        println()
        println("[Content 편의 조회]")
        println("request.contentType = ${request?.contentType}")
        println("request.contentLength = ${request?.contentLength}")
        println("request.characterEncoding = ${request?.characterEncoding}")
        println("--- Header 편의 조회 end ---")
        println()
    }
}