package hello.servlet.web.frontcontroller.v3.controller

import hello.servlet.domain.member.MemberRepository
import hello.servlet.web.frontcontroller.ModelView
import hello.servlet.web.frontcontroller.v3.ControllerV3

class MemberListControllerV3 : ControllerV3 {

    private val memberRepository = MemberRepository.getInstance()

    override fun process(paramMap: HashMap<String, String>): ModelView {
        val members = memberRepository.findAll()
        val mv = ModelView("members")
        mv.getModel()["members"] = members

        return mv
    }

}