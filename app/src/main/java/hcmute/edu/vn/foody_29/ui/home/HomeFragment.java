package hcmute.edu.vn.foody_29.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import hcmute.edu.vn.foody_29.FoodyDatabase;
import hcmute.edu.vn.foody_29.HomeActivity;
import hcmute.edu.vn.foody_29.MainActivity;
import hcmute.edu.vn.foody_29.ProductListActivity;
import hcmute.edu.vn.foody_29.R;
import hcmute.edu.vn.foody_29.adapter.RestaurantAdapter;
import hcmute.edu.vn.foody_29.constant.FoodyConstant;
import hcmute.edu.vn.foody_29.databinding.FragmentHomeBinding;
import hcmute.edu.vn.foody_29.model.Restaurant;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private SharedPreferences sharedPreferences;
    private FoodyDatabase database;
    private RestaurantAdapter adapter;
    private ArrayList<Restaurant> restaurantArrayList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedPreferences = getActivity().getSharedPreferences(FoodyConstant.PREFERENCES, Context.MODE_PRIVATE);

        final ListView listView = binding.listHome;
        adapter = new RestaurantAdapter(getActivity(), R.layout.row_restaurant, restaurantArrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Restaurant restaurant = (Restaurant) adapterView.getItemAtPosition(i);

                sharedPreferences.edit()
                        .putInt(FoodyConstant.RESTAURANT_ID, restaurant.getId())
                        .commit();

                Intent intent = new Intent(getActivity(), ProductListActivity.class);
                startActivity(intent);
            }
        });

        database = new FoodyDatabase(getContext());
        GetRestaurantData();
//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void GetRestaurantData() {
        restaurantArrayList.clear();
        Cursor cursor = database.GetData("SELECT * FROM restaurants");
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int imageSrc = cursor.getInt(2);
            restaurantArrayList.add(new Restaurant(id, name, imageSrc));
        }
        adapter.notifyDataSetChanged();
    }
}