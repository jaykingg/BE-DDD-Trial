package jayking.splearn.application.required

import jayking.splearn.domain.Member
import org.springframework.data.repository.Repository

/**
 * 회원 정보를 저장하거나 조회한다.
 */
interface MemberRepository: Repository<Member, Long>{
    fun save(member: Member): Member
}
