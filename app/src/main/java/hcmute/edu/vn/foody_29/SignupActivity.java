package hcmute.edu.vn.foody_29;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.foody_29.databinding.ActivitySignupBinding;
import hcmute.edu.vn.foody_29.model.User;

public class SignupActivity extends AppCompatActivity {
    private ActivitySignupBinding binding;
    private FoodyDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signup);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();

        database = new FoodyDatabase(this);

        final EditText txtEmail = binding.editTextEmailSignup;
        final EditText txtPass = binding.editTextPasswordSignup;
        final EditText txtPassRe = binding.editTextConfirmPasswordSignup;
        final Button btnConfirm = binding.buttonConfirmSignup;

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString();
                String pass = txtPass.getText().toString();
                String passRe = txtPassRe.getText().toString();
                String message;
                if (email.isEmpty() || pass.isEmpty() || !pass.equals(passRe)) {
                    message = "Thông tin nhập không hợp lệ";
                } else if (CheckDatabase(email)) {
                    message = "Email đã có tài khoản";
                } else {
                    AddUserToDatabase(email, pass);
                    message = "Đăng ký thành công!";
                }
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });

        setContentView(root);
    }

    private boolean CheckDatabase(String email) {
        Cursor cursor = database.GetData(String.format("SELECT * FROM users WHERE Email='%s'", email));
        return !cursor.moveToNext();
    }

    private void AddUserToDatabase(String email, String password) {
        database.QueryData(String.format("INSERT INTO users (Email, Password) VALUES ('%s', '%s')", email, password));
    }
}