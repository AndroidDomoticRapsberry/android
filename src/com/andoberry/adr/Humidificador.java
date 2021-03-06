package com.andoberry.adr;

import android.app.Activity;

import android.os.Bundle;
import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link Humidificador.OnFragmentInteractionListener}
 * interface to handle interaction events. Use the
 * {@link Humidificador#newInstance} factory method to create an instance of
 * this fragment.
 * 
 */
public class Humidificador extends Fragment {
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
	 * @return A new instance of fragment Humidificador.
	 */
	// TODO: Rename and change types and number of parameters
	public static Humidificador newInstance(String param1, String param2) {
		Humidificador fragment = new Humidificador();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public Humidificador() {
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
	
	static Boolean humidi = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View InputView = inflater.inflate(R.layout.fragment_humidificador, container, false);
		
		TextView temp = (TextView) InputView.findViewById(R.id.usuario);

		Button bt = (Button) InputView.findViewById(R.id.buttonAcceder);
		bt.setTag("Humidificador");
		
		onButtonPressed(temp);
		
		Menu_Principal p = new Menu_Principal();
		
		try {
			p.check(bt);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return InputView;
	}
	
	
	public void onButtonPressed(TextView temp) {
		if (mListener != null) {
			mListener.onFragmentInteraction(temp);
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
		public void onFragmentInteraction(TextView temp);

		void onFragmentBInteraction(Bundle uri);
	}

}
