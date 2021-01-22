package com.haydikodlayalim.exchandling.exception;

public class EntityNotfoundException extends RuntimeException {
    public EntityNotfoundException(String param) { //Gelen parametreyi servisden gelecek.
        super(param);
    }
}
