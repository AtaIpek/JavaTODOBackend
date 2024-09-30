package com.ataipek.todo.exception;

public class ApplicationException extends RuntimeException{
    public ApplicationException(String message){
        super(message);
    }
}
