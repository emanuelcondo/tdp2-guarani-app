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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.FragmentLoader;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestHelper;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestSender;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseListener;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Estudiante;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion.InscripcionMateriasFragment;

public class LoginFragment extends Fragment implements ResponseListener {

    private static final String TAG = "LoginActivity";

    private EditText editTextUsuario;
    private EditText editTextPassword;
    private Button buttonLogin;
    private ProgressDialog progressDialog;

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

        progressDialog = new ProgressDialog(getContext(),
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Ingresando...");
        progressDialog.show();

        editTextUsuario = getView().findViewById(R.id.input_userName);
        editTextPassword = getView().findViewById(R.id.input_password);

        String nombreUsuario = this.editTextUsuario.getText().toString();
        String password = editTextPassword.getText().toString();

        loginUsuario(nombreUsuario, password);

    }

    public void onLoginSuccess(String session) {
        if (progressDialog != null)
            progressDialog.dismiss();

        Token.id = session;
        Token.conectado = true;
        RequestHelper.showError(getActivity(), "Logueado!");

        ((MainActivity) getActivity()).setUsuario(new Estudiante(95010, "Carlos", "Ramirez"));
        FragmentLoader.load(getActivity(), new InscripcionMateriasFragment(), "Inscripcion");
    }

    public void onLoginFailed() {
        if (progressDialog != null)
            progressDialog.dismiss();
        buttonLogin.setEnabled(true);
        editTextUsuario.setError(getString(R.string.nombre_error));
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
            valid = false;
        } else {
            this.editTextUsuario.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            editTextPassword.setError(getString(R.string.password_error));
            valid = false;
        } else {
            editTextPassword.setError(null);
        }

        return valid;
    }

    private void loginUsuario(String nombreUsuario, String password) {
        Map<String,String> parametros;
        parametros = new HashMap<>();
        RequestSender requestSender = new RequestSender(getActivity());
        parametros.put("usuario", nombreUsuario);
        parametros.put("password", password);

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

        RequestHelper.showError(getActivity(), codError + ": " + errorMessage);
        onLoginFailed();
    }


}

