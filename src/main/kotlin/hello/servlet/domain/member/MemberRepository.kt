package hello.servlet.domain.member

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
class MemberRepository {
    companion object {
        private val store = hashMapOf<Long, Member>()
        private var id = 0L
        private val instance = MemberRepository()

        fun getInstance(): MemberRepository { return instance }
    }

    fun save(member: Member): Member {
        member.id = ++id
        store[member.id] = member
        return member
    }

    fun findById(id: Long): Member? {
        return store[id]
    }

    fun findAll(): List<Member> {
        return store.values.toList()
    }

    fun clearStore() {
        store.clear()
    }

}