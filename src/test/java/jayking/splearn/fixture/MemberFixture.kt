package jayking.splearn.fixture

import jayking.splearn.domain.MemberRegisterRequest
import jayking.splearn.domain.PasswordEncoder

class MemberFixture private constructor() {
    companion object {
        fun createMemberRegisterRequest(email: String):MemberRegisterRequest {
            return MemberRegisterRequest(email, "jayking", "secret")
        }

        fun createMemberRegisterRequest(): MemberRegisterRequest {
            return createMemberRegisterRequest("jayking@splearn.kr")
        }

        fun createPasswordEncoder() = object : PasswordEncoder {
            override fun encode(password: String): String {
                return password.uppercase()
            }

            override fun matches(password: String, passwordHash: String): Boolean {
                return encode(password) == passwordHash
            }
        }
    }
}
