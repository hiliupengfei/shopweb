package cn.pengfei.ssm.po;

public class tbUsers {
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 主键 
	 */
	private String itemID;
	/**
	 * 昵称
	 */
	private String nickName ;
	/**
	 * 头像
	 */
	private String avatarUrl ;
	/**
	 * 性别
	 */
	private String gender ;
	/**
	 * 省份
	 */
	private String province ;
	/**
	 * 城市
	 */
	private String city ;
	/**
	 * 国家
	 */
	private String country ;
	/**
	 * 电话(手机号)
	 */
	private String phone ;
	private String openID;
	public String getOpenID() {
		return openID;
	}
	public void setOpenID(String openID) {
		this.openID = openID;
	}
	@Override
	public String toString() {
		return "tbUsers [itemID=" + itemID + ", nickName=" + nickName + ", avatarUrl=" + avatarUrl + ", gender="
				+ gender + ", province=" + province + ", city=" + city + ", country=" + country + ", phone=" + phone
				+ "]";
	}
	
}
