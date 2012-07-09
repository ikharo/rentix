package com.rentix.utils;

import java.io.BufferedInputStream;


public class ZipTmp {
	
	private BufferedInputStream is;
	private String name;
	private String type;
	
	public ZipTmp(BufferedInputStream is,String name,String type){
		this.is = is;
		this.name = name;
	}
	
	public BufferedInputStream getIs() {
		return is;
	}
	public void setIs(BufferedInputStream is) {
		this.is = is;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
