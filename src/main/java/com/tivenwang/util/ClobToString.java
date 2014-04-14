/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivenwang.util;

import java.io.Reader;
import java.sql.Clob;

/**
 * 
 * @author Pecan 
 * 类说明：
 */
public class ClobToString {
    public static String clobToString(Clob clob) {
        if (clob == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer(65535);// 64K  
        Reader clobStream = null;
        try {
            clobStream = clob.getCharacterStream();
            char[] b = new char[60000];// 每次获取60K  
            int i = 0;
            while ((i = clobStream.read(b)) != -1) {
                sb.append(b, 0, i);
            }
        } catch (Exception ex) {
            sb = null;
        } finally {
            try {
                if (clobStream != null) {
                    clobStream.close();
                }
            } catch (Exception e) {
            }
        }
        if (sb == null) {
            return null;
        } else {
            return sb.toString();
        }
    }    
}
