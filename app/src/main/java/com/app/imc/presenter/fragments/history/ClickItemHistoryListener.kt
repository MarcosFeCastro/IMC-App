package com.app.imc.presenter.fragments.history

import com.app.imc.domain.Imc

interface ClickItemHistoryListener {
    fun clickItemHistory(result: Imc)
}