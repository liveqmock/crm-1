package com.deppon.foss.framework.server.components.jobgrid;

public class GridJobException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public GridJobException() {
        super();
    }

    public GridJobException(String message, Throwable cause) {
        super(message, cause);
    }

    public GridJobException(String message) {
        super(message);
    }

    public GridJobException(Throwable cause) {
        super(cause);
    }

}
