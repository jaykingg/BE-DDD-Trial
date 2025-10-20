package jayking.splearn.application

import jakarta.persistence.EntityManager
import jayking.splearn.application.required.MemberRepository
import jayking.splearn.domain.Member
import jayking.splearn.fixture.MemberFixture.Companion.createPasswordEncoder
import jayking.splearn.fixture.MemberFixture.Companion.createMemberRegisterRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class MemberRepositoryTest @Autowired constructor(
    private val memberRepository: MemberRepository,
    private val entityManager: EntityManager,
) {
    @Test
    fun createMember() {
        val member = Member.register(createMemberRegisterRequest(), createPasswordEncoder())
        memberRepository.save(member)
        entityManager.flush()
    }
}
