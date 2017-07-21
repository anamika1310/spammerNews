package com.blogspot.addictioncodes.news;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Context.CONNECTIVITY_SERVICE;


public class technologyFrag extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<news>> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBE
    private String USGS = " https://newsapi.org/v1/articles?source=engadget&sortBy=top&apiKey=7dcbb267cb394400b6b6170c0a694932";

    public technologyFrag() {
        // Required empty public constructor
    }

    private View v;
    private NewsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.common, container, false);
        // Inflate the layout for this fragmen
        ConnectivityManager conm = (ConnectivityManager) getActivity().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            getLoaderManager().initLoader(1, null, this);
        }
        else{
            ProgressBar pp=(ProgressBar) v.findViewById(R.id.progress);
            pp.setVisibility(View.GONE);
            TextView textView=(TextView) v.findViewById(R.id.check);
            textView.setVisibility(View.VISIBLE);
        }
        return v;
    }

    @Override
    public Loader<ArrayList<news>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(getContext(), USGS);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<news>> loader, final ArrayList<news> data) {
        ProgressBar pp = (ProgressBar) v.findViewById(R.id.progress);
        pp.setVisibility(View.GONE);
        adapter = new NewsAdapter(getActivity(), data);
        ListView lt = (ListView) v.findViewById(R.id.list);
        lt.setAdapter(adapter);
        lt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),Article.class);
                news n=data.get(position);
                intent.putExtra("Image",n.getImg());
                intent.putExtra("heading",n.getSt());
                intent.putExtra("description",n.getSt1());
                intent.putExtra("web",n.getUrl());
                intent.putExtra("Date",n.getDa());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onLoaderReset(Loader  loader) {
        adapter.clear();
    }
}
