package com.lvh.testandroid.model


data class UserResponse(
        var status: Boolean,
        var error_message: String,
        var results: List<UserEntity>,
        var info: InfoEntity,
        var error_code: String
)
