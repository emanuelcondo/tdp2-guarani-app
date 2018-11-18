package ar.edu.uba.fi.tdp2.guaraniapp.encuestas;

//import com.example.android.common.logger.Log;
import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.FragmentLoader;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestSender;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseWatcher;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.vista.SlidingTabLayout;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.desinscripcion.DesinscripcionListener;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Encuesta;
import ar.edu.uba.fi.tdp2.guaraniapp.model.EncuestaCurso;

import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A basic sample which shows how to use {@link ar.edu.uba.fi.tdp2.guaraniapp.comunes.vista.SlidingTabLayout}
 * to display a custom {@link ViewPager} title strip which gives continuous feedback to the user
 * when scrolling.
 */
public class SlidingTabsBasicFragment extends Fragment implements ResponseWatcher {

    static final String LOG_TAG = "SlidingTabsBasicFragment";

    private Encuesta encuesta;
    private EncuestaCurso encuestaCurso;

    /**
     * A custom {@link ViewPager} title strip which looks much like Tabs present in Android v4.0 and
     * above, but is designed to give continuous feedback to the user when scrolling.
     */
    private SlidingTabLayout mSlidingTabLayout;

    /**
     * A {@link ViewPager} which will be used in conjunction with the {@link SlidingTabLayout} above.
     */
    private ViewPager mViewPager;


    /**
     * Inflates the {@link View} which will be displayed by this {@link Fragment}, from the app's
     * resources.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.encuesta = new Encuesta(0, 0, 0, 0, 0, null);
        this.encuestaCurso = ((MainActivity) getActivity()).getEncuestaCursoSeleccionada();
        return inflater.inflate(R.layout.fragment_encuesta, container, false);
    }

    // BEGIN_INCLUDE (fragment_onviewcreated)
    /**
     * This is called after the {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)} has finished.
     * Here we can pick out the {@link View}s we need to configure from the content view.
     *
     * We set the {@link ViewPager}'s adapter to be an instance of {@link SamplePagerAdapter}. The
     * {@link SlidingTabLayout} is then given the {@link ViewPager} so that it can populate itself.
     *
     * @param view View created in {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // BEGIN_INCLUDE (setup_viewpager)
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mViewPager = view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new SamplePagerAdapter());
        // END_INCLUDE (setup_viewpager)

        // BEGIN_INCLUDE (setup_slidingtablayout)
        // Give the SlidingTabLayout the ViewPager, this must be done AFTER the ViewPager has had
        // it's PagerAdapter set.
        mSlidingTabLayout = view.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);
        // END_INCLUDE (setup_slidingtablayout)
    }
    // END_INCLUDE (fragment_onviewcreated)

    @Override
    public void onSuccess() {
        FragmentLoader.backFragment(getActivity());
    }

    @Override
    public void onError() {

    }

    /**
     * The {@link android.support.v4.view.PagerAdapter} used to display pages in this sample.
     * The individual pages are simple and just display two lines of text. The important section of
     * this class is the {@link #getPageTitle(int)} method which controls what is displayed in the
     * {@link SlidingTabLayout}.
     */
    class SamplePagerAdapter extends PagerAdapter {

        private List<String> titulos = new ArrayList<String>() {
            {
                add("Opinión general");
                add("Temas");
                add("Actualidad");
                add("Nivel teóricas");
                add("Nivel Prácticas");
            }
        };

        /**
         * @return the number of pages to display
         */
        @Override
        public int getCount() {
            return 5;
        }

        /**
         * @return true if the value returned from {@link #instantiateItem(ViewGroup, int)} is the
         * same object as the {@link View} added to the {@link ViewPager}.
         */
        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        // BEGIN_INCLUDE (pageradapter_getpagetitle)
        /**
         * Return the title of the item at {@code position}. This is important as what this method
         * returns is what is displayed in the {@link SlidingTabLayout}.
         * <p>
         * Here we construct one using the position value, but for real application the title should
         * refer to the item's contents.
         */
        @Override
        public CharSequence getPageTitle(int position) {
            //return "P " + (position + 1);
            return titulos.get(position);
        }
        // END_INCLUDE (pageradapter_getpagetitle)

        /**
         * Instantiate the {@link View} which should be displayed at {@code position}. Here we
         * inflate a layout from the apps resources and then change the text view to signify the position.
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            switch (position) {
                case 0: return instantiateOpinionGeneral(container);
                case 1: return instantiateTemas(container);
                case 2: return instantiateActualidad(container);
                case 3: return instantiateNivelTeoricas(container);
                case 4: return instantiateNivelPracticas(container);
                default: return instantiateError(container);
            }
        }

        private View instantiateOpinionGeneral(ViewGroup container) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.encuesta_pager_item,
                    container, false);
            container.addView(view);

            setInfoCurso(view);

            TextView pregunta = view.findViewById(R.id.encuesta_pregunta);
            pregunta.setText(R.string.pregunta_opinion_general);

            addRespuestaNivelGeneral(view,"Excelente", 5);
            addRespuestaNivelGeneral(view,"Muy bueno", 4);
            addRespuestaNivelGeneral(view,"Bueno", 3);
            addRespuestaNivelGeneral(view,"Regular", 2);
            addRespuestaNivelGeneral(view,"Malo", 1);

            return view;
        }

        private View instantiateTemas(ViewGroup container) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.encuesta_pager_item,
                    container, false);
            container.addView(view);

            setInfoCurso(view);

            TextView pregunta = view.findViewById(R.id.encuesta_pregunta);
            pregunta.setText(R.string.pregunta_temas);

            addRespuestaNivelTemas(view, "Muy interesantes", 5);
            addRespuestaNivelTemas(view, "Interesantes", 4);
            addRespuestaNivelTemas(view, "Opinión neutral", 3);
            addRespuestaNivelTemas(view, "Poco interesantes", 2);
            addRespuestaNivelTemas(view, "Nada interesantes", 1);

            return view;
        }

        private View instantiateActualidad(ViewGroup container) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.encuesta_pager_item,
                    container, false);
            container.addView(view);

            setInfoCurso(view);

            TextView pregunta = view.findViewById(R.id.encuesta_pregunta);
            pregunta.setText(R.string.pregunta_actualidad);

            addRespuestaNivelActualizacion(view, "Muy actualizados", 5);
            addRespuestaNivelActualizacion(view, "Actualizados", 4);
            addRespuestaNivelActualizacion(view, "Aun vigentes", 3);
            addRespuestaNivelActualizacion(view, "Poco actualizados", 2);
            addRespuestaNivelActualizacion(view, "Nada actualizados", 1);

            return view;
        }

        private View instantiateNivelTeoricas(ViewGroup container) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.encuesta_pager_item,
                    container, false);
            container.addView(view);

            setInfoCurso(view);

            TextView pregunta = view.findViewById(R.id.encuesta_pregunta);
            pregunta.setText(R.string.pregunta_nivel_teoricas);

            addRespuestaNivelTeoricas(view,"Excelente", 5);
            addRespuestaNivelTeoricas(view,"Muy bueno", 4);
            addRespuestaNivelTeoricas(view,"Bueno", 3);
            addRespuestaNivelTeoricas(view,"Regular", 2);
            addRespuestaNivelTeoricas(view,"Malo", 1);

            return view;
        }

        private View instantiateNivelPracticas(ViewGroup container) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.encuesta_pager_item,
                    container, false);
            container.addView(view);

            setInfoCurso(view);

            TextView pregunta = view.findViewById(R.id.encuesta_pregunta);
            pregunta.setText(R.string.pregunta_nivel_practicas);

            addRespuestaNivelPracticas(view,"Excelente", 5);
            addRespuestaNivelPracticas(view,"Muy bueno", 4);
            addRespuestaNivelPracticas(view,"Bueno", 3);
            addRespuestaNivelPracticas(view,"Regular", 2);
            addRespuestaNivelPracticas(view,"Malo", 1);

            final EditText comentario = view.findViewById(R.id.encuesta_comentario);
            comentario.setVisibility(View.VISIBLE);

            Button botonEnviar = view.findViewById(R.id.enviar_encuesta);
            botonEnviar.setVisibility(View.VISIBLE);
            botonEnviar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (encuesta.getNivelActualizacion() != 0 &&
                            encuesta.getNivelGeneral() != 0 &&
                            encuesta.getNivelTemas() != 0 &&
                            encuesta.getNivelTeoricas() != 0 &&
                            encuesta.getNivelPracticas() != 0) {
                        encuesta.setComentario(comentario.getText().toString());
                        enviarEncuesta();
                    } else
                        Toast.makeText(getContext(), "Completá todas las preguntas antes de enviar la encuesta", Toast.LENGTH_LONG).show();
                }
            });

            return view;
        }

        private View instantiateError(ViewGroup container) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.encuesta_pager_item,
                    container, false);
            container.addView(view);

            TextView pregunta = view.findViewById(R.id.encuesta_pregunta);
            pregunta.setText(R.string.pregunta_error);

            return view;
        }

        /**
         * Destroy the item from the {@link ViewPager}. In our case this is simply removing the
         * {@link View}.
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            /*container.removeView((View) object);
            Log.i(LOG_TAG, "destroyItem() [position: " + position + "]");*/
        }

    }

    private void enviarEncuesta() {
        EncuestaListener listener = new EncuestaListener(getContext(), this);
        RequestSender requestSender = new RequestSender(getContext());

        String url = getContext().getString(R.string.urlAppServer)
                + "encuestas/curso/" + encuestaCurso.getCurso().get_id();

        Gson gson = new Gson();
        try {
            JSONObject jsonObject = new JSONObject(gson.toJson(encuesta));
            requestSender.doPost(listener, url, jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void addRespuestaNivelGeneral(View view, String respuesta, final int nivel) {
        RadioGroup respuestas = view.findViewById(R.id.encuesta_respuestas);

        RadioButton radioButton = new RadioButton(getContext());
        radioButton.setText(respuesta);
        radioButton.setTextSize(18);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                encuesta.setNivelGeneral(nivel);
            }
        });

        respuestas.addView(radioButton);
    }

    private void addRespuestaNivelTemas(View view, String respuesta, final int nivel) {
        RadioGroup respuestas = view.findViewById(R.id.encuesta_respuestas);

        RadioButton radioButton = new RadioButton(getContext());
        radioButton.setText(respuesta);
        radioButton.setTextSize(18);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                encuesta.setNivelTemas(nivel);
            }
        });

        respuestas.addView(radioButton);
    }

    private void addRespuestaNivelActualizacion(View view, String respuesta, final int nivel) {
        RadioGroup respuestas = view.findViewById(R.id.encuesta_respuestas);

        RadioButton radioButton = new RadioButton(getContext());
        radioButton.setText(respuesta);
        radioButton.setTextSize(18);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                encuesta.setNivelActualizacion(nivel);
            }
        });

        respuestas.addView(radioButton);
    }

    private void addRespuestaNivelPracticas(View view, String respuesta, final int nivel) {
        RadioGroup respuestas = view.findViewById(R.id.encuesta_respuestas);

        RadioButton radioButton = new RadioButton(getContext());
        radioButton.setText(respuesta);
        radioButton.setTextSize(18);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                encuesta.setNivelPracticas(nivel);
            }
        });

        respuestas.addView(radioButton);
    }

    private void addRespuestaNivelTeoricas(View view, String respuesta, final int nivel) {
        RadioGroup respuestas = view.findViewById(R.id.encuesta_respuestas);

        RadioButton radioButton = new RadioButton(getContext());
        radioButton.setText(respuesta);
        radioButton.setTextSize(18);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                encuesta.setNivelTeoricas(nivel);
            }
        });

        respuestas.addView(radioButton);
    }

    private void setInfoCurso(View view) {
        TextView codigoMateria = view.findViewById(R.id.encuesta_codigo_materia);
        codigoMateria.setText(encuestaCurso.getMateria().getCodigo());

        TextView nombreMateria = view.findViewById(R.id.encuesta_nombre_materia);
        nombreMateria.setText(encuestaCurso.getMateria().getNombre());

        TextView comision = view.findViewById(R.id.encuesta_numero_curso);
        comision.setText(String.valueOf(encuestaCurso.getComision()));

        TextView docente = view.findViewById(R.id.encuesta_docente_curso);
        docente.setText(encuestaCurso.getCurso().getDocente());
    }
}
