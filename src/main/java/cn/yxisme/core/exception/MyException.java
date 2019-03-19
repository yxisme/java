package cn.yxisme.core.exception;

/**
 * Created by yangxiong on 2019/3/5.
 */
public class MyException extends Exception {
    private static final long serialVersionUID = 3162989605548935190L;

    public MyException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public MyException(CodeMessage codeMessage) {
        this.code = codeMessage.getCode();
        this.msg = codeMessage.getMsg();
    }

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
