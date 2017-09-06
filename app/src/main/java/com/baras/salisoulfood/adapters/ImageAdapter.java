package com.baras.salisoulfood.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baras.salisoulfood.models.MenuItem;
import com.baras.salisoulfood.R;

import java.util.ArrayList;


public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<? extends MenuItem> items;

    public ImageAdapter(Context c, ArrayList<? extends MenuItem> items) {
        mContext = c;
        this.items = items;
    }

    public int getCount() {
        return items.size();
    }

    public Object getItem(int position)
    {
        return items.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View menuItemView = convertView;

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            menuItemView = layoutInflater.inflate(R.layout.menu_item, null);
            ImageView imageView = (ImageView) menuItemView.findViewById(R.id.grid_item_image);
            TextView textView = (TextView) menuItemView.findViewById(R.id.menu_item_description);
            MenuItem item = items.get(position);

            imageView.setAdjustViewBounds(true);
            imageView.setPadding(8, 8, 8, 8);
            Integer imageID = mContext.getResources().getIdentifier(item.getDrawableName(), "drawable", mContext.getPackageName());
            imageView.setImageResource(imageID);

            textView.setText(item.getName());
        } else {
            menuItemView = convertView;
        }

        return menuItemView;
    }
}
