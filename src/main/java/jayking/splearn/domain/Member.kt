package jayking.splearn.domain


class Member private constructor(
    val email: String,
    var nickName: String,
    var passwordHash: String,
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

    fun verifyPassword(password: String, passwordEncoder: PasswordEncoder): Boolean {
        return passwordEncoder.matches(password, this.passwordHash)
    }

    fun changeNickname(nickName: String) {
        this.nickName = nickName
    }

    fun changePassword(password: String,passwordEncoder: PasswordEncoder) {
        this.passwordHash = passwordEncoder.encode(password)
    }

    fun isActive(): Boolean {
        return this.status == MemberStatus.ACTIVE
    }


    companion object {
        fun create(request: MemberCreateRequest, passwordEncoder: PasswordEncoder): Member {
            return Member(
                email = request.email,
                nickName = request.nickName,
                passwordHash = passwordEncoder.encode(request.password),
                status = MemberStatus.PENDING
            )
        }
    }
}