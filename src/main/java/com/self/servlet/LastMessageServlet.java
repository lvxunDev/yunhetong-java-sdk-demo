package com.self.servlet;

import com.self.resource.R;
import com.yunhetong.sdk.LxSDKManager;
import com.yunhetong.sdk.exception.*;
import com.yunhetong.sdk.util.LxHttpUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LastMessageServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        LxSDKManager lxSDKManager = R.getLxSDKManager();

        String s = "";
        try {
            LxHttpUtil.setDebug("http://testsdk.yunhetong.com/sdk");
            s = lxSDKManager.getLastNotice();

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


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
