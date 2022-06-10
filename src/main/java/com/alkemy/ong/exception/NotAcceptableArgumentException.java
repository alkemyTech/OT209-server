package com.alkemy.ong.exception;

public class NotAcceptableArgumentException extends Exception{

    public NotAcceptableArgumentException(String message){

        super(message);
    }

    public NotAcceptableArgumentException(){
        super();
    }
}
