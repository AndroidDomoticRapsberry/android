package com.andoberry.adr;

import android.graphics.Bitmap;

public class ScenesController {
	
	private Bitmap mImage;
	private String mName;
	
	
	
	public ScenesController(Bitmap mImage, String mName) {
		this.mImage = mImage;
		this.mName = mName;
	}
	public Bitmap getmImage() {
		return mImage;
	}
	public void setmImage(Bitmap mImage) {
		this.mImage = mImage;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	
	

}
