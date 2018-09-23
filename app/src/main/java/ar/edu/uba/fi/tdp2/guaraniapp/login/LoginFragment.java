package ar.edu.uba.fi.tdp2.guaraniapp.login;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.FragmentLoader;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Estudiante;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion.InscripcionMateriasFragment;

public class LoginFragment extends Fragment {

    private static final String TAG = "LoginActivity";

    private EditText campo_nombreUsuario;
    private EditText campo_password;
    private Button boton_login;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        boton_login = view.findViewById(R.id.btn_login);

        boton_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        Log.d(TAG, "Login");

        //TODO: Arreglar cuando tengamos login
        ((MainActivity) getActivity()).setUsuario(new Estudiante(95010, "Carlos", "Ramirez"));
        FragmentLoader.load(getActivity(), new InscripcionMateriasFragment(), "Inscripcion");

        /*if (!validate()) {
            return;
        }

        boton_login.setEnabled(false);

        progressDialog = new ProgressDialog(getContext(),
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Ingresando...");
        progressDialog.show();

        campo_nombreUsuario = getView().findViewById(R.id.input_userName);
        campo_password = getView().findViewById(R.id.input_password);

        String nombreUsuario = campo_nombreUsuario.getText().toString();
        String password = campo_password.getText().toString();

        loginUsuario(nombreUsuario, password);*/

    }

    public void onLoginSuccess(String session) {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    public void onLoginFailed() {
        if (progressDialog != null)
            progressDialog.dismiss();
        boton_login.setEnabled(true);
    }

    private boolean validate() {
        boolean valid = true;

        campo_nombreUsuario = getView().findViewById(R.id.input_userName);
        campo_password = getView().findViewById(R.id.input_password);

        String nombreUsuario = campo_nombreUsuario.getText().toString();
        String password = campo_password.getText().toString();

        if (nombreUsuario.isEmpty() || nombreUsuario.length() < 2) {
            campo_nombreUsuario.setError(getString(R.string.nombre_error));
            valid = false;
        } else {
            campo_nombreUsuario.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            campo_password.setError(getString(R.string.password_error));
            valid = false;
        } else {
            campo_password.setError(null);
        }

        return valid;
    }

    private void loginUsuario(String nombreUsuario, String password) {
        Map<String,String> parametros;
        parametros = new HashMap<>();
        /*RequestSender requestSender = new RequestSender(this);
        parametros.put("name", nombreUsuario);
        parametros.put("password", password);

        JSONObject jsonObject = new JSONObject(parametros);

        String url = getString(R.string.urlAppServer) + "users/login";

        LoginListener listener = new LoginListener(this);

        requestSender.doPost(listener, url, jsonObject);*/
    }

}

