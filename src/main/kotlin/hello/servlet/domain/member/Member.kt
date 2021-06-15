package hello.servlet.domain.member

data class Member(val username: String, val age: Int) {
    internal var id = 0L
}