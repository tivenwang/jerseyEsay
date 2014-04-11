/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivenwang.servlet;

import com.tivenwang.memoryData.VerificationCodeManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author onlan_apple
 */
public class VcodeImageCreateServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(VcodeImageCreateServlet.class);
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setStatus(404);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        String imei = request.getHeader("imei");
        String heightString = request.getHeader("height");
        String widthString = request.getHeader("width");
        if (imei != null){
            VerificationCodeManager vcManager = new VerificationCodeManager();
                if (heightString != null && widthString != null){
                    int height = Integer.parseInt(heightString); 
                    int width = Integer.parseInt(widthString);
                    vcManager.height = height;
                    vcManager.width = width;
                }else{
                    vcManager.height = 22;
                    vcManager.width = 60;
                    log.error("[调用验证码接口缺少 height、width头]");
                }  
                vcManager.getRandcode(request, response, imei);       
        }else{
            response.setStatus(404);
            log.error("[调用验证码接口缺少 imei头]");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
