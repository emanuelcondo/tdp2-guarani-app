package ar.edu.uba.fi.tdp2.guaraniapp.login;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.ProgressPopup;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestHelper;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestSender;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseListener;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseWatcher;
import ar.edu.uba.fi.tdp2.guaraniapp.examenes.desinscripcion.DesinscripcionExamenesListener;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.desinscripcion.DesinscripcionCursosListener;

public class LoginFragment extends Fragment implements ResponseListener, ResponseWatcher {

    private static final String TAG = "LoginActivity";

    private EditText editTextUsuario;
    private EditText editTextPassword;
    private Button buttonLogin;
    private ProgressPopup progressPopup;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        buttonLogin = view.findViewById(R.id.btn_login);

        buttonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    private void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            return;
        }

        buttonLogin.setEnabled(false);

        progressPopup = new ProgressPopup("Ingresando...", getContext());
        progressPopup.show();

        editTextUsuario = getView().findViewById(R.id.input_userName);
        editTextPassword = getView().findViewById(R.id.input_password);

        String nombreUsuario = this.editTextUsuario.getText().toString();
        String password = editTextPassword.getText().toString();

        loginUsuario(nombreUsuario, password);

    }

    private void getAlumnoInfo() {
        AlumnoListener listener = new AlumnoListener((MainActivity) getActivity());
        RequestSender requestSender = new RequestSender(getActivity());

        String url = getString(R.string.urlAppServer) + "alumnos/mis-datos";

        requestSender.doGet_expectJSONObject(listener, url);
    }

    public void onLoginSuccess(String session) {
        Token.id = session;
        Token.conectado = true;

        ((MainActivity) getActivity()).sendFirebaseToken();

        getAlumnoInfo();

        progressPopup.dismiss();

        RequestHelper.showError(getActivity(), "Conectado a " + getString(R.string.app_name) + "!");

    }

    public void onLoginFailed() {

        progressPopup.dismiss();
        buttonLogin.setEnabled(true);
        editTextUsuario.requestFocus();
    }

    private boolean validate() {
        boolean valid = true;

        editTextUsuario = getView().findViewById(R.id.input_userName);
        editTextPassword = getView().findViewById(R.id.input_password);

        String nombreUsuario = this.editTextUsuario.getText().toString();
        String password = editTextPassword.getText().toString();

        if (nombreUsuario.isEmpty() || nombreUsuario.length() < 2) {
            this.editTextUsuario.setError(getString(R.string.nombre_error));
            this.editTextUsuario.requestFocus();
            valid = false;
        } else {
            this.editTextUsuario.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            editTextPassword.setError(getString(R.string.password_error));
            this.editTextPassword.requestFocus();
            valid = false;
        } else {
            editTextPassword.setError(null);
        }

        return valid;
    }

    private void loginUsuario(String nombreUsuario, String password) {
        Map<String,String> parametros;
        parametros = new HashMap<>();
        parametros.put("usuario", nombreUsuario);
        parametros.put("password", password);

        RequestSender requestSender = new RequestSender(getActivity());


        JSONObject jsonObject = new JSONObject(parametros);


        String url = getString(R.string.urlAppServer) + "alumnos/login";

        requestSender.doPost(this, url, jsonObject);
    }

    @Override
    public void onRequestCompleted(Object response) {
        try {
            JSONObject jsonObject = ((JSONObject)response).getJSONObject("data");
            String token = jsonObject.getString("token");
            //Usar gson y crear la clase Token

            onLoginSuccess(token);


        } catch (JSONException e) {
            RequestHelper.showError(getActivity(),"No se pudo obtener el Token");

            onLoginFailed();
        }

    }

    @Override
    public void onRequestError(int codError, String errorMessage) {
        RequestHelper.showError(getActivity(), errorMessage);
        onLoginFailed();
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError() {

    }
}

