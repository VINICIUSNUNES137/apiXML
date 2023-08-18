package br.senai.sp.jandira.retrofit_reqres

import com.google.gson.annotations.SerializedName

//formato da resposta <t> => generico
data class BaseResponse<T>(
    @SerializedName("data")
    var data: T? = null
)
