package jayking.splearn.domain


class Member private constructor(
    val email: String,
    val nickName: String,
    val passwordHash: String,
    var status: MemberStatus
) {
    fun activate() {
        check(this.status == MemberStatus.PENDING) { "member is not pending" }

        this.status = MemberStatus.ACTIVE
    }

    fun deactivate() {
        check(this.status == MemberStatus.ACTIVE) { "member is not active" }

        this.status = MemberStatus.DEACTIVATED
    }

    companion object {
        fun create(email: String, nickName: String, passwordHash: String) = Member(
            email, nickName, passwordHash, status = MemberStatus.PENDING
        )
    }
}