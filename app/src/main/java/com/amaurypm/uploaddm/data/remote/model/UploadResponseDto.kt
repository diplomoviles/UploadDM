package com.amaurypm.uploaddm.data.remote.model

import com.google.gson.annotations.SerializedName

data class UploadResponseDto(
    @SerializedName("message")
    var message: String
)
