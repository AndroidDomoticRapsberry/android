package com.andoberry.adr;

import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ScenesController {
	
	private Bitmap mImage;
	private String mName;
	private String tag;
	
	public ScenesController(Bitmap mImage, String mName, String tag) {
		this.mImage = mImage;
		this.mName = mName;
		this.tag = tag;
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
	
	public String getTag(){
		return tag;
	}
	
	public void setTg(String tag){
		this.tag = tag;
	}

}
