package hello.servlet.web.frontcontroller.v3

import hello.servlet.web.frontcontroller.ModelView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

interface ControllerV3 {
    fun process(paramMap: HashMap<String, String>): ModelView
}