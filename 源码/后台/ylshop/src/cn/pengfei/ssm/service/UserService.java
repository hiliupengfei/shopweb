package cn.pengfei.ssm.service;

import java.util.List;

import cn.pengfei.ssm.po.tbUsers;

public interface UserService {
	/**
	 * 添加用户
	 * 编写人：刘朋飞
	 * 编写时间：2019年12月3日
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String insertUsers(tbUsers user) throws Exception ;
	
	public List<tbUsers> searchUserByOpenId(String openId) throws Exception ;
	
	public String updateUserByOpenId(tbUsers user) throws Exception ;
}
