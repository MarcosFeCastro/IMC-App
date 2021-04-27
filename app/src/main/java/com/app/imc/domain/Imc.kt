package com.app.imc.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Imc(
        var id: Int = -1,
        var result: Float,
        var difference: Float
    // var date: Date
) : Parcelable