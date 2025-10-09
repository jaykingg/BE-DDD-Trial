package jayking.splearn.application.provided

import jayking.splearn.domain.Member
import jayking.splearn.domain.MemberRegisterRequest

/**
 * 회원 등록과 관련된 기능을 제공한다.
 */
interface MemberRegister {
    fun register(MemberRegisterRequest: MemberRegisterRequest): Member
}