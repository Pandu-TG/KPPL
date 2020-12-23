package com.stigma15.pandu.MyData

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MyDataRecomendation (
        var title: String,
        var locationd: String,
        var image: String
) : Parcelable