package com.reem.ushop.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reem.ushop.adapter.SubCategoryAdapter;
import com.reem.ushop.databinding.FragmentSubCategoryBinding;
import com.reem.ushop.pojo.Category;
import com.reem.ushop.pojo.Subcategories;
import com.reem.ushop.ui.activity.SubCategoryActivity;
import com.reem.ushop.utils.Constant;

import java.util.ArrayList;


public class SubCategoryFragment extends Fragment {
   private FragmentSubCategoryBinding binding;
   private View v;
   private SubCategoryAdapter subCategoryAdapter;
   private Category category;
   private String  strFilter;
   private ArrayList<Subcategories> subcatList=new ArrayList<>();
    private static final String TAG = "SubCategoryFragment";

    public SubCategoryFragment() {
    }
    public static SubCategoryFragment newInstance(String strFilter,Category category) {
        SubCategoryFragment fragment = new SubCategoryFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constant.CATEGORY,(Category)  category);
        args.putString(Constant.STR_FILTER,strFilter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category= (Category) getArguments().getSerializable(Constant.CATEGORY);
            strFilter=getArguments().getString(Constant.STR_FILTER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSubCategoryBinding.inflate(inflater, container, false);
        v = binding.getRoot();
        initView();
        filter(strFilter);
        return v;
    }

    private void initView() {
        initRecycler();
    }
    @Override
    public void onResume() {
        subcatList=category.getSubcategories();
        setData(subcatList);
        binding.tvCatName.setText(category.getName());
        super.onResume();
    }
    private void filter(String strFilter) {
        ArrayList<Subcategories> filteredList = new ArrayList<>();
        for ( Subcategories item : subcatList ) {
            Log.e(TAG, "filter: category "+subcatList);
            if (item.getName().toLowerCase().contains(strFilter.toLowerCase())) {
                filteredList.add(item);
            }
            subCategoryAdapter.filterList(filteredList);
        }

    }


    private void setData(ArrayList<Subcategories> subcategory) {
       subCategoryAdapter.setList(subcategory);
       subCategoryAdapter.notifyDataSetChanged();
    }
    private void initRecycler() {
        GridLayoutManager layoutManager =new GridLayoutManager(requireActivity(), 3, GridLayoutManager.VERTICAL, false);
        binding.rvSubCat.setHasFixedSize(true);
        binding.rvSubCat.setLayoutManager(layoutManager);
        subCategoryAdapter=new SubCategoryAdapter(requireContext());
        binding.rvSubCat.setAdapter(subCategoryAdapter);
        initListeners();
    }

    private void initListeners() {
        subCategoryAdapter.setOnItemClickListener(new SubCategoryAdapter.OnItemClickListener() {
            @Override
            public void onClicked(int position, Subcategories subcategory) {
                if (subcategory.getSubcategories().size()>0){
                   setData(subcategory.getSubcategories());
                }else {
                    startActivity(new Intent(requireContext(),SubCategoryActivity.class).putExtra(Constant.CATEGORY_NAME,subcategory.getName()));
                }
            }
        });
    }
}