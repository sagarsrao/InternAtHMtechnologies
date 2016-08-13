package com.example.ishumishra97.internathmtechnologies;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int  i;
    private EditText editTextId;
    private Button buttonGet;
    private TextView textViewResult;

    private ProgressDialog loading;
    ArrayList<Model> routesRecord;
    private  RVAdapter rvAdapter;
    RecyclerView rv;
    CardView cardView;
    Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextId = (EditText) findViewById(R.id.editTextId);
        buttonGet = (Button) findViewById(R.id.buttonGet);
        //textViewResult = (TextView) findViewById(R.id.textViewResult);
        rv=(RecyclerView)findViewById(R.id.rv);
        buttonGet.setOnClickListener(this);
    }

    private void getData() {
        String ROUTE_ID = editTextId.getText().toString().trim();
        if (ROUTE_ID.equals("")) {
            Toast.makeText(this, "Please enter an id", Toast.LENGTH_LONG).show();
            return;
        }
        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        String url = Config.DATA_URL+ROUTE_ID;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // loading.dismiss();
                showJSON(response);
                loading.dismiss();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response)//server data
    {
        String name="";
        String class1="";
        String section = "";
        try
        {
            JSONObject jsonObject = new JSONObject(response);
            routesRecord=new ArrayList<Model>();//list

            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            for(i=0;i<=result.length();i++) {

                Model m1=new Model();//create the model Object
                JSONObject collegeData = result.getJSONObject(i);
                name = collegeData.getString(Config.KEY_NAME);
                class1 = collegeData.getString(Config.KEY_CLASS);
                section = collegeData.getString(Config.KEY_SECTION);
                m1.setName(name);//set through model object
                m1.setClasss(class1);
                m1.setSection(section);
                routesRecord.add(m1);

                cardView=(CardView)findViewById(R.id.card_view);
                rv=(RecyclerView)findViewById(R.id.rv);
                Model model=new Model();
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
                rv.setLayoutManager(linearLayoutManager);

                RVAdapter rvAdapter=new RVAdapter(routesRecord);
                rv.setAdapter(rvAdapter);




            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }



  /*      String dataToDisplay="";

        for(int i=0;i<routesRecord.size();i++){
            Model singledata=routesRecord.get(i);

            dataToDisplay=dataToDisplay+" Name:\t"+ singledata.getName()+"\n Class:\t" + singledata.getClasss()
                    +"Section:"+singledata.getSection();
            System.out.println("Data Displaying"+dataToDisplay);
 }*/

        /*rvAdapter = new RVAdapter(routesRecord);
        rv.setAdapter(rvAdapter);*/

       // textViewResult.setText(dataToDisplay);

        /*for (Object s:routesRecord) {
          //  Model singledata=routesRecord.get(i);
            String  dataToDisplay=" Name:\t"+ s.getName()+"\n Class:\t" + singledata.getClasss()
                    +"Section:"+singledata.getSection();
        }*/


        //  textViewResult.setText( " Name:\t"+name+"\n Class:\t" +class1+ " \n Section:\t"+section);
    }

    public void onClick(View v)
    {
        getData();
    }
}