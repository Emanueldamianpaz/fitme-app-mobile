package ar.davinci.edu.infraestructure;


import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import ar.davinci.edu.R;

public class FragmentMng {

    public static void changeFragments(AppCompatActivity context, Fragment fragment) {
        context.getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentMain, fragment)
                .commit();
    }
}
