package com.reem.ushop.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import com.reem.ushop.R;
import com.reem.ushop.adapter.CategoryAdapter;
import com.reem.ushop.adapter.SubCategoryAdapter;
import com.reem.ushop.data.network.api.MainClient;
import com.reem.ushop.databinding.ActivityMainBinding;
import com.reem.ushop.pojo.Category;
import com.reem.ushop.pojo.ResponseGet;
import com.reem.ushop.pojo.Subcategories;
import com.reem.ushop.ui.fragment.SubCategoryFragment;
import com.reem.ushop.utils.Constant;
import com.reem.ushop.utils.ToolUtils;
import com.reem.ushop.utils.dialog.CustomDialog;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.LinearLayout.VERTICAL;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private CategoryAdapter categoryAdapter;
    private static final String TAG = "MainActivity";
    int mPosition = 0;
    String strFiler;
    Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
        checkInternet();
    }

    private void checkInternet() {
        if (!ToolUtils.isNetworkConnected()){
            Log.e(TAG, "checkInternet: false"  );
            CustomDialog.newInstance(getString(R.string.internet_failed), getString(R.string.msg_internet_dialog), getString(R.string.ok), "").show(getSupportFragmentManager(), "CustomDialogFragment");
        }
    }

    private void initView() {
        initRecycler();
        initEditListeners();
    }

    private void initEditListeners() {
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                SubCategoryFragment.newInstance(s.toString(), category);
//                Bundle bundle=new Bundle();
//                bundle.putString(Constant.STR_FILTER,s.toString());
//                new SubCategoryFragment().setArguments(bundle);
                Log.e(TAG, "afterTextChanged: " + s.toString());
            }
        });
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
                if (category.getSubcategories().size() > 0) {
                    mPosition = position;
                    showFragment(SubCategoryFragment.newInstance(strFiler, category));
                } else {
                    startActivity(new Intent(MainActivity.this, SubCategoryActivity.class).putExtra(Constant.CATEGORY_NAME, category.getName()));
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
        binding.progress.setVisibility(View.VISIBLE);
        Callback<ResponseGet<ArrayList<Category>>> callback = new Callback<ResponseGet<ArrayList<Category>>>() {
            @Override
            public void onResponse(Call<ResponseGet<ArrayList<Category>>> call, Response<ResponseGet<ArrayList<Category>>> response) {
                binding.progress.setVisibility(View.GONE);
                if (response.body().getStatus().equals("OK")) {
                    categoryAdapter.setList(response.body().getData());
                    category = response.body().getData().get(id);
                    showFragment(SubCategoryFragment.newInstance(strFiler, response.body().getData().get(id)));
                }
            }

            @Override
            public void onFailure(Call<ResponseGet<ArrayList<Category>>> call, Throwable t) {
                binding.progress.setVisibility(View.GONE);
                Log.e("failure", t.getMessage(), t);
            }
        };
        MainClient.getInstance().getCats(params).enqueue(callback);
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}