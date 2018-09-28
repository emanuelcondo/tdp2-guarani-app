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
import ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion.InscripcionCarrerasFragment;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion.InscripcionMateriasFragment;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.oferta.OfertaCarrerasFragment;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.oferta.OfertaMateriasFragment;


public class FragmentLoader {

    public static final int DEFAULT_FRAGMENT = -1;//R.id.nav_actividades;
    private static int current = -1;

    //Carreras
    public static final String InscripcionCarreras = "InscripcionCarreras";
    public static final String OfertaCarreras = "OfertaCarreras";

    //Materias
    public static final String OfertaMaterias = "OfertaMaterias";
    public static final String InscripcionMaterias = "InscripcionMaterias";

    //Cursos
    public static final String OfertaCursos = "OfertaCursos";
    public static final String InscripcionCursos = "InscripcionCursos";
    public static final String DesinscripcionCursos = "DesinscripcionCursos";

    public static final String OfertaCurso = "OfertaCurso";
    public static final String InscripcionCurso = "InscripcionCurso";

    public static final String DesinscripcionCurso = "DesinscripcionCurso";

    public static final String Login = "Login";
    public static final String HistoriaAcademica = "HistoriaAcademica";




    private static final Map<String, Boolean> drawerVisibilityMap;
    static
    {
        drawerVisibilityMap = new HashMap<>();

        //Carreras
        drawerVisibilityMap.put(InscripcionCarreras, true);
        drawerVisibilityMap.put(OfertaCarreras, true);

        //Materias
        drawerVisibilityMap.put(InscripcionMaterias, true);
        drawerVisibilityMap.put(OfertaMaterias, true);

        //Cursos
        drawerVisibilityMap.put(OfertaCursos, true);
        drawerVisibilityMap.put(InscripcionCursos, true);
        drawerVisibilityMap.put(DesinscripcionCursos, true);

        drawerVisibilityMap.put(OfertaCurso, true);
        drawerVisibilityMap.put(InscripcionCurso, true);

        drawerVisibilityMap.put(DesinscripcionCurso, false);

        drawerVisibilityMap.put(Login, false);
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
            case R.id.nav_oferta:
                fragment = new OfertaCarrerasFragment();
                name = OfertaCarreras;
                break;
            case R.id.nav_inscribirme:
                fragment = new InscripcionCarrerasFragment();
                name = InscripcionCarreras;
                break;
            case R.id.nav_historia:
                fragment = new HistoriaAcademicaFragment();
                name = HistoriaAcademica;
                break;
            case R.id.nav_desinscribirme:
                fragment = new DesinscripcionCursosFragment();
                name = DesinscripcionCursos;
                break;
            default:
                // TODO: Corregir cual es el default
                fragment = new OfertaCarrerasFragment();
                name = OfertaCarreras;
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
