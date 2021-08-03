package hello.servlet.web.servlet

import hello.servlet.domain.member.Member
import hello.servlet.domain.member.MemberRepository
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "memberSaveServlet", urlPatterns = ["/servlet/members/save"])
class MemberSaveServlet : HttpServlet() {

    private val memberRepository = MemberRepository.getInstance()

    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {
        val username = request?.getParameter("username")
        val age = Integer.parseInt(request?.getParameter("age"))

        val member = Member(username, age)
        memberRepository.save(member)

        response?.contentType = "text/html"
        response?.characterEncoding = "utf-8"

        val w = response?.writer
        w?.write("<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" + "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                "    <li>id="+member.id+"</li>\n" +
                "    <li>username="+member.username+"</li>\n" +
                " <li>age="+member.age+"</li>\n" + "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" + "</body>\n" +
                "</html>")
    }
}