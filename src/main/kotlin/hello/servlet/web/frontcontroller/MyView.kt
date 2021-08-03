package hello.servlet.web.frontcontroller

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MyView(private val viewPath: String) {

    fun render(request: HttpServletRequest?, response: HttpServletResponse?) {
        val dispatcher = request?.getRequestDispatcher(viewPath)
        dispatcher?.forward(request, response)
    }

    fun render(model: MutableMap<String, Any>, request: HttpServletRequest?, response: HttpServletResponse?) {
        modelToRequestAttribute(model, request)
        val dispatcher = request?.getRequestDispatcher(viewPath)
        dispatcher?.forward(request, response)
    }

    private fun modelToRequestAttribute(
        model: MutableMap<String, Any>,
        request: HttpServletRequest?
    ) {
        model.forEach { (key, value) -> request?.setAttribute(key, value) }
    }

}