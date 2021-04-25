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
import java.util.ArrayList;


public class SubCategoryFragment extends Fragment {
   private FragmentSubCategoryBinding binding;
   private View v;
   public  static ArrayList<Subcategories> subcategories=new ArrayList<>();
   private SubCategoryAdapter subCategoryAdapter;
   private Category category;
    private static final String TAG = "SubCategoryFragment";

    public SubCategoryFragment() {
    }
    public static SubCategoryFragment newInstance(Category category) {
        SubCategoryFragment fragment = new SubCategoryFragment();
        Bundle args = new Bundle();
        args.putSerializable("category",(Category)  category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            subcategories = getArguments().getParcelableArrayList("sub cat");
            category= (Category) getArguments().getSerializable("category");
            Log.e(TAG, "onCreate: "+category.getName() );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSubCategoryBinding.inflate(inflater, container, false);
        v = binding.getRoot();
        initRecycler();
        return v;
    }

    @Override
    public void onResume() {
        setData(category.getSubcategories());
        binding.tvCatName.setText(category.getName());
        super.onResume();
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
        initListener();
    }

    private void initListener() {
        subCategoryAdapter.setOnItemClickListener(new SubCategoryAdapter.OnItemClickListener() {
            @Override
            public void onClicked(int position, Subcategories subcategory) {
                if (subcategory.getSubcategories().size()>0){
                   setData(subcategory.getSubcategories());
                }else {
                    startActivity(new Intent(requireContext(),SubCategoryActivity.class));
                }
            }
        });
    }
}