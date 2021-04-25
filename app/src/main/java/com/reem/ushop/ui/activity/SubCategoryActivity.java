package com.reem.ushop.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.reem.ushop.databinding.ActivitySubCategoryBinding;
import com.reem.ushop.utils.Constant;


public class SubCategoryActivity extends AppCompatActivity {
    String mCategoryName;
    private ActivitySubCategoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mCategoryName = getIntent().getStringExtra(Constant.CATEGORY_NAME);
        initView();

    }

    private void initView() {
        binding.appbar.tvSubCat.setText(mCategoryName);
        initListeners();
    }

    private void initListeners() {
        binding.appbar.imgBack.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}