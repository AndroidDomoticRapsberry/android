package com.andoberry.adr;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link Scenes_Config.OnFragmentInteractionListener}
 * interface to handle interaction events. Use the
 * {@link Scenes_Config#newInstance} factory method to create an instance of
 * this fragment.
 * 
 */
public class Scenes_Config extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	private OnFragmentInteractionListener mListener;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment Scenes_Config.
	 */
	// TODO: Rename and change types and number of parameters
	public static Scenes_Config newInstance(String param1, String param2) {
		Scenes_Config fragment = new Scenes_Config();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public Scenes_Config() {
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
		return inflater.inflate(R.layout.fragment_scenes__config, container,
				false);
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ListView listView;

		String s = mParam1;
		switch (s){
		case "luces":
			listView = (ListView) view.findViewById(R.id.listView1);
			List<ScenesController> lights = new ArrayList<ScenesController>();
			lights.add(new ScenesController(
					BitmapFactory.decodeResource(getResources(), R.drawable.comedor), "Comedor", "Luces1")); 
			lights.add(new ScenesController(
					BitmapFactory.decodeResource(getResources(), R.drawable.cocina), "Cocina", "Luces2")); 
			lights.add(new ScenesController(
					BitmapFactory.decodeResource(getResources(), R.drawable.bano), "Ba�o", "Luces3")); 
			ScenesControllerAdapter adapterL = new ScenesControllerAdapter(getActivity(), lights);
			listView.setAdapter(adapterL);

			break;
		case "persianas":
			listView = (ListView) view.findViewById(R.id.listView1);
			List<ScenesController> persianas = new ArrayList<ScenesController>();
			persianas.add(new ScenesController(
					BitmapFactory.decodeResource(getResources(), R.drawable.comedor), "Comedor", "Persiana1"));
			persianas.add(new ScenesController(
					BitmapFactory.decodeResource(getResources(), R.drawable.dormitorio), "Dormitorio", "Persiana2")); 
			ScenesControllerAdapter adapterP = new ScenesControllerAdapter(getActivity(), persianas);
			listView.setAdapter(adapterP);

			break;
		}
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
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

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated to
	 * the activity and potentially other fragments contained in that activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		public void onFragmentInteraction(Uri uri);
	}

}
