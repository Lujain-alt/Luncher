package com.example.luncherch24;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LancherAdapter extends RecyclerView.Adapter<LancherAdapter.LancherHolder> {
    public LancherAdapter(List<ResolveInfo> resolveInfos, Context context) {
        this.resolveInfos = resolveInfos;
        this.context = context;
    }

    List<ResolveInfo>resolveInfos;
    Context context;
    @NonNull
    @Override
    public LancherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LancherHolder(LayoutInflater.from(context).inflate(R.layout.template,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LancherHolder holder, int position) {


        holder.bind(resolveInfos.get(position));

    }

    @Override
    public int getItemCount() {
        return resolveInfos.size();
    }

    class LancherHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textApp;
        ImageView imgApp;
        ResolveInfo mItem;

        public LancherHolder(@NonNull View itemView) {
            super(itemView);

            textApp=itemView.findViewById(R.id.textApp);
            imgApp=itemView.findViewById(R.id.imgApp);
            itemView.setOnClickListener( this);
        }


        public void bind(ResolveInfo resolveInfos)
        {
            mItem=resolveInfos;

            PackageManager pm =context.getPackageManager();
            String appName = resolveInfos.loadLabel(pm).toString();
            Drawable appIcon=resolveInfos.loadIcon(pm);
            textApp.setText(appName);
           imgApp.setImageDrawable(appIcon);


        }

        @Override
        public void onClick(View view) {
            ActivityInfo info=mItem.activityInfo;
            Intent i = new Intent(Intent.ACTION_MAIN)
                    .setClassName(info.applicationInfo.packageName,
                            info.name)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }


}
