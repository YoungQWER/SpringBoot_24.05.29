package com.LiveCommerce.exception;

public class OutOfStockException  extends  RuntimeException{

    public OutOfStockException(String message){
        super(message);
    }
}
