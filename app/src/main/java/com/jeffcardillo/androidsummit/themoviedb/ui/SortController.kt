package com.jeffcardillo.androidsummit.themoviedb.ui

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR


class SortController : BaseObservable() {
    var sortAscending = true
        private set

    @get:Bindable
    var nextSortText = SORT_ASC
        private set

    fun switchNextSortDirection() {
        sortAscending = !sortAscending

        this.nextSortText = if (sortAscending) SORT_ASC else SORT_DESC

        notifyPropertyChanged(BR.nextSortText)
    }

    companion object {
        private const val SORT_ASC = "Sort A...Z"
        private const val SORT_DESC = "Sort Z...A"
    }
}