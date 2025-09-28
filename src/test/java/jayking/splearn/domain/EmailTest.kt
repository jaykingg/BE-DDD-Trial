package jayking.splearn.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EmailTest {

    @Test
    fun equality() {
        val email1 = Email("jayking@world.kr")
        val email2 = Email("jayking@world.kr")
        assertThat(email1).isEqualTo(email2)
    }
}