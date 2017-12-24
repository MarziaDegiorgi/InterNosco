package com.polimi.dilapp.levels;


import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.polimi.dilapp.R;
import com.polimi.dilapp.levels.view.ActivityTwoOne;

import java.util.ArrayList;
import java.util.List;

public class GridViewAdapter extends BaseAdapter {
    private List<Integer> images_id;
    private Context context;

    public GridViewAdapter(Context context, int firstId){
        this.context = context;
        images_id = new ArrayList<>();
        images_id.add(firstId);
    }

    @Override
    public int getCount() {
        return images_id.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    // Convert DP to PX
    // Source: http://stackoverflow.com/a/8490361
    public int dpToPx(int dps) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dps * scale + 0.5f);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;

            // Want the width/height of the items
            // to be 120dp
            int wPixel = dpToPx(95);
            int hPixel = dpToPx(120);

            if (convertView == null) {
                // If convertView is null then inflate the appropriate layout file
                convertView = LayoutInflater.from(context).inflate(R.layout.multiple_item_view, null);
            }

            imageView = (ImageView) convertView.findViewById(R.id.imageGridView);
            // Set height and width constraints for the image view
            imageView.setLayoutParams(new LinearLayout.LayoutParams(wPixel, hPixel));

            // Set the content of the image based on the provided URI
            int imageId = this.images_id.get(position);
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageDrawable(context.getResources().getDrawable(imageId));
            Animation animationWait = AnimationUtils.loadAnimation(context, R.anim.blink);
            imageView.setAnimation(animationWait);
            imageView.startAnimation(animationWait);

            // Image should be cropped towards the center
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            // Set Padding for images
            imageView.setPadding(1, 0, 1, 0);

            // Crop the image to fit within its padding
            imageView.setCropToPadding(true);

            return convertView;
    }

    public void addImageResource(int imageResource){
        this.images_id.add(imageResource);
    }

    public void clearImageResources() {
        this.images_id.clear();
    }

}
