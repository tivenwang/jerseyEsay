/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivenwang.memoryData;

import java.util.Date;


/**
 *
 * 验证码bean
 * @author onlan_apple
 */
public class VerificationCode {
    private String code;
    private Date createdDate;

    public VerificationCode(){
        
    }
    
    public VerificationCode(String code,Date createdDate){
        this.code = code;
        this.createdDate = createdDate;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    
}
