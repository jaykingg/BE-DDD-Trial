package jayking.splearn.domain

import jayking.splearn.fixture.MemberFixture.Companion.createPasswordEncoder
import jayking.splearn.fixture.MemberFixture.Companion.createMemberRegisterRequest
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MemberTest {
    private lateinit var member: Member
    private lateinit var passwordEncoder: PasswordEncoder

    @BeforeEach
    fun setUp() {
        this.passwordEncoder = createPasswordEncoder()
        member = Member.register(createMemberRegisterRequest(), passwordEncoder)
    }

    @Test
    fun register() {
        assertThat(member.status).isEqualTo(MemberStatus.PENDING)
    }

    @Test
    fun activate() {
        member.activate()

        assertThat(member.status).isEqualTo(MemberStatus.ACTIVE)
    }


    @Test
    fun activateFail() {
        member.activate()

        assertThatThrownBy{ member.activate() }.isInstanceOf(IllegalStateException::class.java)
    }


    @Test
    fun deactivate() {
        member.activate()

        member.deactivate()

        assertThat(member.status).isEqualTo(MemberStatus.DEACTIVATED)
    }

    @Test
    fun deactivateFail() {
        assertThatThrownBy{ member.deactivate() }.isInstanceOf(IllegalStateException::class.java)

        member.activate()
        member.deactivate()

        assertThatThrownBy{ member.deactivate() }.isInstanceOf(IllegalStateException::class.java)

    }

    @Test
    fun verifyPassword() {
        assertThat(member.verifyPassword("secret",passwordEncoder)).isTrue
        assertThat(member.verifyPassword("hello",passwordEncoder)).isFalse
    }

    @Test
    fun changeNickname() {
        assertThat(member.nickName).isEqualTo("jayking")

        member.changeNickname("mankai")

        assertThat(member.nickName).isEqualTo("mankai")
    }

    @Test
    fun changePassword() {
        member.changePassword("verysecret",passwordEncoder)

        assertThat(member.verifyPassword("verysecret",passwordEncoder)).isTrue
    }

    @Test
    fun isActiveTest() {
        assertThat(member.isActive()).isFalse

        member.activate()

        assertThat(member.isActive()).isTrue

        member.deactivate()

        assertThat(member.isActive()).isFalse
    }

    @Test
    fun invalidEmailTest() {
        assertThatThrownBy { Member.register(createMemberRegisterRequest("invalid email"), passwordEncoder) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

}
