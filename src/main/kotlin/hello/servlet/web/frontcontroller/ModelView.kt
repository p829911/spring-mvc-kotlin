package hello.servlet.web.frontcontroller

class ModelView(val viewName: String) {
    private val model = HashMap<String, Any>()

    fun getModel(): MutableMap<String, Any> {
        return model
    }
}