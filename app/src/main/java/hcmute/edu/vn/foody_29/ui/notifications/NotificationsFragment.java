package hcmute.edu.vn.foody_29.ui.notifications;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import hcmute.edu.vn.foody_29.FoodyDatabase;
import hcmute.edu.vn.foody_29.constant.FoodyConstant;
import hcmute.edu.vn.foody_29.databinding.FragmentNotificationsBinding;
import hcmute.edu.vn.foody_29.model.User;

public class NotificationsFragment extends Fragment {
    private FragmentNotificationsBinding binding;
    private SharedPreferences sharedPreferences;
    private FoodyDatabase database;
    private User user;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedPreferences = getActivity().getSharedPreferences(FoodyConstant.PREFERENCES, MODE_PRIVATE);
        database = new FoodyDatabase(getContext());
        GetUserDetail(sharedPreferences.getInt(FoodyConstant.USER_ID, 1));

        final TextView txtEmail = binding.textViewAccountEmail;
        final EditText txtPass = binding.editTextAccountPassword;
        final EditText txtPassRe = binding.editTextAccountConfirmPassword;
        final Button btnSavePassword = binding.buttonAccountSavePassword;
        final EditText txtAddress = binding.editTextAccountAddress;
        final EditText txtPhone = binding.editTextAccountPhone;
        final Button btnSaveAddressPhone = binding.buttonAccountSaveAddressPhone;

        txtEmail.setText(user.getEmail());
        btnSavePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass = txtPass.getText().toString();
                String passRe = txtPassRe.getText().toString();
                String message;
                if (pass.isEmpty() || !pass.equals(passRe)) {
                    message = "Thông tin nhập không hợp lệ";
                } else if (pass.equals(user.getPassword())) {
                    message = "Trùng với mật khẩu cũ";
                } else {
                    EditUserPassword(user.getId(), pass);
                    message = "Thay đổi mật khẩu thành công!";
                }
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
        btnSaveAddressPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = txtAddress.getText().toString();
                String phone = txtPhone.getText().toString();
                EditUserAddressPhone(user.getId(), address, phone);
                Toast.makeText(getContext(), "Thay đổi thông tin thành công!", Toast.LENGTH_SHORT).show();
            }
        });

//        final TextView textView = binding.textNotifications;
//        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void GetUserDetail(int userId) {
        Cursor cursor = database.GetData(String.format("SELECT * FROM users WHERE Id=%d", userId));
        if (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String email = cursor.getString(1);
            String password = cursor.getString(2);
            String address = cursor.getString(3);
            String phoneNumber = cursor.getString(4);
            user = new User(id, email, password, address, phoneNumber);
        }
    }

    private void EditUserPassword(int userId, String password) {
        database.QueryData(String.format("UPDATE users SET Password='%s' WHERE Id=%d", password, userId));
    }

    private void EditUserAddressPhone(int userId, String address, String phone) {
        database.QueryData(String.format("UPDATE users SET Address='%s', PhoneNumber='%s' WHERE Id=%d", address, phone, userId));
    }
}