package com.example.domain.usecase.main.mypage

import com.example.domain.model.UserInfo
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(): Result<UserInfo> {
        return authRepository.getUserInfo()?.run {
            Result.success(this)
        } ?: Result.failure(Exception("유저 정보를 가져오는데 실패했습니다."))
    }
}