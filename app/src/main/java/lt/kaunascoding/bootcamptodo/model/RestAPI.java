package lt.kaunascoding.bootcamptodo.model;

import android.content.Context;
import android.database.sqlite.SQLiteCantOpenDatabaseException;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

import lt.kaunascoding.bootcamptodo.controller.MainActivity;

/**
 * Created by user on 8/7/2018.
 */

public class RestAPI implements DBCommunication {
    public static final String URL = "http://192.168.0.99:8080";
    public static final String ALL_ITEMS = "/items";
    public static final String DELETE_ITEM = "/items/delete/{id}";
    public static final String UPDATE_ITEM = "/items/edit/{id}";
    public static final String ADD_ITEM = "/items/add";


    private ArrayList<ItemVO> items=null;
    private Context context;
    public RestAPI(Context context){
        items = new ArrayList<>();
        this.context=context;

    }

    @Override
    public ArrayList<ItemVO> getAllItems() {
        RequestQueue queue = Volley.newRequestQueue(this.context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, RestAPI.URL+RestAPI.ALL_ITEMS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Scanner sc = new Scanner(response);
                        int i =0;
                        while(sc.hasNext()){
                            ItemVO itemVO = new ItemVO();
                            itemVO.id=i++;
                            itemVO.done=sc.nextInt();
                            itemVO.title = sc.nextLine();
                            items.add(itemVO);
                        }

                        ((MainActivity)context).itemVOAdapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print(error.getMessage());
            }
        });

        queue.add(stringRequest);
        return items;
    }

    @Override
    public void addItem(String task) {

    }

    @Override
    public void deleteItem(ItemVO itemVO) {

    }

    @Override
    public void updateItem(ItemVO itemVO) {

    }
}
