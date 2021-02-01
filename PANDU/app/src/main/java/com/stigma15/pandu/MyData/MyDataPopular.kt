package com.stigma15.pandu.MyData

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
    data class MyDataPopular (
            var name: String,
            var location: String,
            var photo: String
    ) : Parcelable