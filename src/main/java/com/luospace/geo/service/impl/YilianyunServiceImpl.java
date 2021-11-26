package com.luospace.geo.service.impl;
import com.alibaba.fastjson.JSONObject;
import com.luospace.geo.common.Constant;
import com.luospace.geo.service.YilianyunService;
import com.luospace.geo.util.RedisUtil;
import com.yly.java.yly_sdk.RequestMethod;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;


@Service
public class YilianyunServiceImpl implements YilianyunService {

    @Resource
    RedisUtil redisUtil;

    @Override
    public void init() {
        RequestMethod.getInstance().init("1078843901","8a98cc01351f027ca163973f8c5faefb");
    }

    @Override
    public String getOpenAccessToken() throws Exception {
        if(redisUtil.get(Constant.REDIS_YILIANYUN_ACCESS_TOKEN_PREFIX) != null){
            return redisUtil.get(Constant.REDIS_YILIANYUN_ACCESS_TOKEN_PREFIX).toString();
        } else {
            String result = RequestMethod.getInstance().getAccessToken();
            JSONObject jsonObj = JSONObject.parseObject(result);
            JSONObject body = jsonObj.getJSONObject("body");
            String accessToken = body.getString("access_token");
            Long expiresIn = body.getLong("expires_in");
            redisUtil.set(Constant.REDIS_YILIANYUN_ACCESS_TOKEN_PREFIX,accessToken,expiresIn);
            return accessToken;
        }
    }

    @Override
    public void addPrinter(String machineCode,String msign,String openAccessToken) throws Exception {
        RequestMethod.getInstance().addPrinter(machineCode,msign,openAccessToken);
    }

    @Override
    public void printIndex() throws Exception {
        init();
        String openAccessToken = getOpenAccessToken();
        String machineCode = "4004627624";
        String msign = "904627556582";
        String originId = "1222424424242";
        String content = "";
        content += "<FS>好多多联盟商城</FS>\n";
        content += "<FH>";
        content += "8A-07客户-8号新唐县\n";
        content += "店铺：水盛五金\n";
        content += "件数：4件\n";
        content += "</FH>\n";
        content += "<QR>http://doc2.10ss.net/391314</QR>\n";
        content += "<FH>";
        content += "订单号：12324379379535\n";
        content += "订单日期：2021-11-22 17：22：22\n";
        content += "供应商：测试供应商\n";
        content += "用户地址：中庚环球创意中心\n";
        content += "用户店铺：测试店铺\n";
        content += "</FH>";
        addPrinter(machineCode,msign,openAccessToken);
        RequestMethod.getInstance().printIndex(openAccessToken,machineCode,content,originId);
    }
}
