package cn.pengfei.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import cn.pengfei.ssm.mapper.tbUserMapper;
import cn.pengfei.ssm.po.RetModel;
import cn.pengfei.ssm.po.tbUsers;
import cn.pengfei.ssm.service.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	tbUserMapper userMapper ;
	@Override
	public String insertUsers(tbUsers user) throws Exception {
		String retJson = "";
		RetModel rm = null ;
		int ret = userMapper.insertUser(user);
		if(ret > 0 ){
			rm = new RetModel();
			rm.setCode("000000");
			rm.setMsg("用户添加成功 ");
			rm.setOk(true);
		}
		else{
			rm = new RetModel();
			rm.setCode("000001");
			rm.setMsg("用户添加失败 ");
			rm.setOk(false);
		}
		JSONObject obj = new JSONObject();
		retJson = obj.toJSON(rm).toString();
		return retJson ;
	}

}
