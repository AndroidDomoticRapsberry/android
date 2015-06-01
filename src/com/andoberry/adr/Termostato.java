package com.andoberry.adr;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link Termostato.OnFragmentInteractionListener} interface
 * to handle interaction events. Use the {@link Termostato#newInstance} factory
 * method to create an instance of this fragment.
 * 
 */
public class Termostato extends Fragment {
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
	 * @return A new instance of fragment Termostato.
	 */
	// TODO: Rename and change types and number of parameters
	public static Termostato newInstance(String param1, String param2) {
		Termostato fragment = new Termostato();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public Termostato() {
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

	static Boolean trmstato = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View InputView = inflater.inflate(R.layout.fragment_termostato, container, false);

		final TextView temp = (TextView) InputView.findViewById(R.id.usuario);

		final Button bt = (Button) InputView.findViewById(R.id.buttonAcceder);
		bt.setTag("TermBt");

		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					Button b = (Button) v;
					String tac = (String) b.getTag();
					switch(tac){
					case "TermBt":

						String StT1 = String.valueOf(trmstato);
						ControlThread cT = new ControlThread(SocketHandler.getip(), SocketHandler.getPort(), SocketHandler.getSocket(), StT1, "Permisos", "termostato", "Writer");
						cT.start();
						cT.join();
						String perm = cT.getPermiso();
						if (trmstato != null){
							if (trmstato == false){
								if (perm.equalsIgnoreCase("vision")){
									Toast.makeText(getActivity(), "No tienes permisos", Toast.LENGTH_SHORT).show();
								}
								else {
									b.setBackgroundColor(Color.parseColor("#00FF00"));
									b.setTextColor(Color.parseColor("#000000"));
									b.setText("ENCENDIDO");
									temp.setText("20");
									trmstato = true;
									onButtonPressed(trmstato);
								}
							}
							else {
								StT1 = String.valueOf(trmstato);
								cT = new ControlThread(SocketHandler.getip(), SocketHandler.getPort(), SocketHandler.getSocket(), StT1, "Permisos", "termostato", "Writer");
								cT.start();
								cT.join();
								perm = cT.getPermiso();
								if (perm.equalsIgnoreCase("vision")){
									Toast.makeText(getActivity(), "No tienes permisos", Toast.LENGTH_SHORT).show();
								}
								else {
									b.setBackgroundColor(Color.parseColor("#CCCCCC"));
									b.setTextColor(Color.parseColor("#000000"));
									b.setText("APAGADO");
									temp.setText("0");
									trmstato = false; 
									onButtonPressed(trmstato);
								}
							}
						
						Menu_Principal p = new Menu_Principal();
						p.check(bt);
						checkT(bt, temp, trmstato);
						}
						break;
					}



				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		return InputView;
	}


	public void checkT(Button b, TextView t, boolean trm) throws InterruptedException{

		ControlThread cT = new ControlThread(SocketHandler.getip(), SocketHandler.getPort(), SocketHandler.getSocket(), "lectura", "Listener");
		cT.start();
		cT.join();

		String T1 = cT.getbT1();

		Boolean trm1 = Boolean.getBoolean(T1);
		if (trm1 != null){
		if (trm1 == false){
			b.setBackgroundColor(Color.parseColor("#CCCCCC"));
			b.setTextColor(Color.parseColor("#000000"));
			b.setText("APAGADO");
			t.setText("0");
			//			trm = true;
		}
		else{
			b.setBackgroundColor(Color.parseColor("#00FF00"));			
			b.setTextColor(Color.parseColor("#000000"));
			b.setText("ENCENDIDO");
			t.setText("20");
			//trm = false; 
		}
		}
		//return trm;

	}


	@Override
	public void onViewCreated (View view, Bundle savedInstanceState){

	}



	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(boolean trm) {
		if (mListener != null) {
			mListener.onFragmentInteraction(trm);
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
		public void onFragmentInteraction(boolean trm);
	}

}
