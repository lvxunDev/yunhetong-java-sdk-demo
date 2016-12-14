package com.self.servlet;

import com.self.R;
import com.yunhetong.sdk.LxSDKManager;
import com.yunhetong.sdk.bean.LxUser;
import com.yunhetong.sdk.exception.*;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


public class TokenServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        // 第三方应用的appId
        String appId = "";
        // 云合同公钥地址
        File yhtPublicKey = new File("/path/to/yhtPublicKey.pem");
        // 第三方应用的私钥地址
        File appPrivateKey = new File("/path/to/private.pem");

        LxSDKManager lxSDKManager = null;
        try {
            // 初始化 SDKManager
            lxSDKManager = new LxSDKManager(appId, yhtPublicKey, appPrivateKey);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LxKeyException e) {
            e.printStackTrace();
        } catch (LxNonsupportException e) {
            e.printStackTrace();
        }

        String s = "";
        try {
            s = lxSDKManager.syncGetToken(getUserA());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (LxEncryptException e) {
            e.printStackTrace();
        } catch (LxKeyException e) {
            e.printStackTrace();
        } catch (LxNonsupportException e) {
            e.printStackTrace();
        } catch (LxDecryptException e) {
            e.printStackTrace();
        } catch (LxSignatureException e) {
            e.printStackTrace();
        } catch (LxVerifyException e) {
            e.printStackTrace();
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        try {
            response.getWriter().write(s.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private LxUser getUserA() {
        LxUser lxUser = new LxUser();
        lxUser.setAppUserId("your app Id")                    // 设置 appID
                .setCertifyNumber("123")                      // 设置证件号码
                .setUserType(LxUser.UserType.USER)            // 设置用户类型
                .setPhone("12311111111")                      // 设置手机号码
                .setUserName("Test")                          // 设置用户名
                .setCertifyType(LxUser.CertifyType.ID_CARD)   // 设置实名认证类型
        ;
        return lxUser;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
