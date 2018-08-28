package top.felixu.model;

/**
 * @Author felixu
 * @Date 2018.08.09
 */
public class PayResult {
    private int status;
    private String msg;

    public PayResult(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}