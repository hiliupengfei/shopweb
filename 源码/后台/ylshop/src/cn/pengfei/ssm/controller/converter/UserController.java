package cn.pengfei.ssm.controller.converter;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;

import cn.pengfei.ssm.po.tbUsers;
import cn.pengfei.ssm.service.UserService;
import cn.pengfei.ssm.util.AesCbcUtil;
import cn.pengfei.ssm.util.Util;


@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService ;

	
	@RequestMapping(value={"/insertUserPost"} ,method=RequestMethod.POST)
	public void insertUser(HttpServletRequest req, HttpServletResponse res) throws Exception{
		System.out.println("进入添加用户的方法");
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		//Map<String, String> map = weixinService.parseRequest(req.getInputStream());
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = req.getReader();
		String line =reader.readLine();
		reader.close();
		PrintWriter out = res.getWriter();
		String retJson = "";
		tbUsers user = new tbUsers ();
		JSONObject obj = new JSONObject();
		obj = obj.parseObject(line);
		user.setAvatarUrl(obj.getString("avatarUrl"));
		user.setCity(obj.getString("city"));
		user.setCountry(obj.getString("country"));
		user.setGender(obj.getString("gender"));
		user.setNickName(obj.getString("nickName"));
		user.setProvince(obj.getString("province"));
		System.out.println(user);
		//用户登录
		retJson = userService.insertUsers(user);
		System.out.println(retJson);
		out.write(retJson);
		out.flush();
		out.close();
	}
	/**
     * decoding encrypted data to get openid
     *
     * @param iv
     * @param encryptedData
     * @param code
     * @return
     */
    @RequestMapping(value = "/decodeUserInfo", method = RequestMethod.GET)
    private String decodeUserInfo(String iv, String encryptedData, String code) {
        Map map = new HashMap();
        // login code can not be null
        if (code == null || code.length() == 0) {
            map.put("status", 0);
            map.put("msg", "code 不能为空");
            
            return JSONObject.toJSONString(map);
        }
        // mini-Program's AppID
        String wechatAppId = "wx0d944d9ca0c7c2c7";

        // mini-Program's session-key
        String wechatSecretKey = "a5037129258ab945dd372588cc5db8ca";

        String grantType = "authorization_code";

        // using login code to get sessionId and openId
        String params = "appid=" + wechatAppId + "&secret=" + wechatSecretKey + "&js_code=" + code + "&grant_type=" + grantType;

        // sending request
        String sr = Util.doPost("https://api.weixin.qq.com/sns/jscode2session", params);
        System.out.println(sr);
        // analysis request content
        JSONObject json = JSONObject.parseObject(sr);

        // getting session_key
        String sessionKey = json.get("session_key").toString();

        // getting open_id
        String openId = json.get("openid").toString();

        // decoding encrypted info with AES
        try {
            String result = AesCbcUtil.decrypt(encryptedData, sessionKey, iv, "UTF-8");
            if (null != result && result.length() > 0) {
                map.put("status", 1);
                map.put("msg", "解密成功");

                JSONObject userInfoJSON = JSONObject.parseObject(result);
                Map userInfo = new HashMap();
                userInfo.put("openId", userInfoJSON.get("openId"));
                userInfo.put("nickName", userInfoJSON.get("nickName"));
                userInfo.put("gender", userInfoJSON.get("gender"));
                userInfo.put("city", userInfoJSON.get("city"));
                userInfo.put("province", userInfoJSON.get("province"));
                userInfo.put("country", userInfoJSON.get("country"));
                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
                userInfo.put("unionId", userInfoJSON.get("unionId"));
                map.put("userInfo", userInfo);
                System.out.println(JSONObject.toJSONString(map));
                return JSONObject.toJSONString(map);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("status", 0);
        map.put("msg", "解密失败");
        
        return JSONObject.toJSONString(map);
    }
}
