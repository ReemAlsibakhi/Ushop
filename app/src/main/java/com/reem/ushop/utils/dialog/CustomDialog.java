package com.reem.ushop.utils.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.fragment.app.DialogFragment;
import com.reem.ushop.databinding.FragmentCustomDialogBinding;


public class CustomDialog extends DialogFragment {
    private FragmentCustomDialogBinding binding;
    private String title;
    private String msg;
    private String okMsg;
    private String cancelMsg;
    private Boolean isCancelable = true;
    private onClickListener listener;

    public CustomDialog() {
    }

    public static CustomDialog newInstance(String title, String msg, String okMsg, String cancelMsg) {
        CustomDialog fragment = new CustomDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("msg", msg);
        args.putString("okMsg", okMsg);
        args.putString("cancelMsg", cancelMsg);
        fragment.setArguments(args);
        return fragment;
    }
    private void getArgumentsData() {
        if (getArguments() != null) {
            this.title = getArguments().getString("title");
            this.msg = getArguments().getString("msg");
            this.okMsg = getArguments().getString("okMsg");
            this.cancelMsg = getArguments().getString("cancelMsg");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCustomDialogBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        getArgumentsData();

        setCancelable(isCancelable);
        if (TextUtils.isEmpty(title)) {
            binding.tvTitle.setVisibility(View.GONE);
        } else binding.tvTitle.setVisibility(View.VISIBLE);

        if (TextUtils.isEmpty(okMsg)) {
            binding.btnOk.setVisibility(View.GONE);
        }
        if (TextUtils.isEmpty(cancelMsg)) {
            binding.btnCancel.setVisibility(View.GONE);
        }
        binding.btnOk.setText(okMsg);
        binding.btnCancel.setText(cancelMsg);
        binding.tvTitle.setText(title);
        binding.tvMsg.setText(msg);

        binding.btnOk.setOnClickListener(view1 -> {
            getDialog().dismiss();
            if (listener != null)
                listener.onOkClick();
        });

        binding.btnCancel.setOnClickListener(view1 -> {
            getDialog().dismiss();
            if (listener != null)
                listener.onCancelClick();
        });

        return view;

    }

    public void setListener(onClickListener listener) {
        this.listener = listener;
    }


    @Override
    public void onResume() {
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        // Assign window properties to fill the parent
        params.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.8);
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((WindowManager.LayoutParams) params);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);
        super.onResume();

    }

    public interface onClickListener {
        void onOkClick();

        void onCancelClick();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
