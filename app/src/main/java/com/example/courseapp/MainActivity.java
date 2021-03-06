package com.example.courseapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4,ed5;
    AppCompatButton b1;
String getTitle,getDescrip,getVenue,getdate,getdura;
String apiurl= "https://mountzioncollege.herokuapp.com/addcourse";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1 = (EditText) findViewById(R.id.crstitle);
        ed2 = (EditText) findViewById(R.id.crsdescrip);
        ed3 = (EditText) findViewById(R.id.vnue);
        ed4 = (EditText) findViewById(R.id.crsdate);
        ed5 = (EditText) findViewById(R.id.crsdura);
        b1 = (AppCompatButton) findViewById(R.id.sub);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTitle = ed1.getText().toString();
                getDescrip = ed2.getText().toString();
                getVenue = ed3.getText().toString();
                getdate = ed4.getText().toString();
                getdura = ed5.getText().toString();
                StringRequest sr=new StringRequest(Request.Method.POST,
                        apiurl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
                {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> params=new HashMap<>();
                        params.put("courseDuration",getdura);
                        params.put("courseDescription",getDescrip);
                        params.put("courseVenue",getVenue);
                        params.put("courseTitle",getTitle);
                        params.put("courseDate",getdate);
                        return params;
                    }
                };
                RequestQueue rq= Volley.newRequestQueue(getApplicationContext());
                rq.add(sr);

            }
        });

    }
}