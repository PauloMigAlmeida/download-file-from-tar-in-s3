package com.github.paulomigalmeida.downloadfilefromtarins3.upload.controller;

import java.util.logging.Logger;

abstract class AbstractController<T> {
    // Logger
    protected Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    abstract T execute() throws Exception;
}