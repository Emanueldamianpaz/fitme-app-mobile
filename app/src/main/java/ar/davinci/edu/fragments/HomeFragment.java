package ar.davinci.edu.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ar.davinci.edu.R;

public class HomeFragment extends Fragment {

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        bootstraping(v);
        return v;
    }

    private void bootstraping(View container) {

        CardView cardKilometersRunned = container.findViewById(R.id.cardKilometersRunned);
        cardKilometersRunned.setOnClickListener(v -> {
            // TODO cambiar fragment por el adapterList
        });

        CardView cardCalories = container.findViewById(R.id.cardCalories);
        cardCalories.setOnClickListener(v -> {
            // TODO cambiar fragment por el adapterList
        });

    }
}
