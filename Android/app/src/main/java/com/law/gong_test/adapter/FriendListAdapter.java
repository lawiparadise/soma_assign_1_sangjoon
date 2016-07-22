package com.law.gong_test.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.law.gong_test.R;
import com.law.gong_test.async.MyAsyncCallbackSimple;
import com.law.gong_test.async.MyAsyncExecutor;
import com.law.gong_test.common.Common;
import com.law.gong_test.dto.DTOAll;
import com.law.gong_test.unity.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;


public class FriendListAdapter extends BaseAdapter {

    public static final String TAG = "FriendListAdapter";

    //
    String friendId;
    String[] myFriendList;
    Context cont;
    //
    private ArrayList<DTOAll> list;

    public FriendListAdapter() {
        list = new ArrayList<>();
    }

    public ArrayList<DTOAll> getAddList() {
        return list;
    }

    public void setAddList(ArrayList<DTOAll> addList) {

        this.list = addList;

        for (int i = 0; i < list.size(); i++) {
//            Log.e(TAG, "MY : "+list.get(i).getFriend());
            if (MainActivity.id.equals(list.get(i).getId())) {
                myFriendList = list.get(i).getFriend().split(",");
                Log.e(TAG,"MY : for : "+myFriendList.length);
                //별로 좋은 방법은 아님 안됨.
//                myFriendList = gson.fromJson(list.get(i).getFriend(), String[].class);
/*                for(int j=0 ; j<myFriendList.length ; j++){
                    Log.e(TAG, "MY : for"+String.valueOf(myFriendList[j]));
                }*/


                Log.e(TAG, "MY : if");
                break;
            } else {
                Log.e(TAG, "MY : else");
//                myFriendList = null;
            }
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e(TAG, "MY : getView");
        final int pos = position;
        final Context context = parent.getContext();
        cont = parent.getContext();

        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_friend_item, parent, false);

            viewHolder.layoutBack = (RelativeLayout) convertView.findViewById(R.id.layout_list_back_apart);
            viewHolder.txtId = (TextView) convertView.findViewById(R.id.txt_id);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txt_name);
            viewHolder.btnAdd = (Button) convertView.findViewById(R.id.btn_add_friend);
            viewHolder.imgProfile = (ImageView) convertView.findViewById(R.id.img_profile);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        if (MainActivity.id.equals(list.get(position).getId())) {
            viewHolder.btnAdd.setText("나");
//            Log.e(TAG, "MY : " + position);
        } else {
            for(int i=0 ; i<myFriendList.length ; i++){
                if(myFriendList[i].equals(list.get(position).getId())){
                    viewHolder.aBoolean = false;
                    break;
                } else{
                    viewHolder.aBoolean = true;
                }
            }

            if (viewHolder.aBoolean) {
                viewHolder.btnAdd.setText("친구 추가");
            } else {
                viewHolder.btnAdd.setText("친구");
            }
        }

        final boolean temp = viewHolder.aBoolean;
        final Button button = viewHolder.btnAdd;
        viewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(temp){
                        Log.e(TAG,"MY : pos"+pos);
                        if (!MainActivity.id.equals(list.get(pos).getId())) {
                            button.setText("친구");
                            friendId = list.get(pos).getId();
                            new MyAsyncExecutor<String>((Activity)context).setCallable(first).setCallback(firstBack).execute("true");
                        }
                    }else{
                        Log.e(TAG,"MY : pos"+pos);
                    }
//                Intent i = new Intent(v.getContext(), CaseDetailActivity.class);
//                i.putExtra("case_idx", list.get(pos).getCase_idx());
//                context.startActivity(i);

            }
        });

        viewHolder.txtId.setText(list.get(position).getId());
        viewHolder.txtName.setText(list.get(position).getName());

        //
        Picasso.with(context).load(list.get(position).getImg()).into(viewHolder.imgProfile);

        return convertView;
    }

    Callable<String> first = new Callable<String>() {
        @Override
        public String call() throws Exception {
            Common common = Common.getInstance();
            LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("kind", "addFriend");
            jsonObject.addProperty("id", MainActivity.id);
            jsonObject.addProperty("friend",friendId);

            return common.connect(Common.MAIN_URL, jsonObject);
        }
    };

    MyAsyncCallbackSimple<String> firstBack = new MyAsyncCallbackSimple<String>(){
        @Override
        public void onResult(String result) {
            if(result.equals("fail")){
                Toast.makeText(cont, "fail", Toast.LENGTH_SHORT).show();
                Log.e("callbackSimple", "connect fail");
                return;
            } else {
                Toast.makeText(cont, "친구 추가 되었습니다.", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    };

    public class ViewHolder {
        RelativeLayout layoutBack;
        TextView txtId;
        TextView txtName;
        Button btnAdd;
        ImageView imgProfile;
        boolean aBoolean;
    }
}
