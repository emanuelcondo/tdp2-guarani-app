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

import ar.edu.uba.fi.tdp2.guaraniapp.comunes.FragmentLoader;
import ar.edu.uba.fi.tdp2.guaraniapp.login.LoginFragment;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Curso;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Estudiante;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Horario;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Materia;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;

    private Estudiante usuario;
    private ArrayList<Materia> materias = new ArrayList<>();

    private Curso cursoSeleccionado;
    private Materia materiaSeleccionada;

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

        //TODO: Borrar esto cuando este implementado
        mockearMaterias();

        FragmentLoader.load(this, new LoginFragment(), "Login");
    }

    private void mockearMaterias() {
        Materia materia1 = new Materia("75.01", "Algoritmos y Programación I", "Computacion");
        ArrayList<Horario> horarios1 = new ArrayList<Horario>() {{
            add(new Horario("Lunes",19,22, "Paseo Colón", "LB"));
            add(new Horario("Martes", 19, 22, "Las Heras", "303"));
        }};
        Curso curso1 = new Curso(12,"Gustavo Campagnuolo", horarios1, 0);
        curso1.setAyudantes(new ArrayList<String>() {{
            add("Claudio Ubeda");
            add("Gabriel Loeschbor");
        }});
        materia1.agregarCurso(curso1);
        ArrayList<Horario> horarios2 = new ArrayList<Horario>() {{
            add(new Horario("Miércoles",15,22));
        }};
        materia1.agregarCurso(new Curso(2,"Reinaldo Merlo", horarios2, 20));
        Materia materia2 = new Materia("71.26", "Modelos y Optimización II", "Gestion");
        ArrayList<Horario> horarios3 = new ArrayList<Horario>() {{
            add(new Horario("Viernes",15,23));
        }};
        materia2.agregarCurso(new Curso(1, "José Chatruc", horarios3, 25));
        ArrayList<Horario> horarios4 = new ArrayList<Horario>() {{
            add(new Horario("Lunes",19,22));
            add(new Horario("Martes", 19, 20));
            add(new Horario("Jueves", 20, 22));
        }};
        Curso curso2 = new Curso(2, "Adrián Bastía", horarios4, 40);
        curso2.setAyudantes(new ArrayList<String>() {{
            add("Gerardo Bedoya");
            add("Maximiliano Estévez");
            add("Rafael Maceratesi");
        }});
        materia2.agregarCurso(curso2);
        materias.add(materia1);
        materias.add(materia2);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (!FragmentLoader.backFragment(this)) {
                showExitDialog();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

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

    public void setUsuario(Estudiante usuario) {
        this.usuario = usuario;
    }

    public Estudiante getUsuario() {
        return usuario;
    }

    public ArrayList<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(ArrayList<Materia> materias) {
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
}
