package com.self.servlet;

import com.yunhetong.sdk.LxSDKManager;
import com.yunhetong.sdk.bean.LxUser;
import com.yunhetong.sdk.exception.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.self.resource.R.getLxSDKManager;
import static com.self.resource.R.getUserA;
import static com.self.resource.R.getUserB;


public class TokenServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        LxSDKManager lxSDKManager = getLxSDKManager();
        String whichUser = request.getParameter("user");
        String s = "";
        LxUser user = null;
        if ("A".equals(whichUser)) {
            user = getUserA();
        } else {
            user = getUserB();
        }
        try {
            s = lxSDKManager.getToken(user);
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


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
