package com.dfcs.supermarket.main.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


/**
 * 通用响应
 *
 * @author caoxinyu
 * @date 2020/01/11
 */
@Data
@ApiModel
public class BaseResponse<T> {
	
	/**
	 *返回状态吗
	 */
    @ApiModelProperty(example = "00", value = "返回状态码")
    protected String code;
	/**
	 *返回说明
	 */
    @ApiModelProperty(example = "成功", value = "返回说明")
    protected String message;
	/**
	 *  自定义返回数据
	 */
    @ApiModelProperty(value = "自定义返回数据")
    protected T data;
	
	/**
	 * 通用返回
	 */
	private BaseResponse() {
        this.code = ResponseCode.SUCCESS.code;
        this.message = ResponseCode.SUCCESS.message;
    }
	
	/**
	 * 通用响应
	 *
	 * @param code 代码
	 * @param message 消息
	 */
	private BaseResponse(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }
	
	
	/**
	 * 基反应
	 *
	 * @param code 代码
	 * @param message 消息
	 * @param data 数据
	 */
	public BaseResponse(String code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }
	
	/**
	 * 基反应
	 *
	 * @param data 数据
	 */
	private BaseResponse(T data) {
        this.code = ResponseCode.SUCCESS.code;
        this.message = ResponseCode.SUCCESS.message;
        this.data = data;
    }
	
	/**
	 * 基反应
	 *
	 * @param code 代码
	 */
	public BaseResponse(ResponseCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }
	
	
	/**
	 * 好吧
	 *
	 * @param data 数据
	 *
	 * @return {@link BaseResponse<T>}
	 */
	public static <T> BaseResponse<T> ok(T data) {
        return new BaseResponse<>(data);
    }
	
	/**
	 * 犯错
	 *
	 * @param code 代码
	 * @param message 消息
	 *
	 * @return {@link BaseResponse<T>}
	 */
	public static <T> BaseResponse<T> err(String code, String message) {
        return new BaseResponse<>(code, message);
    }
	
	/**
	 * 犯错
	 *
	 * @param message 消息
	 *
	 * @return {@link BaseResponse<T>}
	 */
	public static <T> BaseResponse<T> err(String message) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.systemError(message);
        return baseResponse;
    }
	
	
	/**
	 * 系统错误
	 */
	public void systemError() {
        this.code = ResponseCode.SYS_ERROR.code;
        this.message = ResponseCode.SYS_ERROR.message;
    }
	
	/**
	 * 系统错误
	 *
	 * @param message 消息
	 */
	private void systemError(String message) {
        this.code = ResponseCode.SYS_ERROR.message;
        this.message = message;
    }
	
	/**
	 * 参数检查失败
	 */
	public void paramCheckFail() {
        this.code = ResponseCode.PARAM_CHECK_FAIL.code;
        this.message = ResponseCode.PARAM_CHECK_FAIL.message;
    }
	
	/**
	 * 成功
	 *
	 * @return boolean
	 */
	private boolean success() {
        return this.getCode().equals(ResponseCode.SUCCESS.code);
    }
	
	/**
	 * 失败
	 *
	 * @return boolean
	 */
	public boolean fail() {
        return !success();
    }
	
	/**
	 * 响应代码
	 *
	 * @author miaozhuang
	 * @date 2020/01/11
	 */
	@AllArgsConstructor
    @Getter
    public enum ResponseCode {
		
		/**
		 * 成功
		 */
		SUCCESS("00", "success"),
		
		/**
		 * 参数检查失败
		 */
		PARAM_CHECK_FAIL("01", "request params check fail"),
		
		/**
		 * 登录检查失败
		 */
		LOGIN_CHECK_FAIL("02", "login check fail"),
		
		/**
		 * 频繁的请求
		 */
		FREQUENT_REQUEST("03", "frequent request"),
		
		/**
		 * 商业检查失败
		 */
		BIZ_CHECK_FAIL("04", "biz check fail"),
		
		/**
		 * 系统错误
		 */
		SYS_ERROR("99", "system error");
		
		/**
		 * 代码
		 */
		@ApiModelProperty(example = "00", value = "返回码数据")
        private String code;
		
		/**
		 * 消息
		 */
		@ApiModelProperty(example = "成功", value = "相应信息")
        private String message;
    }


}
