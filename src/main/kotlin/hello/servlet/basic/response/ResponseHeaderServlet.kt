package hello.servlet.basic.response

import javax.servlet.annotation.WebServlet
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "responseHeaderServlet", urlPatterns = ["/response-header"])
class ResponseHeaderServlet : HttpServlet() {
    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {
        // [status-line]
        response?.status = HttpServletResponse.SC_OK

        // [response-headers]
        response?.setHeader("Content-Type", "text/plain;charset=utf-8")
        response?.setHeader("Cache-Control", "no-cache, no-store, must-revalidate")
        response?.setHeader("Pragma", "no-cache")
        response?.setHeader("my-header", "Hello")

        // [Header 편의 메서드]
//        content(response)
//        cookie(response)
        redirect(response)
        val writer = response?.writer
        writer?.write("ok")
    }

    private fun content(response: HttpServletResponse?) {
        response?.contentType = "text/plain"
        response?.characterEncoding = "utf-8"
    }

    private fun cookie(response: HttpServletResponse?) {
        val cookie = Cookie("myCookie", "good")
        cookie.maxAge = 600
        response?.addCookie(cookie)
    }

    private fun redirect(response: HttpServletResponse?) {
//        response?.status = HttpServletResponse.SC_FOUND
//        response?.setHeader("Location", "/basic/hello-form.html")
        response?.sendRedirect("/basic/hello-form.html")
    }
}