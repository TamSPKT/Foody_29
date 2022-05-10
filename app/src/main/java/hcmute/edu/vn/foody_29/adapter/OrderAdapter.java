package hcmute.edu.vn.foody_29.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import java.util.List;

import hcmute.edu.vn.foody_29.R;
import hcmute.edu.vn.foody_29.model.Order;

public class OrderAdapter extends BaseAdapter {
    private FragmentActivity context;
    private int layout;
    private List<Order> orderList;

    public OrderAdapter(FragmentActivity context, int layout, List<Order> orderList) {
        this.context = context;
        this.layout = layout;
        this.orderList = orderList;
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int i) {
        return orderList.get(i);
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
            holder.image = view.findViewById(R.id.imageView_order_product_image);
            holder.txtProductName = view.findViewById(R.id.textView_order_product_name);
            holder.txtQuantity = view.findViewById(R.id.textView_order_quantity);
            holder.txtTotalCost = view.findViewById(R.id.textView_order_total_cost);
            holder.txtDate = view.findViewById(R.id.textView_order_date);
            holder.txtAddress = view.findViewById(R.id.textView_order_address);
            holder.txtPhone = view.findViewById(R.id.textView_order_phone);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Order order = orderList.get(i);
        holder.image.setBackgroundResource(order.getImageSrc());
        holder.txtProductName.setText(order.getProductName());
        holder.txtQuantity.setText(String.format("Số lượng: %d", order.getQuantity()));
        holder.txtTotalCost.setText(String.format("Tổng tiền: %dVND", order.getTotalCost()));
        holder.txtDate.setText(String.format("Ngày đặt: %s", order.getDate()));
        holder.txtAddress.setText(String.format("Địa chỉ nhận: %s", order.getAddress()));
        holder.txtPhone.setText(String.format("Số điện thoại: %s", order.getPhoneNumber()));

        return view;
    }

    private class ViewHolder {
        ImageView image;
        TextView txtProductName, txtQuantity, txtTotalCost, txtDate, txtAddress, txtPhone;
    }
}
