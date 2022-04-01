package com.example.mubeen.law;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.mubeen.law.DATA_ADOPTER.SUPER_DATA;
import com.example.mubeen.law.RECYCLER_VIEWS_ADAOPTER.Recyler_view_for_main_cat;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.mubeen.law.IMPORTANT_VALUES.ip;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String SERVER_IP = "http://"+ip+"/LAW/super_cat_getter.php";

    RecyclerView MAIN_CAT_RV;

    ArrayList<SUPER_DATA> super_cat_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super_cat_list = new ArrayList<>();

        MAIN_CAT_RV = findViewById(R.id.main_Cat_x_recycler);
        final Recyler_view_for_main_cat RV = new Recyler_view_for_main_cat(super_cat_list,MainActivity.this);
        RecyclerView.LayoutManager main_layout_of_cat = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
        MAIN_CAT_RV.setLayoutManager(main_layout_of_cat);



        StringRequest super_data_getter = new StringRequest(Request.Method.POST, SERVER_IP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                        Log.d("moon", "onResponse: "+response);
                        try {
                            JSONArray jsonArray_super_cat = new JSONArray(response);
                            JSONObject jsonObject_single_value = null;
                            for (int i=0;i<jsonArray_super_cat.length();i++)
                            {
                                jsonObject_single_value = jsonArray_super_cat.getJSONObject(i);
                                String super_id_json = jsonObject_single_value.getString("ID");
                                String super_cat_of_json = jsonObject_single_value.getString("CRIME");
                                String super_cat_img_json = jsonObject_single_value.getString("img");
                                Log.d("trytry ", "onResponse: " + super_id_json + " " +super_cat_of_json + " " + super_cat_img_json + " "  );

                                SUPER_DATA super_data = new SUPER_DATA(super_id_json,super_cat_of_json,super_cat_img_json);
                                super_cat_list.add(super_data);


                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        MAIN_CAT_RV.setAdapter(RV);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                Map<String, String> prams = new HashMap<>();
//
//                prams.put("user_id", "s");
//
//
//
//                //prams.put("mobile",mobile);
//                return prams;
//            }
//
//        };
        MySingleton.getInstance(MainActivity.this).AddToRequestQue(super_data_getter);






        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
