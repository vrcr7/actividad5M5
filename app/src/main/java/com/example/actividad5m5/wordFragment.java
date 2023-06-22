package com.example.actividad5m5;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link wordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class wordFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private EditText texto;
private Button boton2;

    public wordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.

     * @return A new instance of fragment wordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static wordFragment newInstance(String param1) {
        wordFragment fragment = new wordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_word, container, false);
        texto=view.findViewById(R.id.editTextText);
        texto.setText(mParam1);
        boton2= view.findViewById(R.id.button2);
        boton2.setOnClickListener(new View.OnClickListener() {
             @Override
                public void onClick(View v) {
                 if (dataPassListener != null) {
                     dataPassListener.onDataPass(texto.getText().toString());
                 }
                 getActivity().onBackPressed();

             }
        });
        return view;
    }

    public interface OnDataPassListener {
        void onDataPass(String data);
    }
    private OnDataPassListener dataPassListener;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            dataPassListener = (OnDataPassListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }
}