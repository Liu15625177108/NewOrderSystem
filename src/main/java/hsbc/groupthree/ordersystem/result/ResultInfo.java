package hsbc.groupthree.ordersystem.result;

/**
 * @ClassName ResultInfo
 * @Author:Jerry.Liu;
 * @Description:the class of the result view.
 * @Package hsbc.groupthree.ordersystem.result
 * @Date 2018/8/17 10:46
 */
public class ResultInfo <T>{
    private int code;                    // the return code.
    private String msg;                     // the return msg;
    private T data;                         //the return data


    public  ResultInfo(){}

    public ResultInfo(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
