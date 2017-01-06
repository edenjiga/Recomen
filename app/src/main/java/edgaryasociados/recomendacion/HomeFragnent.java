package edgaryasociados.recomendacion;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragnent extends Fragment {


    Button button=null;

    public HomeFragnent() {
        // Required empty public constructor
    }

    //EJEMPLO PARA EDGAR CON CARIÑO <3 DE COMO UTILIZAR BOTONES EN FRAGMENTS
    @Override
    //METODO ONCREATE DE TODA CLASE JAVA ANDROID
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /*LA CLASE FRAGMENTS NO TIENE EL METODO findViewById PERO LA CLASE VIEW SI, ASI QUE
        * VOLVEMOS EL FRAGMENT UN VIEW Y AL DE TODO DEVOLVEMOS ESE VIEW QUE TIENE LOS CAMBIOS Q REALIZAMOS*/
        View view = inflater.inflate(R.layout.fragment_home_fragnent, container, false);

        //CREAMOS EL BOTTON Y LOS BUSCAMOS EN EL XML
        Button button = (Button) view.findViewById(R.id.main_button);
        //UTILIZAMOS EL METODO setOnClickListener PARA DARLE FUNCION AL BOTON Y LISTO :D GG
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Snackbar.make(view, "tu mama", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // SE MANDA LA VISTA CON LAS MODIFICACIONES
            return view;
    }


   /* public void nuevo(View view){
        button = (Button) getView().findViewById(R.id.main_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Holiwis", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }*/



}
