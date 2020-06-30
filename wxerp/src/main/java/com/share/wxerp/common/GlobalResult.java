package com.share.wxerp.common;

/**
 * @Description: 自定义响应数据结构
 *              这个类是提供给门户，ios，安卓，微信商城用的
 *              门户接受此类数据后需要使用本类的方法转换成对于的数据类型格式（类，或者list）
 *              其他自行处理
 *              200：表示成功
 *              201：（已创建）请求成功并且服务器创建了新的资源
 *              202：（已接受）服务器已接受请求，但尚未处理
 *              203：（非授权信息）服务器已成功处理了请求，但返回的信息可能来自另一来源
 *              204：（无内容）  服务器成功处理了请求，但没有返回任何内容
 *              205：（重置内容） 服务器成功处理了请求，但没有返回任何内容
 *              400：（错误请求）服务器不理解请求的语法
 *              401：（未授权） 请求要求身份验证
 *              403：（禁止） 服务器拒绝请求。
 *              404：（未找到） 服务器找不到请求的网页。
 *              500：表示错误，错误信息在msg字段中
 *              501：bean验证错误，不管多少个错误都以map形式返回
 *              502：拦截器拦截到用户token出错
 *              555：异常抛出信息
 *
 */
public class GlobalResult {

    // 响应业务状态
    private Integer respcode;

    // 响应消息
    private String  respmsg;

    // 响应中的数据
    private Object detail;


    public static GlobalResult build(Integer respcode, String respmsg, Object detail) {
        return new GlobalResult(respcode, respmsg, detail);
    }

    public static GlobalResult ok(Object detail) {
        return new GlobalResult(detail);
    }

    public static GlobalResult ok() {
        return new GlobalResult(null);
    }

    public static GlobalResult success(String respmsg, Object detail) {
        return new GlobalResult(200, respmsg, detail);
    }
    public static GlobalResult success(String respmsg) {
        return new GlobalResult(200, respmsg, null);
    }

    public static GlobalResult resubmit(String respmsg) {
        return new GlobalResult(204, respmsg, null);
    }

    public static GlobalResult error(String respmsg) {
        return new GlobalResult(400, respmsg, null);
    }

    public static GlobalResult errorRealm(String respmsg) {
        return new GlobalResult(401, respmsg, null);
    }

    public static GlobalResult errorMsg(String respmsg) {
        return new GlobalResult(500,respmsg, null);
    }

    public static GlobalResult errorMap(Object detail) {
        return new GlobalResult(501, "error", detail);
    }

    public static GlobalResult errorTokenMsg(String respmsg) {
        return new GlobalResult(502, respmsg, null);
    }

    public static GlobalResult errorException(String respmsg) {
        return new GlobalResult(555, respmsg, null);
    }

    public GlobalResult() {

    }

    public GlobalResult(Integer respcode, String respmsg, Object detail) {
        this.respcode = respcode;
        this.respmsg = respmsg;
        this.detail = detail;
    }

    public GlobalResult(Object detail) {
        this.respcode = 200;
        this.respmsg = "交易成功";
        this.detail = detail;
    }

    public Integer getRespcode() {
        return respcode;
    }

    public void setRespcode(Integer respcode) {
        this.respcode = respcode;
    }

    public String getRespmsg() {
        return respmsg;
    }

    public void setRespmsg(String respmsg) {
        this.respmsg = respmsg;
    }

    public Object getDetail() {
        return detail;
    }

    public void setDetail(Object detail) {
        this.detail = detail;
    }
}
