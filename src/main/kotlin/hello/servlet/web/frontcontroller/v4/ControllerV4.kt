package hello.servlet.web.frontcontroller.v4

interface ControllerV4 {

    /**
     * @param paramMap
     * @param model
     * @return viewName
     */
    fun process(paramMap: HashMap<String, String>, model: HashMap<String, Any>): String

}