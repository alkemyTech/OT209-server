package com.alkemy.ong.exception;

public class UserNotMatch extends RuntimeException{

    public UserNotMatch(String mensaje){
        super(mensaje);
    }

}
