package hcmute.edu.vn.foody_29.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import hcmute.edu.vn.foody_29.R;
import hcmute.edu.vn.foody_29.model.Product;
import hcmute.edu.vn.foody_29.model.Restaurant;

public class ProductAdapter extends BaseAdapter {
    private AppCompatActivity context;
    private int layout;
    private List<Product> productList;

    public ProductAdapter(AppCompatActivity context, int layout, List<Product> productList) {
        this.context = context;
        this.layout = layout;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int i) {
        return productList.get(i);
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
            holder.image = view.findViewById(R.id.imageView_product);
            holder.txtName = view.findViewById(R.id.textView_product_name);
            holder.txtCost = view.findViewById(R.id.textView_product_cost);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Product product = productList.get(i);
        holder.image.setBackgroundResource(product.getImageSrc());
        holder.txtName.setText(product.getName());
        holder.txtCost.setText(String.format("Giá: %dVND", product.getCost()));

        return view;    }

    private class ViewHolder {
        ImageView image;
        TextView txtName, txtCost;
    }
}
