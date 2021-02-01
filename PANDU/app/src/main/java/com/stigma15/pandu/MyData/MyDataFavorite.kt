package com.stigma15.pandu.MyData

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MyDataFavorite(
        var nama: String,
        var locationd: String,
        var img: String
) : Parcelable
