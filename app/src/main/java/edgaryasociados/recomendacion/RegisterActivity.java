package edgaryasociados.recomendacion;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Edgar on 19/12/2016.
 */

public class RegisterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }

    public void onRegistrar(View view) {
        EditText userText = (EditText)findViewById(R.id.userEditText);
        EditText passwordText = (EditText)findViewById(R.id.passwordEditText);
        EditText emailText = (EditText)findViewById(R.id.emailAddressEditText);

    }
}
