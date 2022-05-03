package com.joaovicttors.user_feature.data.models

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName(IMAGE) val image: String?,
    @SerializedName(NAME) val name: String?,
    @SerializedName(ID) val id: Int?,
    @SerializedName(USERNAME) val username: String?
) {

    private companion object {
        private const val IMAGE: String = "img"
        private const val NAME: String = "name"
        private const val ID: String = "id"
        private const val USERNAME: String = "username"
    }
}