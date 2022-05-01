package com.ys_production.xmlparsing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class listAdepter extends ArrayAdapter {
    private final int resource;
    private final LayoutInflater layoutInflater;
    Context context;
    ViewHolder viewHolder;
    feedMain currentXml;
    private List<feedMain> application;

    public listAdepter(Context context, int resource, List<feedMain> application) {
        super(context, resource);
        this.resource = resource;
        this.application = application;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return application.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View view = layoutInflater.inflate(resource,parent,false);
        if (convertView == null) {
            convertView = layoutInflater.inflate(resource, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else viewHolder = (ViewHolder) convertView.getTag();

        currentXml = application.get(position);

        viewHolder.name.setText(currentXml.getName());
        viewHolder.artist.setText(currentXml.getArtist());
        viewHolder.release.setText(currentXml.getReleasedate());
//        com.bumptech.glide.Glide.with(context).load(currentXml.getImageURL()).into(viewHolder.imageView2);
//        com.bumptech.glide.Glide.with(context).load(currentXml.getImageURL()).into(viewHolder.imageView);

        return convertView; //super.getView(position, convertView, parent)
    }

    private class ViewHolder {
        final TextView name;
        final TextView artist;
        final TextView release;
//        final ImageView imageView;
//        final ImageView imageView2;

        ViewHolder(View v) {
            this.name = v.findViewById(R.id.app_name);
            this.artist = v.findViewById(R.id.app_artist);
            this.release = v.findViewById(R.id.app_release);
//            this.imageView = v.findViewById(R.id.app_image);
//            this.imageView2 = v.findViewById(R.id.app_image2);
        }
    }
}
