package com.andoberry.adr;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ScenesControllerAdapter extends BaseAdapter{
	private Context mContext;
	private List<ScenesController> mListScene;

	public ScenesControllerAdapter(Context context, List<ScenesController> list) {
		mContext = context;
		mListScene = list;

	}

	@Override
	public int getCount() {
		return mListScene.size();
	}

	@Override
	public Object getItem(int pos) {
		return mListScene.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}
	
	static int id = 1000000;

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		// get selected entry
		ScenesController entry = mListScene.get(pos);
		
	
		// inflating list view layout if null
		if(convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.items, null);
		}
		
		// set avatar
		ImageView ivAvatar = (ImageView)convertView.findViewById(R.id.imageView1);
		ivAvatar.setImageBitmap(entry.getmImage());

		// set name
		TextView tvName = (TextView)convertView.findViewById(R.id.TextView03);
		tvName.setText(entry.getmName());
		
		Button bt = (Button) convertView.findViewById(R.id.Button03);
		//bt.setId(id+pos);
		bt.setTag(entry.getTag());
		
		Menu_Principal p = new Menu_Principal();
		
		p.check(bt);
		
		//check c = new check(p.encendidoL, p.encendidoP, p.encendidoT, p.encendidoH, p.subLuz1, bt);
		//c.start();
				
		//bt.setTag(id+pos);
		
		return convertView;
	}

}
