package sg.edu.np.s10179564k.mad_practical11;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView NewUser = findViewById(R.id.NewUser);

        NewUser.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(MainActivity.this,CreateNewUser.class);
                startActivity(intent);
                return false;
            }
        });
    }

    public void onLogin(View v) {
        EditText User = findViewById(R.id.User);
        EditText Password = findViewById(R.id.Password);

        String txtUser = User.getText().toString();
        String txtPass = Password.getText().toString();

        Pattern userPattern = Pattern.compile("^[A-Za-z0-9]{6,12}$");
        Matcher userMatcher = userPattern.matcher(txtUser);

        Pattern passPattern = Pattern.compile("^(?=.*[A-Z])(?=.*[0-9])(?=.*\\W).*$");
        Matcher passMatcher = passPattern.matcher(txtPass);

        if (userMatcher.matches() && passMatcher.matches()) {
            SharedPreferences sharedPref = getSharedPreferences("MY_GLOBAL_PREFS", MODE_PRIVATE);
            String user = sharedPref.getString("Username", "");
            String pass = sharedPref.getString("Password", "");
            if (txtUser.equals(user) && txtPass.equals(pass)) {
                Toast.makeText(MainActivity.this, "Valid", Toast.LENGTH_LONG).show();

            }

        } else {
            Toast tt = Toast.makeText(MainActivity.this, "Invalid", Toast.LENGTH_LONG);
            tt.show();
        }
    }
}
