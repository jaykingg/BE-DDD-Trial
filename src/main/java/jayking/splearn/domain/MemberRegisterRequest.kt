package jayking.splearn.domain

data class MemberRegisterRequest(
    val email: String,
    val nickName: String,
    val password: String
)
