package com.atexo.carte.exceptions;

public class CardException extends Exception {

    private static final long serialVersionUID = 1L;

    public CardException () {
        super();
    }

    public CardException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CardException(String msg) {
        super(msg);
    }

    public CardException(Throwable cause) {
        super(cause);
    }
}
