package com.andoberry.adr;


import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	ListView listView;

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static PlaceholderFragment newInstance(int sectionNumber) {
		PlaceholderFragment fragment = new PlaceholderFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public PlaceholderFragment() {
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		TextView textView1 = (TextView) view.findViewById(R.id.textView3);
		int position = getArguments().getInt(ARG_SECTION_NUMBER);

		textView1.setText("Editar");
		if(position == 1){
			listView = (ListView) view.findViewById(R.id.listView1);
//			Button b = (Button) view.findViewById(R.id.Button03);
			//Button b2 = (Button) view.findViewById(R.id.Button03);
			
			List<ScenesController> listScenes = new ArrayList<ScenesController>();
			listScenes.add(new ScenesController(
					BitmapFactory.decodeResource(getResources(), R.drawable.lights), "Luces", "Luces")); 
			
			listScenes.add(new ScenesController(
					BitmapFactory.decodeResource(getResources(), R.drawable.persiana), "Persianas", "Persianas")); //b.setTag("Persianas");
			listScenes.add(new ScenesController(
					BitmapFactory.decodeResource(getResources(), R.drawable.termostato), "Termostato", "Termostato"));
			listScenes.add(new ScenesController(
					BitmapFactory.decodeResource(getResources(), R.drawable.humi), "Humidificador", "Humidificador"));
			ScenesControllerAdapter adapter = new ScenesControllerAdapter(getActivity(), listScenes);
			listView.setAdapter(adapter);
			
			listView.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Toast.makeText(getActivity(), "Hola", Toast.LENGTH_SHORT).show();
				}
				
			});
		}
	}



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.fragment_fragment__tab_swipe, container,
				false);
		
		return rootView;
	}
}