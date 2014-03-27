package com.tivenwang.util;

import java.io.IOException;
import java.util.Properties;


/** 
 * @author Pecan 
 * 类说明 
 */
public class CustomConfigPropertites {
	private static Properties p ;
	static{
		loadConfFromFile();
	}
    private static void loadConfFromFile(){
        p  = new Properties();
        try {
			p.load(CustomConfigPropertites.class.getResourceAsStream("/custom.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    /**
     * 
     * @param key
     */
    public static String getPropertites(String key){
    	return p.getProperty(key);
    }
}
