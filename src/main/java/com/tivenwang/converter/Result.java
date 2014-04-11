/*
  * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivenwang.converter;

import java.io.Serializable;

/**
 *
 * @author
 * 返回值bean
 */
public class Result implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean result;
    private int resultId;
    private String resultMSG;
    
    public Result(){
        this.result = true;
        this.resultId = 1000;
        this.resultMSG = "";
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean getResult() {
        return this.result;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public int getResultId() {
        return this.resultId;
    }

    public void setResultMSG(String msg) {
        this.resultMSG = msg;
    }

    public String getResultMSG() {
        return this.resultMSG;
    }
}
