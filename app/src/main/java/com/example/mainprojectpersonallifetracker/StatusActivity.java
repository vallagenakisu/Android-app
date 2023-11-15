package com.example.mainprojectpersonallifetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.PixelCopy;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StatusActivity extends AppCompatActivity {
    private List<String> mylist;
    private ListView mylistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        mylistview =  findViewById(R.id.listview);
        mylist = new ArrayList<>();
        String url = "https://api.myjson.online/v1/records/c2ad765a-31e0-4864-b431-bb13c3382f0c";


        RequestQueue mQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonObject0 = response.getJSONObject("data");
                            JSONObject jsonObject = jsonObject0.getJSONObject("Expense");

                            JSONObject jsonObject1 = jsonObject.getJSONObject("A");
                            JSONObject jsonObject2 = jsonObject.getJSONObject("B");
                            JSONObject jsonObject3 = jsonObject.getJSONObject("C");
                            JSONObject jsonObject4 = jsonObject.getJSONObject("D");
                            mylist.add(jsonObject1.getString("type"));
                            mylist.add(jsonObject1.getString("amount"));
                            mylist.add(jsonObject1.getString("description"));

                            mylist.add(jsonObject2.getString("type"));
                            mylist.add(jsonObject2.getString("amount"));
                            mylist.add(jsonObject2.getString("description"));
                            mylist.add(jsonObject3.getString("type"));
                            mylist.add(jsonObject3.getString("amount"));
                            mylist.add(jsonObject3.getString("description"));
                            mylist.add(jsonObject4.getString("type"));
                            mylist.add(jsonObject4.getString("amount"));
                            mylist.add(jsonObject4.getString("description"));
//                            for (Iterator<String> it = jsonObject.keys(); it.hasNext(); ) {
//                                String key = it.next();
//                                JSONObject expense = jsonObject.getJSONObject(key);
//                                mylist.add(expense.getString("type"));
//                                mylist.add(String.valueOf(expense.getInt("amount")));
//                                // Optionally, you can add the description as well:
//                                // mylist.add(expense.getString("description"));
//                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(StatusActivity.this, R.layout.listtextview,R.id.text, mylist);
                            mylistview.setAdapter(adapter);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

        };
        mQueue.add(request);
    }
}