package hcmute.edu.vn.foody_29;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import hcmute.edu.vn.foody_29.constant.FoodyConstant;
import hcmute.edu.vn.foody_29.databinding.ActivityProductBinding;
import hcmute.edu.vn.foody_29.model.Product;

public class ProductActivity extends AppCompatActivity {
    private ActivityProductBinding binding;
    private SharedPreferences sharedPreferences;
    private FoodyDatabase database;
    private Product product;
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_product);
        binding = ActivityProductBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();

        sharedPreferences = getSharedPreferences(FoodyConstant.PREFERENCES, MODE_PRIVATE);
        database = new FoodyDatabase(this);
        GetProductDetail(sharedPreferences.getInt(FoodyConstant.PRODUCT_ID, 1));

        final ImageView imageView = binding.imageViewProductDetailImage;
        final TextView txtName = binding.textViewProductDetailName;
        final TextView txtCost = binding.textViewProductDetailCost;
        final TextView txtCount = binding.textViewProductDetailCount;
        final Button btnIncrement = binding.buttonProductDetailIncrement;
        final Button btnDecrement = binding.buttonProductDetailDecrement;
        final Button btnSubmit = binding.buttonProductDetailSubmit;

        imageView.setBackgroundResource(product.getImageSrc());
        txtName.setText(product.getName());
        txtCost.setText(String.format("Giá: %dVND", product.getCost()));
        txtCount.setText(String.valueOf(count));
        btnIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                txtCount.setText(String.valueOf(count));
            }
        });
        btnDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count > 1) {
                    count--;
                    txtCount.setText(String.valueOf(count));
                }
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogConfirmOrder(sharedPreferences.getInt(FoodyConstant.USER_ID, 1));
            }
        });

        setContentView(root);
    }

    private void GetProductDetail(int productId) {
        Cursor cursor = database.GetData(String.format("SELECT * FROM products WHERE Id=%d", productId));
        if (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(2);
            int cost = cursor.getInt(3);
            int imageSrc = cursor.getInt(4);
            product = new Product(id, name, cost, imageSrc);
        }
    }

    private void DialogConfirmOrder(int userId) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_confirm_order);

        EditText txtAddress = dialog.findViewById(R.id.editText_dialog_order_address);
        EditText txtPhone = dialog.findViewById(R.id.editText_dialog_order_phone);
        Button btnConfirm = dialog.findViewById(R.id.button_dialog_order_submit);
        Button btnCancel = dialog.findViewById(R.id.button_dialog_order_cancel);

        Cursor userCursor = database.GetData(String.format("SELECT * FROM users WHERE Id=%d", userId));
        if (userCursor.moveToNext()) {
            String address = userCursor.getString(3);
            String phone = userCursor.getString(4);
            txtAddress.setText(address);
            txtPhone.setText(phone);
        }

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = txtAddress.getText().toString();
                String phone = txtPhone.getText().toString();
                int totalCost = product.getCost() * count;
                String orderDate = DateFormat.format("yyyy-MM-dd hh:mm:ss", new java.util.Date()).toString();
                database.QueryData(String.format("" +
                        "INSERT INTO orders (UserId, ProductId, Quantity, TotalCost, OrderDate, Address, PhoneNumber) " +
                        "VALUES (%d, %d, %d, %d, '%s', '%s', '%s')",
                        userId, product.getId(), count, totalCost, orderDate, address, phone));
                Toast.makeText(getApplicationContext(), "Đặt đơn hàng thành công!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}