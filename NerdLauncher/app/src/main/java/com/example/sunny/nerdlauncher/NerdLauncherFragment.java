package com.example.sunny.nerdlauncher;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NerdLauncherFragment extends Fragment {

    private static  final  String TAG = "NerdLauncherFragment";

    private RecyclerView myRecyclerView;

    public  static  NerdLauncherFragment newInstance() {
        return new NerdLauncherFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nerd_launcher,container,false);
        myRecyclerView = (RecyclerView)view.findViewById(R.id.fragemnt_nerd_launcher_recycler_view);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setAdapter();
        return  view;

    }

    private void setAdapter() {
        Intent startupdIntent = new Intent(Intent.ACTION_MAIN);
        startupdIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        PackageManager pm = getActivity().getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(startupdIntent,0);
        Log.i(TAG, "Found " + activities.size() + " activities");


        Collections.sort(activities, new Comparator<ResolveInfo>(){
            @Override
            public int compare(ResolveInfo lhs, ResolveInfo rhs) {
                PackageManager pm = getActivity().getPackageManager();
                return String.CASE_INSENSITIVE_ORDER.compare(lhs.loadLabel(pm).toString(), rhs.loadLabel(pm).toString());
            }
        });
        myRecyclerView.setAdapter(new ActivityAdapter(activities));
    }


    private  class ActivityHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        private ResolveInfo mResolveInfo;
        private TextView mNameTextView;
        private ImageView mImageView;

        public  ActivityHolder(View itemView) {
            super(itemView);
            mNameTextView = (TextView) itemView.findViewById(R.id.name_label);
            mImageView = (ImageView) itemView.findViewById(R.id.icon_view);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            ActivityInfo activityInfo = mResolveInfo.activityInfo;
            Intent i = new Intent(Intent.ACTION_MAIN).setClassName(activityInfo.applicationInfo.packageName, activityInfo.name);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }

        public void bindActivity(ResolveInfo resolveInfo) {
            mResolveInfo = resolveInfo;
            PackageManager pm = getActivity().getPackageManager();
            String appName = mResolveInfo.loadLabel(pm).toString();

            mNameTextView.setText(appName);
            mImageView.setImageDrawable(mResolveInfo.loadIcon(pm));
        }
    }

    private class ActivityAdapter extends  RecyclerView.Adapter<ActivityHolder> {
            private final  List<ResolveInfo> mActivities;
            public ActivityAdapter(List<ResolveInfo> activities) {
                mActivities = activities;
            }

        @Override
        public ActivityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_layout,parent, false);
            return new ActivityHolder(view);
        }

        @Override
        public void onBindViewHolder(ActivityHolder holder, int position) {
            ResolveInfo info = mActivities.get(position);
            holder.bindActivity(info);
        }

        @Override
        public int getItemCount() {
            return mActivities.size();
        }
    }

}
