package com.andoberry.adr;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.graphics.BitmapFactory;

import android.support.annotation.Nullable;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v13.app.FragmentStatePagerAdapter;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link Fragment_TabSwipe.OnFragmentInteractionListener}
 * interface to handle interaction events. Use the
 * {@link Fragment_TabSwipe#newInstance} factory method to create an instance of
 * this fragment.
 * 
 */
public class Fragment_TabSwipe extends Fragment implements ActionBar.TabListener {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	public static final String TAG = "Fragment_TabSwipe";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;


	private OnFragmentInteractionListener mListener;




	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param bundle
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment Fragment_TabSwipe.
	 */
	static Boolean edit = false;

	public static Fragment_TabSwipe newInstance(String edit2, String param2) {
		Fragment_TabSwipe fragment = new Fragment_TabSwipe();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, edit2);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public Fragment_TabSwipe() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.activity_fragment__tab_swipper,
				container, false);
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(List<ScenesController> uri) {
		if (mListener != null) {
			mListener.onFragmentTabSwipeInteraction(uri);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnFragmentInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		public void onFragmentTabSwipeInteraction(List<ScenesController> listScenes);
	}


	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		final ActionBar actionBar = getActivity().getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

		mViewPager = (ViewPager) getActivity().findViewById(R.id.pager);

		mViewPager.setAdapter(mSectionsPagerAdapter);


		mViewPager
		.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});


		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}

		mViewPager.setCurrentItem(0);
		actionBar.setSelectedNavigationItem(0);
	}

	@Override
	public void onDestroyView() {
		final ActionBar actionBar = getActivity().getActionBar();
		actionBar.removeAllTabs();
		mSectionsPagerAdapter = null;
		mViewPager = null;
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		super.onDestroyView();
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {

		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).



			return PlaceholderFragment.newInstance(position + 1);
		}

		@Override
		public int getCount() {
			// Show 2 total pages.
			return 2;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.Escenas).toUpperCase(l);
			case 1:
				return getString(R.string.Eventos).toUpperCase(l);
			}
			return null;
		}
	}
	
	 static boolean edit3 = edit;
	public static class PlaceholderFragment extends Fragment {


		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */

		ListView listView;
		
		boolean edit2 = edit3;

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
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_fragment__tab_swipe,
					container, false);
			return rootView;
		}

		@Override
		public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
			TextView textView1 = (TextView) view.findViewById(R.id.textView3);
			int position = getArguments().getInt(ARG_SECTION_NUMBER);

			textView1.setText("Editar");
			switch (position){
			case 1: 
				listView = (ListView) view.findViewById(R.id.listView1);


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
						if (edit2){
						Toast.makeText(getActivity(), "Es editable", Toast.LENGTH_SHORT).show();
						}
						else{
							Toast.makeText(getActivity(), "NOPE", Toast.LENGTH_SHORT).show();
						}
						
					}

				});	break;
				
			case 2: 
				listView = (ListView) view.findViewById(R.id.listView1);
				
				List<ScenesController> listEvents = new ArrayList<ScenesController>();
				listEvents.add(new ScenesController(
						BitmapFactory.decodeResource(getResources(), R.drawable.lights), "Fiestuki", "Fiestuki")); 
				listEvents.add(new ScenesController(
						BitmapFactory.decodeResource(getResources(), R.drawable.lights), "Dormir", "Dormir")); 
				listEvents.add(new ScenesController(
						BitmapFactory.decodeResource(getResources(), R.drawable.lights), "Cena", "Cena")); 
				ScenesControllerAdapter adapterE = new ScenesControllerAdapter(getActivity(), listEvents);
				listView.setAdapter(adapterE);
				
				listView.setOnItemClickListener(new OnItemClickListener(){

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						if (edit2){
						Toast.makeText(getActivity(), "Es editable", Toast.LENGTH_SHORT).show();
						}
						else{
							Toast.makeText(getActivity(), "NOPE", Toast.LENGTH_SHORT).show();
						}
						
					}

				});	break;
				
			}
			
			
		}
	}
}


