package com.github.paulomigalmeida.downloadfilefromtarins3.base.operation;

public interface IOperation<T> {
    T performOperation() throws Exception;
}
