package com.example.jetpackcomposeapp.model

import com.google.gson.annotations.SerializedName

data class Move(
    val move: BasicApiResponse,
    @SerializedName("version_group_details") val versionGroupDetails: List<VersionGroupDetails>
)

data class VersionGroupDetails(
    @SerializedName("level_learned_at") val levelLearnedAt: Int,
    @SerializedName("move_learn_method") val moveLearnMethod: BasicApiResponse
)
