package com.dfcs.supermarket.main.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


@Data
@ApiModel
public class BaseResponse<T> {

    // 返回状态吗
    @ApiModelProperty(example = "00", value = "返回状态码")
    protected String code;
    // 返回说明
    @ApiModelProperty(example = "成功", value = "返回说明")
    protected String message;
    // 自定义返回数据
    @ApiModelProperty(value = "自定义返回数据")
    protected T data;

    public BaseResponse() {
        this.code = ResponseCode.SUCCESS.code;
        this.message = ResponseCode.SUCCESS.message;
    }

    public BaseResponse(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }


    public BaseResponse(String code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseResponse(T data) {
        this.code = ResponseCode.SUCCESS.code;
        this.message = ResponseCode.SUCCESS.message;
        this.data = data;
    }

    public BaseResponse(ResponseCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }


    public static <T> BaseResponse<T> ok(T data) {
        return new BaseResponse<>(data);
    }

    public static <T> BaseResponse<T> err(String code, String message) {
        return new BaseResponse<>(code, message);
    }

    public static <T> BaseResponse<T> err(String message) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.systemError(message);
        return baseResponse;
    }


    public void systemError() {
        this.code = ResponseCode.SYS_ERROR.code;
        this.message = ResponseCode.SYS_ERROR.message;
    }

    public void systemError(String message) {
        this.code = ResponseCode.SYS_ERROR.message;
        this.message = message;
    }

    public void paramCheckFail() {
        this.code = ResponseCode.PARAM_CHECK_FAIL.code;
        this.message = ResponseCode.PARAM_CHECK_FAIL.message;
    }

    public boolean success() {
        return this.getCode().equals(ResponseCode.SUCCESS.code);
    }

    public boolean fail() {
        return !success();
    }

    @AllArgsConstructor
    @Getter
    public enum ResponseCode {

        SUCCESS("00", "success"),

        PARAM_CHECK_FAIL("01", "request params check fail"),

        LOGIN_CHECK_FAIL("02", "login check fail"),

        FREQUENT_REQUEST("03", "frequent request"),

        BIZ_CHECK_FAIL("04", "biz check fail"),

        SYS_ERROR("99", "system error");

        @ApiModelProperty(example = "00", value = "返回码数据")
        private String code;

        @ApiModelProperty(example = "成功", value = "相应信息")
        private String message;
    }


}
