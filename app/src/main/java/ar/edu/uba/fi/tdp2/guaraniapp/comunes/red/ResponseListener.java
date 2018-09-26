package ar.edu.uba.fi.tdp2.guaraniapp.comunes.red;


public interface ResponseListener {

    void onRequestCompleted(Object response);

    void onRequestError(int codError, String errorMessage);


}
