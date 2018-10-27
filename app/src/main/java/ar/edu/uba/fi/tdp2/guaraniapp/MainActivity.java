package ar.edu.uba.fi.tdp2.guaraniapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import ar.edu.uba.fi.tdp2.guaraniapp.comunes.FragmentLoader;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.firebase.FirebaseMessagingManager;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Examen;
import ar.edu.uba.fi.tdp2.guaraniapp.model.InscripcionExamen;
import ar.edu.uba.fi.tdp2.guaraniapp.login.LoginFragment;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Curso;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Alumno;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Inscripcion;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Materia;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;

    private Alumno alumno;
    private List<Materia> materias = new ArrayList<>();
    private List<Examen> fechasExamen = new ArrayList<>();

    private FirebaseMessagingManager firebaseMessagingManager;

    private Curso cursoSeleccionado;
    private Materia materiaSeleccionada;
    private Inscripcion inscripcionSeleccionada;
    private InscripcionExamen inscripcionExamenSeleccionada;
    //private Examen examenSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer =  findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentLoader.load(this, new LoginFragment(), "Login");

        firebaseMessagingManager = new FirebaseMessagingManager(this);
    }

    @Override
    public void onBackPressed() {
        //DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (!FragmentLoader.backFragment(this)) {
                showExitDialog();
            }
        }
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        FragmentLoader.load(this, id);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setDrawerEnabled(final boolean enabled) {

        int lockMode = enabled ? DrawerLayout.LOCK_MODE_UNLOCKED :
                DrawerLayout.LOCK_MODE_LOCKED_CLOSED;

        drawer.setDrawerLockMode(lockMode);
        toggle.setDrawerIndicatorEnabled(enabled);

        ActionBar actionBar = getSupportActionBar();


        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(!enabled);
            actionBar.setDisplayShowHomeEnabled(enabled);
            actionBar.setHomeButtonEnabled(enabled);
        }

        toggle.syncState();

        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!toggle.isDrawerIndicatorEnabled())
                    onBackPressed();

            }
        });
    }

    public void setCursoSeleccionado(Curso curso) {
        cursoSeleccionado = curso;
    }

    public Curso getCursoSeleccionado() {
        return cursoSeleccionado;
    }

    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String title = getString(R.string.message_confirm_exit);
        builder.setTitle(title);

        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finishAffinity();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }

    public Materia getMateriaSeleccionada() {
        return materiaSeleccionada;
    }

    public void setMateriaSeleccionada(Materia materiaSeleccionada) {
        this.materiaSeleccionada = materiaSeleccionada;
    }

    public void setToolbarName(String toolbarName) {
        toolbar.setTitle(toolbarName);
    }

    public Inscripcion getInscripcionSeleccionada() {
        return inscripcionSeleccionada;
    }

    public void setInscripcionSeleccionada(Inscripcion inscripcionSeleccionada) {
        this.inscripcionSeleccionada = inscripcionSeleccionada;
    }

    public List<Examen> getFechasExamen() {
        return fechasExamen;
    }

    public void setFechasExamen(List<Examen> fechasExamen) {
        this.fechasExamen = fechasExamen;
    }

    public void flipDesinscripcion() {
        if (getAlumno().getInscripciones() != null) {
            int inscripciones = getAlumno().getInscripciones().size();
            setDesinscripcionesEnabled(inscripciones > 0);
        }
    }

    public void flipDesinscripcionExamenes() {
        if (getAlumno().getInscripcionesExamenes() != null) {
            int inscripciones = getAlumno().getInscripcionesExamenes().size();
            setDesinscripcionesExamenesEnabled(inscripciones > 0);
        }
    }

    public void setDesinscripcionesEnabled(boolean enabled) {
        NavigationView navigationView = findViewById(R.id.nav_view);

        Menu menuNav = navigationView.getMenu();
        MenuItem nav_desinscr = menuNav.findItem(R.id.nav_desinscribirme);
        nav_desinscr.setEnabled(enabled);
    }

    public void setDesinscripcionesExamenesEnabled(boolean enabled) {
        NavigationView navigationView = findViewById(R.id.nav_view);

        Menu menuNav = navigationView.getMenu();
        MenuItem nav_desinscr = menuNav.findItem(R.id.nav_desinscribirme_examen);
        nav_desinscr.setEnabled(enabled);
    }

    public void eliminarInscripcion(Inscripcion inscripcion) {
        alumno.eliminarInscripcion(inscripcion);
        flipDesinscripcion();
    }

    public InscripcionExamen getInscripcionExamenSeleccionada() {
        return inscripcionExamenSeleccionada;
    }

    public void setInscripcionExamenSeleccionada(InscripcionExamen inscripcionExamenSeleccionada) {
        this.inscripcionExamenSeleccionada = inscripcionExamenSeleccionada;
    }

    public void sendFirebaseToken() {
        firebaseMessagingManager.sendFirebaseToken();
    }

    public void removeFechaExamen(Examen examen) {
        this.fechasExamen.remove(examen);
    }

    public void removeInscripcionExamen(InscripcionExamen inscripcionExamen) {
        this.alumno.getInscripcionesExamenes().remove(inscripcionExamen);
    }

    /*
    public Examen getExamenSeleccionado() {
        return examenSeleccionado;
    }

    public void setExamenSeleccionado(Examen examenSeleccionado) {
        this.examenSeleccionado = examenSeleccionado;
    }
    */
}
