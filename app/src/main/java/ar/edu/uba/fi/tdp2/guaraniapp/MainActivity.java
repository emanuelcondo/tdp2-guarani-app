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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ar.edu.uba.fi.tdp2.guaraniapp.comunes.FragmentLoader;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.firebase.FirebaseMessagingManager;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Examen;
import ar.edu.uba.fi.tdp2.guaraniapp.model.InscripcionExamen;
import ar.edu.uba.fi.tdp2.guaraniapp.login.LoginFragment;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Curso;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Alumno;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Inscripcion;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Materia;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Periodo;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;

    private Alumno alumno;
    private Periodo periodo;
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
        if (getAlumno() != null && getAlumno().getInscripciones() != null) {
            setDesinscripcionesEnabled(esFechaDeDesinscripcionCursos());
        }
    }

    public boolean esFechaDeDesinscripcionCursos() {
        String fechaInicioDesinscripcion = this.periodo.getDesinscripcionCurso().getInicio();
        String fechaFinDesinscripcion = this.periodo.getDesinscripcionCurso().getFin();

        Date dateInicio = null;
        Date dateFin = null;
        Date hoy = Calendar.getInstance().getTime();
        try {
            dateInicio = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
                    , Locale.getDefault()).parse(fechaInicioDesinscripcion);
            dateFin = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
                    , Locale.getDefault()).parse(fechaFinDesinscripcion);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateInicio);
            cal.add(Calendar.HOUR, -3);
            dateInicio = cal.getTime();
            cal.setTime(dateFin);
            cal.add(Calendar.HOUR, -3);
            dateFin = cal.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return hoy.after(dateInicio) && hoy.before(dateFin);
    }

    public void setDesinscripcionesEnabled(boolean enabled) {
        NavigationView navigationView = findViewById(R.id.nav_view);

        Menu menuNav = navigationView.getMenu();
        MenuItem nav_desinscr = menuNav.findItem(R.id.nav_desinscribirme);
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

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public Date getFechaInicioInscripcion() {
        //TODO: Calcular en base a la prioridad
        String fechaInicioInscripcion = this.periodo.getInscripcionCurso().getInicio();

        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
                    , Locale.getDefault()).parse(fechaInicioInscripcion);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.HOUR, -3);

            //Calculo la fecha y hora segun prioridad

            // INTERVALO => cantidad de veces que se puede dividir un dia (9 a 18hs) en intervalos de media hora
            int INTERVALO = 18;
            // PASO = 0.5 => valor en horas de lo que tiene que esperar el alumno por cada punto de prioridad
            double PASO = 0.5;


            // si el resultado de dividir por INTERVALO es > 0 incremento dias
            // y el residuo hace que incrementen las horas
            int prioridad = alumno.getPrioridad();
            if (prioridad >= 91) {
                //la prioridad supera el maximo lo pongo el viernes a las 17:30
                cal.add(Calendar.DATE, 4);
                cal.add(Calendar.HOUR, 8);
                cal.add(Calendar.MINUTE, 30);
            } else {
                double aux = (double) prioridad / INTERVALO;
                //supongo que la inscripcion se produce en una semana completa entonces no tengo en cuenta los findes
                int dias = (int) Math.floor(aux);
                if (aux > 1) {
                    cal.add(Calendar.DATE, dias);
                    //fix por bug al siguiente dia
                    //cal.add(Calendar.MINUTE, 30);
                }
                double horasMinutos = ((aux - dias) > 0 )?(prioridad - dias * INTERVALO) * PASO - PASO : 8.5;
                int horas = (int) Math.floor(horasMinutos);

                cal.add(Calendar.HOUR, horas);


                int minutos = (int) ((horasMinutos - horas) * 60);

                if (minutos > 0) {
                    cal.add(Calendar.MINUTE, minutos);
                }
            }
            date = cal.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public void flipInscripionCursos() {
        NavigationView navigationView = findViewById(R.id.nav_view);

        Menu menuNav = navigationView.getMenu();
        MenuItem menuItem = menuNav.findItem(R.id.nav_inscribirme);
        menuItem.setEnabled(esFechaDeInscripcionCursos());
    }

    private boolean esFechaDeInscripcionCursos() {
        Date hoy = Calendar.getInstance().getTime();
        Date dateFinInscripcion = null;
        try {
            dateFinInscripcion = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
                    , Locale.getDefault()).parse(periodo.getInscripcionCurso().getFin());
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateFinInscripcion);
            cal.add(Calendar.HOUR, -3);
            dateFinInscripcion = cal.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return hoy.after(getFechaInicioInscripcion()) && hoy.before(dateFinInscripcion);
    }

    public boolean existePrioridad() {
        Date hoy = Calendar.getInstance().getTime();
        return hoy.after(getFechaPublicacionPrioridades());
    }

    public Date getFechaPublicacionPrioridades() {
        Date datePublicacionPrioridad = null;
        try {
            datePublicacionPrioridad = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
                    , Locale.getDefault()).parse(periodo.getConsultaPrioridad().getInicio());
            Calendar cal = Calendar.getInstance();
            cal.setTime(datePublicacionPrioridad);
            cal.add(Calendar.HOUR, -3);
            datePublicacionPrioridad = cal.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return datePublicacionPrioridad;
    }

    public Date getFechaFinInscripcion() {
        Date dateFinInscripcion = null;
        try {
            dateFinInscripcion = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
                    , Locale.getDefault()).parse(periodo.getInscripcionCurso().getFin());
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateFinInscripcion);
            cal.add(Calendar.HOUR, -3);
            dateFinInscripcion = cal.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateFinInscripcion;
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
