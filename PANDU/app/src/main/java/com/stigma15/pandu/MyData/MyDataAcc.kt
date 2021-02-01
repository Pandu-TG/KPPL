package com.stigma15.pandu.MyData

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MyDataAcc(
    var id: String? = null,
    var username: String? = null,
    var nohp: Int? = null
) : Parcelable