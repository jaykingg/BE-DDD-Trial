package jayking.splearn.domain

import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table

/**
 * @Table 은 Database DDL 에서 물리적으로 선언한 것을 명셈목적으로 작성한 것.
 */
@Entity
@Table(name = "member")
class Member protected constructor(
) {
    @Id
    var id: Long? = null
        internal set

    @Embedded
    lateinit var email: Email
        internal set

    lateinit var nickName: String
        internal set

    lateinit var passwordHash: String
        internal set

    @Enumerated(EnumType.STRING)
    lateinit var status: MemberStatus
        internal set

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
        fun register(request: MemberRegisterRequest, passwordEncoder: PasswordEncoder): Member {
            return Member().apply {
                email = Email(request.email)
                nickName = request.nickName
                passwordHash = passwordEncoder.encode(request.password)
                status = MemberStatus.PENDING
            }
        }
    }
}