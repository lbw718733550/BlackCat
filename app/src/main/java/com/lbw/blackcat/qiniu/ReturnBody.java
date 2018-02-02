package com.lbw.blackcat.qiniu;

/**
 * @autor lbw
 * @time 2018/1/31 10:44
 * @desc 七牛云 上传 用户定义的反馈信息模板
 */

public class ReturnBody {

    public long deadline;
    public String scope;


    public ReturnBody() {
    }

    public ReturnBody(long deadline, String scope) {
        this.deadline = deadline;
        this.scope = scope;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "ReturnBody{" +
                "deadline='" + deadline + '\'' +
                ", scope='" + scope + '\'' +
                '}';
    }
}
