package jayking.splearn.domain

data class MemberCreateRequest(
    val email: String,
    val nickName: String,
    val password: String
)
