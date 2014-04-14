/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivenwang.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 *
 * @author onlan_apple
 */
public class CheckUtil {
    
    private static final int subStringLength = 30;
    public static final String DateTimeFormatPattern = "yyyy-MM-dd HH:mm:ss";
    public static final String DateTimeFormatPattern1 = "yyyy-MM-dd HH:mm";
    public static final String DateTimeFormatPatternForLog = "yyyy-MM-dd HH:mm:ss-SS";
    public static final String DateFormatPattern = "yyyy-MM";
    public static final String DateMonthDayPattern = "yyyy-MM-dd";
    public static final String DateTimeFormatPattern2 = "yyyyMMddHHmmss";
    //Feiran Add
    public static final String DateTimePatternNews = "MM-dd HH:mm";

    private static final String EmailFormatPattern = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    //中文及中文标点的正则表达式
    private static final String StringSizeInDatabasePattern = "[\u2E80-\u9fa5（）〈 〉「」〔〕！？。，《》【】“”·、：；‘’……－——＿＿﹏﹏￥]{1}";
    //非法字符检测（只能输入英文、汉字和数字）
    private static final String CheckUserNamePattern1 = "[\\w\u4E00-\u9FA5]{1}";
    //中文字数统计
    private static final String CheckUserNamePattern2= "[\u4E00-\u9FA5]{1}";
    
    //log日志文件正则表达式
    private static final String logPattern = "\\[INFO\\]\\s\\[\\[#(.*)\\]\\]";
    //	手机号正则
    private static final String phonePattern = "^(1[3-8][0-9]{9})$";
    //数字正则
    private static final String numPattern = "^([0-9]{13,})$";
    
    private static final String numPattern9 = "^([0-9]{9,})$";
    
    //数字正则
    private static final String numPattern2 = "^[0-9]+$";
    //只含英文和数字
    private static final String numLetterPattern = "^[A-Za-z0-9]+$";
    //只含英文
    private static final String letterPattern = "^[A-Za-z]+$";        
    /**
     * 检测用户名是否合法之一
     * 只有英文、汉字和数字为合法字符
     * @param content
     * @return 是否合法
     */
    public static boolean CheckUserNameIsLllegal(String content){
        
        int count=0;
        final Pattern patternn = Pattern.compile(CheckUserNamePattern1);
        final Matcher mat = patternn.matcher(content);
        while (mat.find()){
            count++;
        }
        if (content.length() == count){
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean checkFmiId(String fmi){
    	if (null==fmi||fmi.length()<12||!fmi.startsWith("FM")) {//基本检查
			return false;
		}else {
			String sub=fmi.substring(2, 11);
			if (!numFormat9(sub)) {//中间9为是否为数字
				return false;
			}else {
				int count = 0;
				char[] ca = sub.toCharArray(); 
				for(char c:ca)	{
					if(c%2 != 0){
						count++;
					}
				}
				if (fmi.substring(11, 12).equals(new Integer(count).toString())) {//检验位检查
					return true;
				}else {
					return false;
				}
			}
		}
    }
    
    /**
     * 检测用户名是否合法之二
     * 字符长度是否超过在4-12位之间（中文在上证报数据库中占2位）
     * @param content
     * @return 是否合法 
     */
    public static boolean CheckUserNameSize(String content){
        int count=0;
        final Pattern patternn = Pattern.compile(CheckUserNamePattern2);
        final Matcher mat = patternn.matcher(content);
        while (mat.find()){
            count++;
        }
        int total = content.length() - count + count * 2;
        if (total >= 4 && total <= 12){
            return true;
        } else {
            return false;
        }
    }

    /**
     * 检测用户名是否都是中文
     * 字符长度是否超过在4-12位之间（中文在上证报数据库中占2位）
     * @param content
     * @return 是否合法
     */
    public static boolean CheckUserNameZh(String content){
    	int count=0;
    	final Pattern patternn = Pattern.compile(CheckUserNamePattern2);
    	final Matcher mat = patternn.matcher(content);
    	while (mat.find()){
    		count++;
    	}
    	if (count==content.length()) {
			return true;
		}else {
			return false;
		}
    }
    
    /** 
     * 统计中文汉字，中文标点，英文及英文标点，数字 在 oracle utf-8编码下占用的位数。
     * 其中中文汉字与中文标点占用3位，其他都为占用一位
     * @param content 
     * @return 占用的位数 
     */
    public static int CountSizeInDatabase(String content){
        
        final Matcher matcher = Pattern.compile(StringSizeInDatabasePattern).matcher(content);
        int count = 0;//汉字和中文标点的个数
        int cc = 0;//字符串占的总位数，一个汉字或者中文标点占用3位，英文字符，数字，英文标点占一位，（在oracle,utf-8编码中）
        while (matcher.find())
        {
            count++;
        }
        cc = content.length() - count + count * 3;
        return cc;

    }
    
    /** 
     * 验证输入的邮箱格式是否符合 
     * @param email 
     * @return 是否合法 
     */  
    public static boolean emailFormat(String email) {  
        
        boolean tag = true;
        final Pattern patternn = Pattern.compile(EmailFormatPattern);  
        final Matcher mat = patternn.matcher(email);  
        if (!mat.find()) {  
            tag = false;  
        }  
        return tag;  
    }
    /** 
     * 验证输入的手机号码是否符合 
     * @param email 
     * @return 是否合法 
     */  
    public static boolean phoneFormat(String num) {  
    	
    	boolean tag = true;
    	final Pattern patternn = Pattern.compile(phonePattern);  
    	final Matcher mat = patternn.matcher(num);  
    	if (!mat.find()) {  
    		tag = false;  
    	}  
    	return tag;  
    }
    /** 
     * 验证输入是否全是数字 
     * @param email 
     * @return 是否合法 
     */  
    public static boolean numFormat(String num) {  
    	
    	boolean tag = true;
    	final Pattern patternn = Pattern.compile(numPattern);  
    	final Matcher mat = patternn.matcher(num);  
    	if (!mat.find()) {  
    		tag = false;  
    	}  
    	return tag;  
    }
    
    /** 
     * 长度9
     * 验证输入是否全是数字 
     * @param email 
     * @return 是否合法 
     */  
    public static boolean numFormat9(String num) {  
    	
    	boolean tag = true;
    	final Pattern patternn = Pattern.compile(numPattern9);  
    	final Matcher mat = patternn.matcher(num);  
    	if (!mat.find()) {  
    		tag = false;  
    	}  
    	return tag;  
    }
    
    /**
     * 隐藏手机号
     * @param phone
     * @return
     */
    public static String hidePhone(String phone){
        if (phone.length() >= 11){
        	StringBuffer stringBuffer=new StringBuffer(phone);
        	return stringBuffer.replace(3, 7, "****").toString();
        }else{
            return phone;
        }
    }

    /**
     * 隐藏银行卡号
     * @param phone
     * @return
     */
    public static String hideBankCard(String bankCard){
    	if (bankCard.length() >= 11){
    		StringBuffer stringBuffer=new StringBuffer(bankCard);
    		return stringBuffer.replace(0, bankCard.length()-5, "****** **** **** ").toString();
    	}else{
    		return bankCard;
    	}
    }

    /**
     * 隐藏支付宝账号
     * @param phone
     * @return
     */
    public static String hideAlipayCard(String alipayCard){
    	int index=alipayCard.indexOf("@");
    	if (alipayCard.length() >= 11){
    		StringBuffer stringBuffer=new StringBuffer(alipayCard);
    		return stringBuffer.replace(3, index, "******").toString();
    	}else{
    		return alipayCard;
    	}
    }
    /**
     * 按格式提取日志文件
     * @param log
     * @return 
     */
    public static String logParse(String log){
        
    	final Pattern pattern = Pattern.compile(logPattern);
    	final Matcher match = pattern.matcher(log);
        if(match.find()){
            return match.group(1);
        }else{
            return null;
        }
    }
    
    /**
     * 按照默认的长度30截断字符
     * @param string
     * @return
     */
    public static String rightString(String string){
        if (string == null || string.equals("")){
            return string;
        }else{
            if (string.length() > subStringLength){
                return string.substring(0,subStringLength);
            }else{
                return string;
            }
        }
    }
    
    /**
     * 按照给定的长度截断字符
     * @param string
     * @param length
     * @return
     */
    public static String rightString(String string, int length){
        if (string == null || string.equals("")){
            return string;
        }else{
            if (string.length() > length){
                return string.substring(0,length);
            }else{
                return string;
            }
        }
    }
    
    /**
     * 转换为yyyy-MM-dd HH:mm:ss格式的string
     * @param date
     * @return 
     */
    public static String DateToString(Date date){
        SimpleDateFormat df = new SimpleDateFormat(DateTimeFormatPattern);
        String dateString ;
        dateString = df.format(date);
        return dateString;
    }
    /**
     * 转换为yyyy-MM-dd HH:mm:ss-SS格式的string
     * @param date
     * @return
     */
    public static String DateToStringForLog(Date date){
    	SimpleDateFormat df = new SimpleDateFormat(DateTimeFormatPatternForLog);
    	String dateString ;
    	dateString = df.format(date);
    	return dateString;
    } 
    
    /**
     * 转换为yyyy-MM-dd 格式的string
     * @param date
     * @return
     */
    public static String DateToStringByDay(Date date){
        SimpleDateFormat df = new SimpleDateFormat(DateMonthDayPattern);
        String dateString ;
        dateString = df.format(date);
        return dateString;
    } 
    
    /**
     * 转换为yyyy-MM-dd 格式的date 时分秒变0
     * @param date
     * @return
     */
    public static Date DateToDateByDay(Date date) throws ParseException{
        SimpleDateFormat df = new SimpleDateFormat(DateMonthDayPattern);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dayStr = df.format(date); 
        date = sdf.parse(dayStr);    
                
        return date;
    }     
    
    /**
     * 将圆为单位转换为以分为单位
     * @return 
     */
    public static String yuanToFeng(double price){
        double p = price * 100;
        return (long)p+"";
    }
    
    /**
     * 将分为单位转换为以圆为单位
     * @return 
     */
    public static double fengToYuan(String price){
        double p = Double.parseDouble(price);
        return p/100;
    }
    
//    public static void main(String[] args) throws ParseException{
//        System.out.print(StringToDate3("20130306121314"));
//    }
    /**
     * 将时间格式转为 20130306121314
     * @return 
     */
    public static String DateToString4(Date date){
        SimpleDateFormat df = new SimpleDateFormat(DateTimeFormatPattern2);
        String dateString ;
        dateString = df.format(date);
        return dateString;
    }
    
    /**
     * 将20130306121314 转换为时间格式 
     * @return 
     */
    public static Date StringToDate3(String str) throws ParseException{
        SimpleDateFormat df = new SimpleDateFormat(DateTimeFormatPattern2);
        Date dateString ;
        dateString = df.parse(str);
        return dateString;
    }
    /**
     * yyyy-MM-dd HH:mm:ss 格式转换为date
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date StringToDate(String str) throws ParseException{
    	SimpleDateFormat df = new SimpleDateFormat(DateTimeFormatPattern);
    	Date dateString ;
    	dateString = df.parse(str);
    	return dateString;
    }
    
    /**
     * yyyy-MM-dd 格式转换为date
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date StringToDateByDay(String str) throws ParseException{
    	SimpleDateFormat df = new SimpleDateFormat(DateMonthDayPattern);
    	Date dateString ;
    	dateString = df.parse(str);
    	return dateString;
    }
    
    /**
     *yyyy-MM-dd HH:mm 格式转换为date 
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date StringToDate1(String str) throws ParseException{
    	SimpleDateFormat df = new SimpleDateFormat(DateTimeFormatPattern1);
    	Date dateString ;
    	dateString = df.parse(str);
    	return dateString;
    }
    
    /**
     * yyyy-MM-dd HH:mm 格式转换为String 
     * @return 
     */
    public static String DateToString1(Date date){
        SimpleDateFormat df = new SimpleDateFormat(DateTimeFormatPattern1);
        String dateString ;
        dateString = df.format(date);
        return dateString;
    }    
    
    public static String dateToMd5String(Date date){
    	String str=CheckUtil.DateToStringByDay(date);
    	MessageDigest messageDigest = null;  
        try {  
            messageDigest = MessageDigest.getInstance("MD5");  
            messageDigest.reset();  
            messageDigest.update(str.getBytes("UTF-8"));  
        } catch (NoSuchAlgorithmException e) {  
            System.out.println("NoSuchAlgorithmException caught!");  
            return String.valueOf(System.currentTimeMillis());
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
            return String.valueOf(System.currentTimeMillis());
        }  
        byte[] byteArray = messageDigest.digest();  
        StringBuffer md5StrBuff = new StringBuffer();  
        for (int i = 0; i < byteArray.length; i++) {              
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));  
            else  
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
        }  
        return md5StrBuff.toString();  
    }
    
    /**
    * 比较两个日期之间的大小
    *
    * @param d1
    * @param d2
    * @return 前者大于后者返回true 反之false
    */
    public static boolean compareDate(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        int result = c1.compareTo(c2);
        if (result >= 0)
            return true;
        else
            return false;
    }
    
    /**
     * 校验银行卡卡号
     * @param cardId
     * @return
     */
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        return cardId.charAt(cardId.length() - 1) == bit;        
    }
    
    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     * @param nonCheckCodeCardId  银行卡号的末尾号去掉
     * @return 校验后的末尾号
     * 
     */
    private static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if(nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            throw new IllegalArgumentException("Bank card code must be number!");
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if(j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;            
        }
        return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');
    }

    /**
     * 检测只含数字
     * @param content
     * @return 是否合法
     */
    public static boolean CheckNum(String content){
        
    	boolean tag = false;
    	final Pattern patternn = Pattern.compile(numPattern2);  
    	final Matcher mat = patternn.matcher(content);  
    	if (mat.find()) {  
    		tag = true;  
    	}  
    	return tag;  
    }
    
    /**
     * 只有英文和数字为合法字符
     * @param content
     * @return 是否合法
     */
    public static boolean CheckNumLetter(String content){                
    	boolean tag = false;
    	final Pattern patternn = Pattern.compile(numLetterPattern);  
    	final Matcher mat = patternn.matcher(content);  
    	if (mat.find()) {  
    		tag = true;  
    	}  
    	return tag;         
    }    
    
    /**
     * 只有英文字符
     * @param content
     * @return 是否合法
     */
    public static boolean CheckLetter(String content){                
    	boolean tag = false;
    	final Pattern patternn = Pattern.compile(letterPattern);  
    	final Matcher mat = patternn.matcher(content);  
    	if (mat.find()) {  
    		tag = true;  
    	}  
    	return tag;         
    }       
    
    /**
     * 得到本月的第一天 yyyy-MM-dd格式
     * @param date
     * @return
     */
    public static Calendar FirstDayOfMonth() throws ParseException{
        Calendar firstDate = Calendar.getInstance();
        firstDate.set(Calendar.DATE,1);
        return firstDate;
    }       
    
    /*
    public static void main(String args[]){
    	try {
			System.out.println(CheckUtil.compareDate(CheckUtil.StringToDate("2013-11-12  23:00:00"), CheckUtil.StringToDate("2013-11-12 3:00:00")));
			System.out.println(CheckUtil.hideAlipayCard("wangtianzhen@foxmail.com"));
			System.out.println(CheckUtil.hideBankCard("6222600110054323008"));
			System.out.println(CheckUtil.numFormat("62226001100543，23008"));
			System.out.println(CheckUtil.CheckUserNameZh("tian"));
			System.out.println(CheckUtil.checkFmiId("FM1014030814"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    * */
}
