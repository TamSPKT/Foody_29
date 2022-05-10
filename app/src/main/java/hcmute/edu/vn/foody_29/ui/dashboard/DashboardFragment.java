package hcmute.edu.vn.foody_29.ui.dashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import hcmute.edu.vn.foody_29.FoodyDatabase;
import hcmute.edu.vn.foody_29.R;
import hcmute.edu.vn.foody_29.adapter.OrderAdapter;
import hcmute.edu.vn.foody_29.constant.FoodyConstant;
import hcmute.edu.vn.foody_29.databinding.FragmentDashboardBinding;
import hcmute.edu.vn.foody_29.model.Order;

public class DashboardFragment extends Fragment {
    private FragmentDashboardBinding binding;
    private SharedPreferences sharedPreferences;
    private FoodyDatabase database;
    private OrderAdapter adapter;
    private ArrayList<Order> orderArrayList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedPreferences = getActivity().getSharedPreferences(FoodyConstant.PREFERENCES, Context.MODE_PRIVATE);

        final ListView listView = binding.listDashboard;
        adapter = new OrderAdapter(getActivity(), R.layout.row_order, orderArrayList);
        listView.setAdapter(adapter);

        database = new FoodyDatabase(getContext());
        GetOrderData(sharedPreferences.getInt(FoodyConstant.USER_ID, 1));

//        final TextView textView = binding.textDashboard;
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void GetOrderData(int userId) {
        orderArrayList.clear();
        Cursor cursor = database.GetData(String.format("SELECT * FROM orders WHERE UserId=%d", userId));
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int uId = cursor.getInt(1);
            int productId = cursor.getInt(2);
            int quantity = cursor.getInt(3);
            int totalCost = cursor.getInt(4);
            String orderDate = cursor.getString(5);
            String address = cursor.getString(6);
            String phone = cursor.getString(7);
            Cursor productCursor = database.GetData(String.format("SELECT * FROM products WHERE Id=%d", productId));
            String productName = "";
            int imageSrc = 700001;
            if (productCursor.moveToNext()) {
                productName = productCursor.getString(2);
                imageSrc = productCursor.getInt(4);
            }
            orderArrayList.add(new Order(id, uId, productId, quantity, address, phone, productName, imageSrc, totalCost, orderDate));
        }
        adapter.notifyDataSetChanged();
    }
}