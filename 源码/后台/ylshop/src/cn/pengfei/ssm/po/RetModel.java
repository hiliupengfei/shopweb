package cn.pengfei.ssm.po;

public class RetModel {
	
	/**
	 * 返回码
	 */
	private String code ;
	/**
	 * 返回信息
	 */
	private String msg ;
	/**
	 * 是否成功
	 */
	private boolean isOk ;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isOk() {
		return isOk;
	}
	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}
	
}
