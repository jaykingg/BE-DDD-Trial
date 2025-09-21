package jayking.splearn.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class MemberTest {
    @Test
    fun create() {
        val member = Member.create("jayking@splearn.kr", "jayking", "secret")

        assertThat(member.status).isEqualTo(MemberStatus.PENDING)
    }

    @Test
    fun activate() {
        val member = Member.create("jayking@splearn.kr", "jayking", "secret")
        member.activate()

        assertThat(member.status).isEqualTo(MemberStatus.ACTIVE)
    }


    @Test
    fun activateFail() {
        val member = Member.create("jayking@splearn.kr", "jayking", "secret")
        member.activate()

        assertThatThrownBy{ member.activate() }.isInstanceOf(IllegalStateException::class.java)
    }


    @Test
    fun deactivate() {
        val member = Member.create("jayking@splearn.kr", "jayking", "secret")
        member.activate()

        member.deactivate()

        assertThat(member.status).isEqualTo(MemberStatus.DEACTIVATED)
    }

    @Test
    fun deactivateFail() {
        val member = Member.create("jayking@splearn.kr", "jayking", "secret")

        assertThatThrownBy{ member.deactivate() }.isInstanceOf(IllegalStateException::class.java)

        member.activate()
        member.deactivate()

        assertThatThrownBy{ member.deactivate() }.isInstanceOf(IllegalStateException::class.java)


    }

}