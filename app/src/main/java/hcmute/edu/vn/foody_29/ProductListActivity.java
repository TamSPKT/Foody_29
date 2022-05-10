package hcmute.edu.vn.foody_29;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import hcmute.edu.vn.foody_29.adapter.ProductAdapter;
import hcmute.edu.vn.foody_29.constant.FoodyConstant;
import hcmute.edu.vn.foody_29.databinding.ActivityProductListBinding;
import hcmute.edu.vn.foody_29.model.Product;
import hcmute.edu.vn.foody_29.model.Restaurant;

public class ProductListActivity extends AppCompatActivity {
    private ActivityProductListBinding binding;
    private SharedPreferences sharedPreferences;
    private FoodyDatabase database;
    private ProductAdapter adapter;
    private ArrayList<Product> productArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_product_list);
        binding = ActivityProductListBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();

        sharedPreferences = getSharedPreferences(FoodyConstant.PREFERENCES, MODE_PRIVATE);

        final ListView listView = binding.listProduct;
        adapter = new ProductAdapter(this, R.layout.row_product, productArrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product product = (Product) adapterView.getItemAtPosition(i);
                sharedPreferences.edit()
                        .putInt(FoodyConstant.PRODUCT_ID, product.getId())
                        .commit();

                Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                startActivity(intent);
            }
        });

        database = new FoodyDatabase(this);
        GetProductData(sharedPreferences.getInt(FoodyConstant.RESTAURANT_ID, 1));

        setContentView(root);
    }

    private void GetProductData(int restaurantId) {
        productArrayList.clear();
        Cursor cursor = database.GetData(String.format("SELECT * FROM products WHERE RestaurantId=%d", restaurantId));
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(2);
            int cost = cursor.getInt(3);
            int imageSrc = cursor.getInt(4);
            productArrayList.add(new Product(id, name, cost, imageSrc));
        }
        adapter.notifyDataSetChanged();
    }
}