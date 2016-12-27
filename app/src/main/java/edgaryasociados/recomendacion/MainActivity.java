package edgaryasociados.recomendacion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;


public class MainActivity extends AppCompatActivity {


    //String de url de la pagina
    private static final String LOGIN_URL="http://ceramicapiga.com/tesis/login.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onLogin(View view) {

           new PostAsync().execute();


    }

    protected boolean isEmpty(EditText user, EditText password){

        return(user.getText().toString().equals("") && password.getText().toString().equals(""));


    }

    public void onGotoRegisterActivity(View view) {
        Intent intent =new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }


    class PostAsync extends AsyncTask<Void, Void, Void>{

        private ProgressDialog pDialog;
        JSONObject json = new JSONObject();
        JSONParser jsonParser = new JSONParser();
        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";


        EditText userEditText = (EditText)findViewById(R.id.userEditText);
        EditText passwordEditText = (EditText)findViewById(R.id.passwordEditText);
        String user=userEditText.getText().toString();
        String password= passwordEditText.getText().toString();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Attempting login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                HashMap<String, String> params = new HashMap<>();
                params.put("user", user);
                params.put("password", password);

                Log.d("request", "starting");

                json = jsonParser.makeHttpRequest(LOGIN_URL, "POST", params);

            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }



        @Override
        protected void onPostExecute(Void aVoid) {

            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }


                try {
                    String salt = json.getString("salt");
                    String largepassword = json.getString("largepassword");
                    String a = Textgenerator.get_SHA_512_SecurePassword(password, salt);

                    if(a.equals(largepassword)) {
                        Toast.makeText(getApplication(),"Funciono", Toast.LENGTH_LONG).show();

                    }else{
                        Toast.makeText(getApplication(),"No funciono", Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


        }
    }
}
