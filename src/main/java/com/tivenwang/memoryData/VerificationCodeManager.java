/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivenwang.memoryData;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * （定期任务）注册时生成的验证码 清理任务 （每晚凌晨3点执行）
 * @author onlan_apple
 */
public class VerificationCodeManager implements Runnable{
    
    private static Logger log = Logger.getLogger(VerificationCodeManager.class);
    public final static ConcurrentHashMap <String, VerificationCode> codeMap = new ConcurrentHashMap<String , VerificationCode>(10000,0.75f);
    public final static long effectiveTime = 3600000l;//验证码有效时间 一个小时；
    private final static String base = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final static int baseLength = base.length();;
    private final static int codeLength = 4;
    public final static Object lock = new Object();
    private Random random = new Random();
    
    public void run(){
        //清理过时的验证码
        //System.out.println("ClearVcode:清理验证码");
        clearInvalidCode();
    }
    
    /**
     * 
     * @return 随机生成的验证码
     */
    public static String randomCode(){
        Random random = new Random();
        String randomCode = "";
        for (int i = 0;i < codeLength;i++){
            randomCode += String.valueOf(base.charAt(random.nextInt(baseLength-1)));
        }
        return randomCode;
    }
    
    public static void delCodeMap(String imei){
        synchronized(lock){
            codeMap.remove(imei);
        }
    }
    
    public static void putCodeMap(String imei,VerificationCode vc){
        synchronized(lock){
            codeMap.put(imei, vc);
        }
    }
    /**
     * 清除无效的验证码
     */
    public static void clearInvalidCode(){
        log.fatal("[每日任务：验证码清理]");
        log.fatal("BeginClear:开始清理");
        //System.out.println("BeginClear:开始清理");
        VerificationCode codeBean = null;
        long now = System.currentTimeMillis();
        synchronized(lock){
            Iterator<Entry<String, VerificationCode>> e = codeMap.entrySet().iterator();
            while (e.hasNext()){
            	Entry<String, VerificationCode> entry = e.next();
                codeBean = (VerificationCode)entry.getValue();
                if (now - codeBean.getCreatedDate().getTime() >= effectiveTime){
                    //System.out.println("remove   "+"key:"+entry.getKey()+"  value:"+codeBean.getCode()+";"+codeBean.getCreatedDate().getTime());
                    e.remove();
                }
            }
        }
        log.fatal("FinishedClear:清理结束");
        //System.out.println("FinishedClear:清理结束");
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    public int width = 60;//图片宽
    public int height = 22;//图片高
    private int lineSize = 30;//干扰线数量
    private int stringNum = 4;//随机产生字符数量
    /*
     * 获得字体
     */
    private Font getFont(){
        return new Font("Fixedsys",Font.CENTER_BASELINE,18);
    }
    /*
     * 获得颜色
     */
    private Color getRandColor(int fc,int bc){
        if(fc > 255)
            fc = 255;
        if(bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc-fc-16);
        int g = fc + random.nextInt(bc-fc-14);
        int b = fc + random.nextInt(bc-fc-18);
        return new Color(r,g,b);
    }
    /**
     * 生成随机图片
     */
    public void getRandcode(HttpServletRequest request,
            HttpServletResponse response,String imei) {
        //HttpSession session = request.getSession();
        //BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();//产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman",Font.ROMAN_BASELINE,18));
        g.setColor(getRandColor(110, 133));
        //绘制干扰线
        for(int i=0;i<=lineSize;i++){
            drowLine(g);
        }
        //绘制随机字符
        String randomString = "";
        for(int i=1;i<=stringNum;i++){
            randomString=drowString(g,randomString,i);
        }
        //session.removeAttribute(RANDOMCODEKEY);
        //session.setAttribute(RANDOMCODEKEY, randomString);
        VerificationCodeManager.putCodeMap(imei, new VerificationCode(randomString, new Date()));
        //System.out.println("new post! ");
        //System.out.println("imei:"+imei+",  randomString:"+randomString);
        //System.out.println();
        ////
        /*
        Iterator k = codeMap.entrySet().iterator();
        VerificationCode codeBean;
        String imeiii;
        while (k.hasNext()){
            Map.Entry entry = (Map.Entry)k.next();
            codeBean = (VerificationCode)entry.getValue();
            imeiii = entry.getKey().toString();
            System.out.println("imei:"+imeiii+",  randomString:"+codeBean.getCode()+",  createdTime:"+codeBean.getCreatedDate());
        }
        * */
        
        /////
        g.dispose();
        try {
            ImageIO.write(image, "JPEG", response.getOutputStream());//将内存中的图片通过流动形式输出到客户端
        } catch (Exception e) {
            log.error("验证码生成异常，imei:"+imei+" ,width:"+this.width+" ,height:"+this.height+"  ,异常原因:"+e);
        }
    }
    /*
     * 绘制字符串
     */
    private String drowString(Graphics g,String randomString,int i){
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101),random.nextInt(111),random.nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(base.length())));
        randomString +=rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        double jjj = width*0.8*0.25;
        int jjjj = (int) jjj;
        double lll = height*0.65;
        int llll = (int)lll;
        g.drawString(rand, jjjj*(i-1),llll);
        return randomString;
    }
    /*
     * 绘制干扰线
     */
    private void drowLine(Graphics g){
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x+xl, y+yl);
    }
    /*
     * 获取随机的字符
     */
    public String getRandomString(int num){
        return String.valueOf(base.charAt(num));
    }
}
