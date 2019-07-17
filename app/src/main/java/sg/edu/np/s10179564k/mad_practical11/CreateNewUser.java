package sg.edu.np.s10179564k.mad_practical11;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateNewUser extends AppCompatActivity {
    DbHandler db;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user);
        db = new DbHandler(this, null, null, 1);
    }

    public void onCreate(View v) {
        EditText NewUser = findViewById(R.id.NewUser);
        EditText NewPassword = findViewById(R.id.NewPassword);

        String txtUsername = NewUser.getText().toString();
        String txtPassword = NewPassword.getText().toString();

        Pattern userPattern = Pattern.compile("^[a-zA-Z0-9]{6,12}$");
        Matcher userMatcher = userPattern.matcher(txtUsername);

        Pattern passPattern = Pattern.compile("^(?=.*[0-9])(?=.*[!@#$%^&*+=])(?=.*[a-zA-Z]).{1,}$");
        Matcher passMatcher = passPattern.matcher(txtPassword);

        if (userMatcher.matches() && passMatcher.matches()) {
            SharedPreferences.Editor editor = getSharedPreferences("MY_GLOBAL_PREFS", MODE_PRIVATE).edit();
            editor.putString("Username", txtUsername);
            editor.putString("Password", txtPassword);
            editor.apply();

            Account a = new Account(txtUsername, txtPassword);
            db.addAccount(a);
            Toast tt = Toast.makeText(CreateNewUser.this, "New User Created Successfully.", Toast.LENGTH_LONG);
            tt.show();
        }

        else {
            Toast tt = Toast.makeText(CreateNewUser.this, "Invalid User Creation. Please Try Again.", Toast.LENGTH_LONG);
            tt.show();
        }

        Intent in = new Intent(CreateNewUser.this, MainActivity.class);
        startActivity(in);
    }
    public void onCancel(View v){
        Intent in = new Intent(CreateNewUser.this, MainActivity.class);

        startActivity(in);
    }
}
