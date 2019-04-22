package com.code.codegen.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@SuppressWarnings("ALL")
@ToString
public class R<T> implements Serializable {
    private static final String SUCCESS = "0";
    private static final String FAIL = "1";
    private static final long serialVersionUID = 1L;
    @Getter
    @Setter
    private String msg = "success";

    @Getter
    @Setter
    private String code = SUCCESS;

    @Getter
    @Setter
    private T data;

    public R() {
        super();
    }

    public R(T data) {
        super();
        this.data = data;
    }

    public R(T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
    }
    public R(T data, String code, String msg) {
        super();
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public R(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.code = FAIL;
    }

}
