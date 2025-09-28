package jayking.splearn.domain

data class Email(
    val email: String
) {
    companion object {
        private val EMAIL_REGEX = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$")
    }

    init {
        require(EMAIL_REGEX.matches(email)) { throw IllegalArgumentException("invalid email address") }
    }

}
