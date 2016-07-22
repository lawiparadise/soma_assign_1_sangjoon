package com.law.gong_test.act;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.law.gong_test.R;
import com.law.gong_test.adapter.FriendListAdapter;
import com.law.gong_test.adapter.PresentListAdapter;
import com.law.gong_test.dto.DTOArray;

public class PresentActivity extends Activity {

    public static final String TAG = "FriendActivity";

    int myFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present);

        Intent intent = getIntent();
        String json = intent.getStringExtra("list");
        Log.e(TAG, "MY : json : " + json);

        Gson gson = new Gson();
        DTOArray dtoArray = gson.fromJson(json, DTOArray.class);


/*        DTOAll[] array = gson.fromJson(json, DTOAll[].class);
        Log.e(TAG, "MY : "+array[0].getName());
        ArrayList<DTOAll> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, array);
        Log.e(TAG, "MY : " + arrayList.get(1).getName());*/

        PresentListAdapter adapter = new PresentListAdapter();
        ListView listView = (ListView) findViewById(R.id.list);
        adapter.setAddList(dtoArray.getList());
        listView.setAdapter(adapter);
    }
}
