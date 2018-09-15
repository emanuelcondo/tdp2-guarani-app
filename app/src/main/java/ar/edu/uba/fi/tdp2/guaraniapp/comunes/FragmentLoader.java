package ar.edu.uba.fi.tdp2.guaraniapp.comunes;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.MateriasFragment;


public class FragmentLoader {

    public static final int DEFAULT_FRAGMENT = -1;//R.id.nav_actividades;
    private static int current = -1;

    public static final String Inscripcion = "Inscripcion";
    public static final String OfertaHoraria = "OfertaHoraria";
    public static final String Desinscripcion = "Desinscripcion";


    private static final Map<String, Boolean> drawerVisibilityMap;
    static
    {
        drawerVisibilityMap = new HashMap<>();
        drawerVisibilityMap.put(Inscripcion, true);
        drawerVisibilityMap.put(OfertaHoraria, true);
        drawerVisibilityMap.put(Desinscripcion, true);


    }


    public static void load(Activity activity, Fragment fragment, String name) {
        Bundle bundle = new Bundle();

        fragment.setArguments(bundle);

        FragmentManager fragmentManager = activity.getFragmentManager();
        int stackCount = fragmentManager.getBackStackEntryCount();
        String previous = getFragmentName(fragmentManager, stackCount - 1);

        if (previous.equals(name)) { return;}

        fragmentManager.beginTransaction()
                .replace(R.id.contenedor, fragment)
                .addToBackStack(name)
                .commit();

        boolean drawerVisible = drawerVisibilityMap.get(name);
        ((MainActivity) activity).setDrawerEnabled(drawerVisible);


    }

    public static boolean backFragment(Activity activity) {
        FragmentManager fragmentManager = activity.getFragmentManager();

        int stackCount = fragmentManager.getBackStackEntryCount();
        if ( stackCount > 1) {
            fragmentManager.popBackStack();
            String next = getFragmentName(fragmentManager, stackCount - 2);
            boolean visible = drawerVisibilityMap.get(next);
            ((MainActivity) activity).setDrawerEnabled(visible);

            return true;

        }
        return false;
    }

    public static void load(Activity activity, int id) {
        Fragment fragment;
        String name;

        if (id == R.id.nav_inscribirme) {
            //Mostrar pantalla perfil
            fragment = new MateriasFragment();
            name = Inscripcion;
        } /*else if (id == R.id.nav_calendario) {
            fragment = new CalendarioFragment();
            name = Calendario;
        }*/ else {

            // TODO: Corregir cual es el default
            fragment = new MateriasFragment();
            name = Inscripcion;
        }

        load(activity, fragment, name);

        current = id;

    }

    private static String getFragmentName(FragmentManager fragmentManager, int pos){


        if (pos >= 0) {
            return fragmentManager.getBackStackEntryAt(pos).getName();
        }

        return "";

    }

}
