package com.example.mubeen.law;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.mubeen.law.DATA_ADOPTER.SUB_DATA;
import com.example.mubeen.law.RECYCLER_VIEWS_ADAOPTER.Recycler_view_for_sub_cat;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.mubeen.law.IMPORTANT_VALUES.ip;

public class Sub_Categaries extends AppCompatActivity {

    RecyclerView rv_for_sub_cat;
    ArrayList<SUB_DATA> SUB_DATA_LIST;
    Intent super_key;
    String SUPER_KEY_TEXT_FROM_INTENT;
   // String SUPER_PIC_FROM_INTENT;

    ImageView SUPER_PIC_FROM_INTENT;
    TextView SUPER_TEXT_TITTLE_FROM_INTENT;



    String SERVER_IP_FOR_SUB_CAT = "http://"+ip+"/LAW/sub_cat_getter.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_categaries);

        super_key = getIntent();

        SUPER_KEY_TEXT_FROM_INTENT = super_key.getStringExtra("SUPER_KEY");
       // Toast.makeText(this, SUPER_KEY_TEXT_FROM_INTENT, Toast.LENGTH_SHORT).show();

        //image initionalization for the image
        SUPER_PIC_FROM_INTENT = findViewById(R.id.custom_main_cat_img_in_sub_cat);
        SUPER_TEXT_TITTLE_FROM_INTENT = findViewById(R.id.custom_main_cat_tittle_in_sub_Cat);


        Picasso.get().load(super_key.getStringExtra("SUPER_IMG").toString()).resize(200,100).into(SUPER_PIC_FROM_INTENT);
        SUPER_TEXT_TITTLE_FROM_INTENT.setText(SUPER_KEY_TEXT_FROM_INTENT);

        SUB_DATA_LIST = new ArrayList<>();
        rv_for_sub_cat =findViewById(R.id.recyclerView_for_sub_Cat);
        final Recycler_view_for_sub_cat recycler_view_for_sub_cat = new Recycler_view_for_sub_cat(this,SUB_DATA_LIST);
        final RecyclerView.LayoutManager  adapter = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rv_for_sub_cat.setLayoutManager(adapter);

        StringRequest stringRequest_for_sub = new StringRequest(Request.Method.POST, SERVER_IP_FOR_SUB_CAT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //Toast.makeText(Sub_Categaries.this, response+  "mm", Toast.LENGTH_SHORT).show();

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            JSONObject jsonObject = null;

                            for (int i=0;i<jsonArray.length();i++)
                            {
                                jsonObject = jsonArray.getJSONObject(i);
                                String LAW_SUB_TYPE_ID = jsonObject.getString("SUB_ID");
                                String LAW_SUB_TYPE = jsonObject.getString("sub_law_name");
                                String LAW_SUB_TYPE_RESON = jsonObject.getString("reasons");
                                String LAW_SUB_TYPE_EXAMPLE = jsonObject.getString("example");

                                SUB_DATA sub_data = new SUB_DATA(LAW_SUB_TYPE_ID,LAW_SUB_TYPE,LAW_SUB_TYPE_RESON,LAW_SUB_TYPE_EXAMPLE);
                                Log.d("trytry", "onResponse: " + sub_data);
                                SUB_DATA_LIST.add(sub_data);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        rv_for_sub_cat.setAdapter(recycler_view_for_sub_cat);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Sub_Categaries.this, "sub " + error, Toast.LENGTH_SHORT).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> prams = new HashMap<>();

                prams.put("LAW_SUPER_TYPE", SUPER_KEY_TEXT_FROM_INTENT);



                //prams.put("mobile",mobile);
                return prams;
            }

        };

        MySingleton.getInstance(this).AddToRequestQue(stringRequest_for_sub);


    }
}
