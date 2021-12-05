package com.example.weatherapi.common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T> {

    @NonNull
    public final String message;
    public final T data;
    public final Status status;

    public Resource(@NonNull String message, T data, Status status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }


    /*public static <T> Resource<T> success(T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> loading() {
        return new Resource<>(Status.LOADING, null, null);
    }

    public static <T> Resource<T> error(@Nullable T data, String message) {
        return new Resource<>(Status.ERROR, data, message);
    }*/


    public enum Status {
        SUCCESS,
        LOADING,
        ERROR
    }
}
