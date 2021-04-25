package com.reem.ushop.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import com.reem.ushop.R;
import com.reem.ushop.adapter.CategoryAdapter;
import com.reem.ushop.data.network.api.MainClient;
import com.reem.ushop.databinding.ActivityMainBinding;
import com.reem.ushop.pojo.Category;
import com.reem.ushop.pojo.ResponseGet;
import com.reem.ushop.pojo.Subcategories;
import com.reem.ushop.ui.fragment.SubCategoryFragment;
import com.reem.ushop.utils.Constant;
import com.reem.ushop.utils.ToolUtils;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static android.widget.LinearLayout.VERTICAL;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private CategoryAdapter categoryAdapter;
    private static final String TAG = "MainActivity";
    int mPosition=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        initRecycler();
        initEditListeners();
    }

    private void initEditListeners() {

    }

    private void initRecycler() {
        binding.rvCategory.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        categoryAdapter = new CategoryAdapter(MainActivity.this);
        DividerItemDecoration itemDecor = new DividerItemDecoration(getApplicationContext(), VERTICAL);
        binding.rvCategory.addItemDecoration(itemDecor);
        binding.rvCategory.setAdapter(categoryAdapter);
        initListeners();

    }
    private void initListeners() {
        categoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onClicked(int position, Category category) {
                if (category.getSubcategories().size()>0){
                    mPosition=position;
                    showFragment(SubCategoryFragment.newInstance(category));
                }else {
                    startActivity(new Intent(MainActivity.this,SubCategoryActivity.class).putExtra(Constant.CATEGORY_NAME,category.getName()));
                }
            }
        });
    }
    @Override
    protected void onResume() {
        ToolUtils.hideKeyboard(MainActivity.this);
        getCategories(mPosition);
        super.onResume();
    }
    private void getCategories(int id) {
        ArrayMap<String, Object> params = new ArrayMap<>();
        params.put("server_key", "1539874186");
        params.put("access_token", "edded313b5e8c9b85b6346dbb810a9653b3e53791611576220c743996f7984d9d506c73bb05fd1a973");
        Callback<ResponseGet<ArrayList<Category>>> callback = new Callback<ResponseGet<ArrayList<Category>>>() {
            @Override
            public void onResponse(Call<ResponseGet<ArrayList<Category>>> call, Response<ResponseGet<ArrayList<Category>>> response) {
                if (response.body().getStatus().equals("OK")){
                    categoryAdapter.setList(response.body().getData());
                  showFragment(SubCategoryFragment.newInstance(response.body().getData().get(mPosition)));
                }
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