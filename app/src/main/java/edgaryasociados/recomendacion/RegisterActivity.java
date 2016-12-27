package edgaryasociados.recomendacion;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Edgar on 19/12/2016.
 */

public class RegisterActivity extends Activity {

    //String de url de la pagina
    private static final String LOGIN_URL = "http://ceramicapiga.com/tesis/registration.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }

    public void onRegistrar(View view) {
        new PostAsync().execute();

    }

    class PostAsync extends AsyncTask<Void, Void, Void> {

        private ProgressDialog pDialog;
        JSONObject json = new JSONObject();
        JSONParser jsonParser = new JSONParser();
        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";

        EditText userEditText = (EditText) findViewById(R.id.userEditText);
        EditText passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        String user = userEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        String salt = "";
        String largePassword = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(RegisterActivity.this);
            pDialog.setMessage("Attempting login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            salt = Textgenerator.generateSalt();
            largePassword = Textgenerator.get_SHA_512_SecurePassword(password, salt);

            try {
                HashMap<String, String> params = new HashMap<>();
                params.put("user", user);
                params.put("email", "elmismo");
                params.put("salt", salt);
                params.put("largepassword", largePassword);

                Log.d("request", "starting");

                json = jsonParser.makeHttpRequest(LOGIN_URL, "POST", params);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }

            if (json != null) {
                Toast.makeText(getApplication(), json.toString(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplication(), "No Entro", Toast.LENGTH_LONG).show();

            }
        }

    }
}
