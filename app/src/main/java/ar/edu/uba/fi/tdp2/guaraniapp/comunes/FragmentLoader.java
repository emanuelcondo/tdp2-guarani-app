package ar.edu.uba.fi.tdp2.guaraniapp.comunes;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.historia.HistoriaAcademicaFragment;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.desinscripcion.DesinscripcionCursosFragment;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion.InscripcionMateriasFragment;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.oferta.OfertaMateriasFragment;


public class FragmentLoader {

    public static final int DEFAULT_FRAGMENT = -1;//R.id.nav_actividades;
    private static int current = -1;

    public static final String Inscripcion = "Inscripcion";
    public static final String InscripcionCurso = "InscripcionCurso";
    public static final String OfertaHoraria = "OfertaHoraria";
    public static final String OfertaCurso = "OfertaCurso";
    public static final String Desinscripcion = "Desinscripcion";
    public static final String DesinscripcionCurso = "DesinscripcionCurso";
    public static final String Cursos = "Cursos";
    public static final String Login = "Login";
    public static final String HistoriaAcademica = "HistoriaAcademica";


    private static final Map<String, Boolean> drawerVisibilityMap;
    static
    {
        drawerVisibilityMap = new HashMap<>();
        drawerVisibilityMap.put(Inscripcion, true);
        drawerVisibilityMap.put(OfertaHoraria, true);
        drawerVisibilityMap.put(OfertaCurso, true);
        drawerVisibilityMap.put(Desinscripcion, true);
        drawerVisibilityMap.put(Cursos, true);
        drawerVisibilityMap.put(Login, false);
        drawerVisibilityMap.put(InscripcionCurso, true);
        drawerVisibilityMap.put(DesinscripcionCurso, true);
        drawerVisibilityMap.put(HistoriaAcademica, true);


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

        switch (id) {
            case R.id.nav_oferta_horaria:
                fragment = new OfertaMateriasFragment();
                name = OfertaHoraria;
                break;
            case R.id.nav_inscribirme:
                fragment = new InscripcionMateriasFragment();
                name = Inscripcion;
                break;
            case R.id.nav_historia_academica:
                fragment = new HistoriaAcademicaFragment();
                name = HistoriaAcademica;
                break;
            case R.id.nav_desinscribirme:
                fragment = new DesinscripcionCursosFragment();
                name = Desinscripcion;
                break;
            default:
                // TODO: Corregir cual es el default
                fragment = new OfertaMateriasFragment();
                name = OfertaHoraria;
        }

        load(activity, fragment, name);

        current = id;

    }

    private static String getFragmentName(FragmentManager fragmentManager, int pos){


        if (pos >= 0) {
            Log.d("getFragmentName", fragmentManager.getBackStackEntryAt(pos).getName());
            return fragmentManager.getBackStackEntryAt(pos).getName();
        }

        return "";

    }

}
