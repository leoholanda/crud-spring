package com.holanda.exception;

import java.io.Serial;

public class RecordDuplicateException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public RecordDuplicateException(String message) {
        super(message);
    }
}
