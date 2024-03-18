package com.example.hiringdisplay;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import com.example.hiringdisplay.databinding.ActivityScrollingBinding;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    private final static String hiringUrl = "https://fetch-hiring.s3.amazonaws.com/hiring.json";
    String filename;
    private ActivityScrollingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityScrollingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());


        FloatingActionButton fab = binding.fab;


        // get the file name
        String[] tmpStrings = hiringUrl.split("/");
        filename = tmpStrings[tmpStrings.length-1];
        long downloadID = setDownTask();

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                //Checking if the received broadcast is for our Enqueued download by matching download id
                if (downloadID == id) {
                    Toast.makeText(MainActivity.this, "Download Completed", Toast.LENGTH_SHORT).show();
                    HiringAdapter adapter = new HiringAdapter(sortCleanData());
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.hiring_view);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                }
            }
        }, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE), Context.RECEIVER_NOT_EXPORTED);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Features to be added", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private long setDownTask() {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(hiringUrl));
        request.setTitle("Download");
        request.setDescription("Downloading");

        // in order for this if to run, you must use the android 3.2 to compile your app
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);


        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename);

        // get download service and enqueue file
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        return manager.enqueue(request);
    }
    private ArrayList<HiringItem> sortCleanData() {
        JSONArray jsonArray;
        ArrayList<HiringItemList> sortData = new ArrayList<>();
        ArrayList<HiringItem> returnData = new ArrayList<>();
        try {
            HashMap<Integer, HiringItemList> data = new HashMap<>();
            BufferedReader reader = new BufferedReader(new FileReader(
                    new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), filename)));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + '\n');
            }
            jsonArray = new JSONArray(sb.toString());
            for(int i=0; i<jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                int listId = jsonObject.getInt("listId");
                String name = jsonObject.optString("name");
                if (name.isEmpty() || name.equals("null")) {
                    continue;
                }
                if (!data.containsKey(listId)) {
                    HiringItemList list = new HiringItemList(listId);
                    data.put(listId, list);
                }
                data.get(listId).addItem(new HiringItem(listId, id, name));
            }
            for (Integer listId: data.keySet()) {
                data.get(listId).sort();
                sortData.add(data.get(listId));
            }
            sortData.sort((l1, l2) -> l1.getListID() - l2.getListID());

            for (HiringItemList finalDatum : sortData) {
                ArrayList<HiringItem> list = finalDatum.getList();
                returnData.addAll(list);
//                for (HiringItem hiringItem : list) {
//                    Log.d("Hiring", finalDatum.getListID() + " " +
//                            hiringItem.getID() + " " + hiringItem.getName());
//                }
            }
        } catch (Exception e) {
            Log.e("Hiring", e.toString());
        }
        return returnData;
    }


    public void displayData() {

    }

}

