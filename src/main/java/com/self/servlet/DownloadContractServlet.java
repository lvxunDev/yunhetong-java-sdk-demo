package com.self.servlet;

import com.yunhetong.sdk.LxSDKManager;
import com.yunhetong.sdk.exception.*;
import com.yunhetong.sdk.util.LxIOUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.self.resource.R.getLxSDKManager;


public class DownloadContractServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        LxSDKManager lxSDKManager = getLxSDKManager();
        Long contractId = Long.valueOf(request.getParameter("contractId"));
        try {
            Map<String, Object> stringObjectMap = lxSDKManager.downloadContract(contractId);
            /* 如果成功的话返回一个下载流 */
            Object body = stringObjectMap.get("body");
            byte[] bytes = LxIOUtils.objToByteArray(body);
            if (Boolean.valueOf(stringObjectMap.get("success").toString())) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/octet-stream");
                response.setHeader("Accept-Ranges", "bytes");
                response.setHeader("Accept-Length", String.valueOf(bytes.length));
                response.setHeader("Content-Disposition", "attachment; filename=" + contractId + ".zip");
            } else {    // 如果不成功的话直接返回 body 里的错误信息
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
            }
            try {
                ServletOutputStream os = response.getOutputStream();
                os.write(bytes);
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
