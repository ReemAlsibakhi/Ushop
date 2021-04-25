package com.reem.ushop.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.widget.Toast;
import com.reem.ushop.adapter.CategoryAdapter;
import com.reem.ushop.data.network.api.MainClient;
import com.reem.ushop.databinding.ActivityMainBinding;
import com.reem.ushop.pojo.Category;
import com.reem.ushop.pojo.ResponseGet;
import com.reem.ushop.utils.ToolUtils;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ArrayList<Category> categoryList = new ArrayList<>();
    private CategoryAdapter categoryAdapter;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        buildRecy();
    }
    private void buildRecy() {
        binding.rvCategory.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        categoryAdapter = new CategoryAdapter(MainActivity.this);
        binding.rvCategory.setAdapter(categoryAdapter);
    }

    @Override
    protected void onResume() {
        ToolUtils.hideKeyboard(MainActivity.this);
        getCategories();
        super.onResume();
    }
    private void getCategories() {
        ArrayMap<String, Object> params = new ArrayMap<>();
        params.put("server_key", "1539874186");
        params.put("access_token", "edded313b5e8c9b85b6346dbb810a9653b3e53791611576220c743996f7984d9d506c73bb05fd1a973");
        Callback<ResponseGet<ArrayList<Category>>> callback = new Callback<ResponseGet<ArrayList<Category>>>() {
            @Override
            public void onResponse(Call<ResponseGet<ArrayList<Category>>> call, Response<ResponseGet<ArrayList<Category>>> response) {
                Log.e("get category",response.body().getData().size()+"");
                Toast.makeText(MainActivity.this,response.toString()+"",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<ResponseGet<ArrayList<Category>>> call, Throwable t) {
                Log.e("failure", t.getMessage(), t);
            }
        };
        MainClient.getInstance().getCats(params).enqueue(callback);
    }
}