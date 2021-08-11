package hello.servlet.web.frontcontroller.v4

import hello.servlet.web.frontcontroller.MyView
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "FrontControllerServletV4", urlPatterns = ["/front-controller/v4/*"])
class FrontControllerServletV4 : HttpServlet() {

    private val controllerMap = HashMap<String, ControllerV4>()

    init {
        controllerMap["/front-controller/v4/members/new-form"] = MemberFormControllerV4()
        controllerMap["/front-controller/v4/members/save"] = MemberSaveControllerV4()
        controllerMap["/front-controller/v4/members"] = MemberListControllerV4()
    }

    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {
        println("FrontControllerServletV4.service")

        val requestURI = request?.requestURI

        val controller = controllerMap[requestURI]
        if (controller == null) {
            response?.status = HttpServletResponse.SC_NOT_FOUND
            return
        }

        // paramMap
        val paramMap = createParamMap(request)
        val model = hashMapOf<String, Any>() // 모델 추가
        val viewName = controller.process(paramMap, model)

        val view = viewResolver(viewName)

        view.render(model, request, response)
    }

    private fun viewResolver(viewName: String) = MyView("/WEB-INF/views/${viewName}.jsp")

    private fun createParamMap(request: HttpServletRequest?): HashMap<String, String> {
        val paramMap = HashMap<String, String>()
        request?.parameterNames?.asIterator()
            ?.forEachRemaining { paramMap[it] = request.getParameter(it) }
        return paramMap
    }
}

