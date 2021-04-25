package com.reem.ushop.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.widget.Toast;

import com.reem.ushop.R;
import com.reem.ushop.adapter.CategoryAdapter;
import com.reem.ushop.data.network.api.MainClient;
import com.reem.ushop.databinding.ActivityMainBinding;
import com.reem.ushop.pojo.Category;
import com.reem.ushop.pojo.ResponseGet;
import com.reem.ushop.pojo.Subcategories;
import com.reem.ushop.ui.fragment.SubCategoryFragment;
import com.reem.ushop.utils.ToolUtils;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static android.widget.LinearLayout.VERTICAL;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ArrayList<Category> categoryList = new ArrayList<>();
    private ArrayList<Subcategories> subCatList = new ArrayList<>();
    private CategoryAdapter categoryAdapter;
    private int currentPosition = 0;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        buildRecy();
        showFragment(SubCategoryFragment.newInstance(subCatList));
    }
    private void buildRecy() {

        binding.rvCategory.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        categoryAdapter = new CategoryAdapter(MainActivity.this);
        DividerItemDecoration itemDecor = new DividerItemDecoration(getApplicationContext(), VERTICAL);
        binding.rvCategory.addItemDecoration(itemDecor);
        binding.rvCategory.setAdapter(categoryAdapter);
        categoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onClicked(int position, Category category) {
                showFragment(SubCategoryFragment.newInstance(category.getSubcategories()));
            }
        });

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
                if (response.body().getStatus().equals("OK")){
                    Log.e(TAG, "onResponse: data"+response.body().getData()+"" );
                    categoryAdapter.setList(response.body().getData());
                    subCatList=response.body().getData().get(0).getSubcategories();
                    Log.e(TAG, "onResponse: sub cat"+subCatList );
                }
                Toast.makeText(MainActivity.this,response.toString()+"",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<ResponseGet<ArrayList<Category>>> call, Throwable t) {
                Log.e("failure", t.getMessage(), t);
            }
        };
        MainClient.getInstance().getCats(params).enqueue(callback);
    }
    private void showFragment( Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.container, fragment)
                .commitAllowingStateLoss();
    }
}