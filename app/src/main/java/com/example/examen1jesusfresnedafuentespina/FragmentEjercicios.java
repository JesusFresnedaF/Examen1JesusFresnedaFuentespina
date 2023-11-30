package com.example.examen1jesusfresnedafuentespina;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentEjercicios#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentEjercicios extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentEjercicios() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentEjercicios.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentEjercicios newInstance(String param1, String param2) {
        FragmentEjercicios fragment = new FragmentEjercicios();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View rootView = inflater.inflate(R.layout.fragment_ejercicios, container, false);
        ListView lista = rootView.findViewById(R.id.lista_ejercicios);
        //lista de ejercicios con sus imagenes
        String ejercicios[] = {"Ejercicio 1", "Ejercicio 2", "Ejercicio 3"};
        int imagenes[] = {R.drawable.ejercicio1_foreground, R.drawable.ejercicio2_foreground, R.drawable.ejercicio3_foreground};
        //adapter que configurará como deben ser los items de la lista. En este caso quiero que tengan una imagen y un texto.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, ejercicios) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = view.findViewById(android.R.id.text1);
                textView.setCompoundDrawablesWithIntrinsicBounds(imagenes[position], 0, 0, 0);
                textView.setCompoundDrawablePadding(15);
                return view;
            }
        };
        lista.setAdapter(adapter);

        //cuando pulse un item, mostrará el fragment que quiro que muestre.
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obteniendo el elemento seleccionado utilizando la posición
                String elementoSeleccionado = (String) parent.getItemAtPosition(position);
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Configuration config = getResources().getConfiguration();
                //si está en horizontal, lo muestro debajo de la lista
                if(config.orientation == Configuration.ORIENTATION_LANDSCAPE){
                    switch(elementoSeleccionado){
                        case "Ejercicio 1":
                            FragmentEjercicio1 fragment1 = new FragmentEjercicio1();
                            transaction.replace(R.id.descripcion, fragment1);
                            break;
                        case "Ejercicio 2":
                            FragmentEjercicio2 fragment2 = new FragmentEjercicio2();
                            transaction.replace(R.id.descripcion, fragment2);
                            break;
                        case "Ejercicio 3":
                            FragmentEjercicio3 fragment3 = new FragmentEjercicio3();
                            transaction.replace(R.id.descripcion, fragment3);
                            break;
                    }
                }
                //si está en vertical sustituyo la lista por la descripción del ejercicio
                else{
                    switch(elementoSeleccionado){
                        case "Ejercicio 1":
                            FragmentEjercicio1 fragment1 = new FragmentEjercicio1();
                            transaction.replace(R.id.listaEjercicios, fragment1);
                            break;
                        case "Ejercicio 2":
                            FragmentEjercicio2 fragment2 = new FragmentEjercicio2();
                            transaction.replace(R.id.listaEjercicios, fragment2);
                            break;
                        case "Ejercicio 3":
                            FragmentEjercicio3 fragment3 = new FragmentEjercicio3();
                            transaction.replace(R.id.listaEjercicios, fragment3);
                            break;
                    }
                }


                transaction.commit();
            }
        });

        return rootView;
    }
}