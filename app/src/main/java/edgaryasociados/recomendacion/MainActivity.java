package edgaryasociados.recomendacion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;


public class MainActivity extends AppCompatActivity {


    //String de url de la pagina
    private static final String pagina="http://ceramicapiga.com/tesis/login.php";

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onLogin(View view) {
        EditText userEditText = (EditText)findViewById(R.id.userEditText);
        EditText passwordEditText = (EditText)findViewById(R.id.passwordEditText);
        // we Check the text is not empty

           new SaveTheFeed().execute();


    }

    protected boolean isEmpty(EditText user, EditText password){

        return(user.getText().toString().equals("") && password.getText().toString().equals(""));


    }

    public void onGotoRegisterActivity(View view) {
        Intent intent =new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }


    class SaveTheFeed extends AsyncTask<Void, Void, Void>{

        String prueba = "chao";
        String jsonString;

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


            EditText userEditText = (EditText)findViewById(R.id.userEditText);
            EditText passwordEditText = (EditText)findViewById(R.id.passwordEditText);


            try {
                URL url = null;
                url = new URL(pagina);
                HttpURLConnection urlConnection =null;
                urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Accept-Encoding", "identity");
                urlConnection.setDoInput(true);
                urlConnection.setDoInput(true);

                /*
                JSONObject jSend = new JSONObject();
                jSend.put("user", "12");
                jSend.put("password", "12"); */
                String jstr="user=12&password=12";

                DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
                wr.writeBytes(jstr.toString());
                wr.flush();
                wr.close();

                if(urlConnection.getResponseCode()==200){
                    BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while((line = br.readLine()) !=null){
                        sb.append(line);
                    }
                    jsonString = sb.toString();

                    prueba=sb.toString();

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            EditText us =(EditText)findViewById(R.id.userEditText);
            us.setText(prueba);
            if(prueba.toString().equals("1")){
                Toast.makeText(getApplication(),"Entro", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplication(),"No Entro", Toast.LENGTH_LONG).show();
            }

        }
    }
}
