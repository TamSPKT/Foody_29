package hcmute.edu.vn.foody_29;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.foody_29.constant.FoodyConstant;
import hcmute.edu.vn.foody_29.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private SharedPreferences sharedPreferences;
    private FoodyDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();

        sharedPreferences = getSharedPreferences(FoodyConstant.PREFERENCES, MODE_PRIVATE);
        database = new FoodyDatabase(this);

        final Button btnLogin = binding.buttonLogin;
        final Button btnSignup = binding.buttonSignupLogin;
        final EditText txtEmail = binding.editTextEmailLogin;
        final EditText txtPassword = binding.editTextPasswordLogin;

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = database.GetData(String.format("SELECT * FROM users WHERE Email='%s' AND Password='%s'",
                        txtEmail.getText().toString(), txtPassword.getText().toString()));
                if (cursor.moveToNext()) {
                    int id = cursor.getInt(0);
                    sharedPreferences.edit()
                            .putInt(FoodyConstant.USER_ID, id)
                            .commit();

                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Email hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        // TODO: Temporary check connection to database
        Cursor cursor = database.GetData("SELECT * FROM users");
        if (cursor.moveToNext()) {
            String email = cursor.getString(1);
            String password = cursor.getString(2);
            txtEmail.setText(email);
            txtPassword.setText(password);
        }

        setContentView(root);
    }
}