package com.self.resource;

import com.self.servlet.TokenServlet;
import com.yunhetong.sdk.LxSDKManager;
import com.yunhetong.sdk.exception.LxKeyException;
import com.yunhetong.sdk.exception.LxNonsupportException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Seanwu on 2016/12/20.
 */
public class testR {


    /**
     * 初始化 LxSDKManager
     * @return 正常的话返回 LxSDKManager ，有异常的话返回 null
     */
    public static LxSDKManager getLxSDKManager() {
        // 第三方应用的appId
        String appId = "2016071110484200002";
        // 云合同公钥地址
        InputStream yhtPublicKey = TokenServlet.class.getResourceAsStream("/tuiTest/lx_sk.pem");
        // 第三方应用的私钥地址
        InputStream appPrivateKey = TokenServlet.class.getResourceAsStream("/tuiTest/p2p_pk.pem");
        try {
            // 初始化 SDKManager
            int i =1;
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

}
