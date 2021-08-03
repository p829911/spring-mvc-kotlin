package hello.servlet.web.frontcontroller.v3

import hello.servlet.web.frontcontroller.ModelView
import hello.servlet.web.frontcontroller.MyView
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "FrontControllerServletV3", urlPatterns = ["/front-controller/v3/*"])
class FrontControllerServletV3 : HttpServlet() {

    private val controllerMap = HashMap<String, ControllerV3>()

    init {
        controllerMap["/front-controller/v3/members/new-form"] = MemberFormControllerV3()
        controllerMap["/front-controller/v3/members/save"] = MemberSaveControllerV3()
        controllerMap["/front-controller/v3/members"] = MemberListControllerV3()
    }

    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {
        println("FrontControllerServletV3.service")

        val requestURI = request?.requestURI

        val controller = controllerMap[requestURI]
        if (controller == null) {
            response?.status = HttpServletResponse.SC_NOT_FOUND
            return
        }

        // paramMap
        val paramMap = createParamMap(request)
        val mv: ModelView = controller.process(paramMap)

        val viewName = mv.viewName
        val view = viewResolver(viewName)

        view.render(mv.getModel(), request, response)
    }

    private fun viewResolver(viewName: String) = MyView("/WEB-INF/views/${viewName}.jsp")

    private fun createParamMap(request: HttpServletRequest?): HashMap<String, String> {
        val paramMap = HashMap<String, String>()
        request?.parameterNames?.asIterator()
            ?.forEachRemaining { paramMap[it] = request.getParameter(it) }
        return paramMap
    }
}

