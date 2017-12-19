package com.github.paulomigalmeida.downloadfilefromtarins3.base.controller;

import java.util.logging.Logger;

public abstract class AbstractController<T> {
    // Logger
    protected Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    public abstract T execute() throws Exception;
}