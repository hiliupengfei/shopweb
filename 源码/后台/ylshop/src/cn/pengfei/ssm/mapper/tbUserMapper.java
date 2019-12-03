package cn.pengfei.ssm.mapper;

import java.util.List;

import cn.pengfei.ssm.po.tbUsers;

public interface tbUserMapper {
	/**
	 * 添加用户
	 * 编写人：刘朋飞
	 * 编写时间：2019年12月3日
	 * @param user 用户实体
	 * @return 返回执行后结果
	 * @throws Exception 抛出异常
	 */
	public int insertUser(tbUsers user) throws Exception ;
	
	public List<tbUsers> searchUserByOpenId(String openId) throws Exception ;
	
	public int updateUserByOpenId(tbUsers user) throws Exception ;
}
