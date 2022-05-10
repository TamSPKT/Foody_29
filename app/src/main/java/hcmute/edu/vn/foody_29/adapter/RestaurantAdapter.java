package hcmute.edu.vn.foody_29.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import java.util.List;

import hcmute.edu.vn.foody_29.R;
import hcmute.edu.vn.foody_29.model.Restaurant;

public class RestaurantAdapter extends BaseAdapter {
    private FragmentActivity context;
    private int layout;
    private List<Restaurant> restaurantList;

    public RestaurantAdapter(FragmentActivity context, int layout, List<Restaurant> restaurantList) {
        this.context = context;
        this.layout = layout;
        this.restaurantList = restaurantList;
    }

    @Override
    public int getCount() {
        return restaurantList.size();
    }

    @Override
    public Object getItem(int i) {
        return restaurantList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.image = view.findViewById(R.id.imageView_restaurant);
            holder.txtName = view.findViewById(R.id.textView_restaurant_name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Restaurant restaurant = restaurantList.get(i);
        holder.image.setBackgroundResource(restaurant.getImageSrc());
        holder.txtName.setText(restaurant.getName());

        return view;
    }

    private class ViewHolder {
        ImageView image;
        TextView txtName;
    }
}
