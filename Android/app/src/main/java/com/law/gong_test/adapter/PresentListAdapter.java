package com.law.gong_test.adapter;

import android.app.Activity;
import android.content.Context;
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

<<<<<<< HEAD
=======
import com.google.gson.Gson;
>>>>>>> aca1389e2a10a53b910b9a8d4bffb80271c446d5
import com.google.gson.JsonObject;
import com.law.gong_test.R;
import com.law.gong_test.async.MyAsyncCallbackSimple;
import com.law.gong_test.async.MyAsyncExecutor;
import com.law.gong_test.common.Common;
import com.law.gong_test.dto.DTOAll;
<<<<<<< HEAD
=======
import com.law.gong_test.dto.DTOArray;
>>>>>>> aca1389e2a10a53b910b9a8d4bffb80271c446d5
import com.law.gong_test.unity.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;


public class PresentListAdapter extends BaseAdapter {

    public static final String TAG = "PresentListAdapter";

    //
    String friendId;
    String[] myFriendList;
    Context cont;

    //
    DTOAll myDTO;
    //
    private ArrayList<DTOAll> list;

    private ArrayList<DTOAll> friendLIst;

    public PresentListAdapter() {
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
                myDTO = list.get(i);
                myFriendList = list.get(i).getFriend().split(",");
<<<<<<< HEAD
                Log.e(TAG,"MY : for : "+myFriendList.length);
=======
                Log.e(TAG, "MY : for : " + myFriendList.length);
>>>>>>> aca1389e2a10a53b910b9a8d4bffb80271c446d5
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

        friendLIst = new ArrayList<>();
<<<<<<< HEAD
        for(int i=0 ; i<myFriendList.length ; i++){
=======
        for (int i = 0; i < myFriendList.length; i++) {
>>>>>>> aca1389e2a10a53b910b9a8d4bffb80271c446d5
            DTOAll dtoAll = new DTOAll();
            dtoAll.setId(myFriendList[i]);
            friendLIst.add(dtoAll);
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
            convertView = inflater.inflate(R.layout.list_present_item, parent, false);

            viewHolder.layoutBack = (RelativeLayout) convertView.findViewById(R.id.layout_list_back_apart);
            viewHolder.txtId = (TextView) convertView.findViewById(R.id.txt_id);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txt_name);
            viewHolder.btnAdd = (Button) convertView.findViewById(R.id.btn_add_friend);
            viewHolder.imgProfile = (ImageView) convertView.findViewById(R.id.img_profile);
<<<<<<< HEAD
=======
            viewHolder.txtStar = (TextView) convertView.findViewById(R.id.txt_star);
            viewHolder.txtMoney = (TextView) convertView.findViewById(R.id.txt_money);
>>>>>>> aca1389e2a10a53b910b9a8d4bffb80271c446d5

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        if (MainActivity.id.equals(list.get(position).getId())) {
            viewHolder.btnAdd.setText("나");
//            Log.e(TAG, "MY : " + position);
        } else {
            viewHolder.btnAdd.setText("선물하기");

            final Button button = viewHolder.btnAdd;
            viewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Integer.parseInt(myDTO.getMoney()) > 1000) {
                        Log.e(TAG, "MY : pos" + pos);
                        if (!MainActivity.id.equals(list.get(pos).getId())) {
                            friendId = list.get(pos).getId();
<<<<<<< HEAD
=======
                            Log.e(TAG, "MY : friendId : " + friendId);
>>>>>>> aca1389e2a10a53b910b9a8d4bffb80271c446d5
                            new MyAsyncExecutor<String>((Activity) context).setCallable(first).setCallback(firstBack).execute("true");
                        }
                    } else {
                        Log.e(TAG, "MY : pos" + pos);
                        Toast.makeText(context, "돈이 없습니다.", Toast.LENGTH_SHORT).show();
                    }

//                Intent i = new Intent(v.getContext(), CaseDetailActivity.class);
//                i.putExtra("case_idx", list.get(pos).getCase_idx());
//                context.startActivity(i);

                }
            });
        }


<<<<<<< HEAD


        viewHolder.txtId.setText(list.get(position).getId());
        viewHolder.txtName.setText(list.get(position).getName());

=======
        viewHolder.txtId.setText(list.get(position).getId());
        viewHolder.txtName.setText(list.get(position).getName());

        int j=0;
        String[] str = list.get(position).getItem().split(",");
        for(int i=0 ; i<str.length ; i++){
            if(String.valueOf(str[i]).equals("star")){
                j++;
            }
        }
        viewHolder.txtStar.setText("별 : "+j);
        j=0;

        viewHolder.txtMoney.setText(list.get(position).getMoney());

>>>>>>> aca1389e2a10a53b910b9a8d4bffb80271c446d5
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
            jsonObject.addProperty("kind", "present");
            jsonObject.addProperty("id", MainActivity.id);
<<<<<<< HEAD
            jsonObject.addProperty("friend",friendId);
=======
            jsonObject.addProperty("friend", friendId);
            Log.e(TAG, "MY : friendId in call : " + friendId);
>>>>>>> aca1389e2a10a53b910b9a8d4bffb80271c446d5

            return common.connect(Common.MAIN_URL, jsonObject);
        }
    };

<<<<<<< HEAD
    MyAsyncCallbackSimple<String> firstBack = new MyAsyncCallbackSimple<String>(){
        @Override
        public void onResult(String result) {
            if(result.equals("fail")){
=======
    MyAsyncCallbackSimple<String> firstBack = new MyAsyncCallbackSimple<String>() {
        @Override
        public void onResult(String result) {
            if (result.equals("fail")) {
>>>>>>> aca1389e2a10a53b910b9a8d4bffb80271c446d5
                Toast.makeText(cont, "fail", Toast.LENGTH_SHORT).show();
                Log.e("callbackSimple", "connect fail");
                return;
            } else {
                Toast.makeText(cont, "선물 완료 되었습니다.", Toast.LENGTH_SHORT).show();
<<<<<<< HEAD
=======
                Gson gson = new Gson();
                DTOArray dtoArray = gson.fromJson(result, DTOArray.class);
                list = dtoArray.getList();
                notifyDataSetChanged();
>>>>>>> aca1389e2a10a53b910b9a8d4bffb80271c446d5
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
<<<<<<< HEAD
=======

        TextView txtStar;
        TextView txtMoney;
>>>>>>> aca1389e2a10a53b910b9a8d4bffb80271c446d5
    }
}
