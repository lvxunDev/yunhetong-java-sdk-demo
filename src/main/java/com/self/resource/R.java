package com.self.resource;

import com.self.servlet.TokenServlet;
import com.yunhetong.sdk.LxSDKManager;
import com.yunhetong.sdk.bean.LxContract;
import com.yunhetong.sdk.bean.LxContractActor;
import com.yunhetong.sdk.bean.LxUser;
import com.yunhetong.sdk.exception.LxKeyException;
import com.yunhetong.sdk.exception.LxNonsupportException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by Seanwu on 2016/12/20.
 */
public class R {


    /**
     * 初始化 LxSDKManager
     * @return 正常的话返回 LxSDKManager ，有异常的话返回 null
     */
    public static LxSDKManager getLxSDKManager() {
        // 第三方应用的appId
        String appId = "2016121514373700002";
        // 云合同公钥地址
        InputStream yhtPublicKey = TokenServlet.class.getResourceAsStream("/key/yhtSK.pem");
        // 第三方应用的私钥地址
        InputStream appPrivateKey = TokenServlet.class.getResourceAsStream("/key/rsa_private_key_pkcs8.pem");
        try {
            // 初始化 SDKManager
            return new LxSDKManager(appId, yhtPublicKey, appPrivateKey);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LxKeyException e) {
            e.printStackTrace();
        } catch (LxNonsupportException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成测试用 userA
     *
     * @return 返回一个 userA
     */
    public static LxUser getUserA() {
        LxUser lxUser = new LxUser();
        lxUser.setAppUserId("javaTestUserAAA")            // 设置用户id
                .setCertifyNumber("123")                      // 设置证件号码
                .setUserType(LxUser.UserType.COMPANY)            // 设置用户类型
                .setPhone("15267131111")                      // 设置手机号码
                .setUserName("测试甲有限公司")                          // 设置用户名
                .setCreateSignature("1")
                .setCertifyType(LxUser.CertifyType.ID_CARD)   // 设置实名认证类型
        ;
        return lxUser;
    }
    /**
     * 生成测试用 userA
     *
     * @return 返回用户 B
     */
    public static LxUser getUserB() {
        LxUser lxUser = new LxUser();
        lxUser.setAppUserId("javaTestUserBBB")                // 设置用户Id
                .setCertifyNumber("123")                      // 设置证件号码
                .setUserType(LxUser.UserType.USER)            // 设置用户类型
                .setPhone("15267132222")                      // 设置手机号码
                .setUserName("测试乙")                          // 设置用户名
                .setCreateSignature("0")
                .setCertifyType(LxUser.CertifyType.ID_CARD)   // 设置实名认证类型
        ;
        return lxUser;
    }

    /**
     * 创建合同参与方
     * @return 返回合同参与方
     */
    public static LxContractActor[] getActor(){
        LxContractActor actorA = new LxContractActor();
        actorA.setUser(getUserA());
        actorA.setAutoSign(false);
        actorA.setLocationName("signA");

        LxContractActor actorB = new LxContractActor();
        actorB.setUser(getUserB());
        actorB.setAutoSign(false);
        actorB.setLocationName("signB");
        return new LxContractActor[]{actorA, actorB};
    }

    /**
     * 创建测试合同
     * @return 测试合同
     */
    public static LxContract getTestContract() {
        LxContract lxContract = new LxContract();
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("${nameA}", "nameA");
        lxContract.setDefContractNo("随便写")           // 设置自定义合同编号
                .setTemplateId("123456")                // 设置合同模板 Id
                .setTitle("测试合同标题")                          // 设置合同标题
                .setParams(params)                          // 这是模板占位符
        ;
        return lxContract;
    }

}
