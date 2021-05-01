package com.android.articleapp.utils.network.helper

data class Resource<out T>(val status: Status, val data: T?, val error: String?) {
    companion object {
        @JvmStatic
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        @JvmStatic
        fun <T> error(error: String?, data: T?): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                error
            )
        }

        @JvmStatic
        fun <T> loading(data: T?): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }
    }
}