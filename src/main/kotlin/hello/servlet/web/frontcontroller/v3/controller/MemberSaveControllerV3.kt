package hello.servlet.web.frontcontroller.v3.controller

import hello.servlet.domain.member.Member
import hello.servlet.domain.member.MemberRepository
import hello.servlet.web.frontcontroller.ModelView
import hello.servlet.web.frontcontroller.v3.ControllerV3

class MemberSaveControllerV3 : ControllerV3 {

    private val memberRepository = MemberRepository.getInstance()

    override fun process(paramMap: HashMap<String, String>): ModelView {
        val username = paramMap["username"]
        val age = Integer.parseInt(paramMap["age"])

        val member = Member(username, age)
        memberRepository.save(member)

        val mv = ModelView("save-result")
        mv.getModel()["member"] = member
        return mv
    }

}