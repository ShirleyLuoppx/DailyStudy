package com.ppx.dailystudy.material;

import android.text.Editable;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputLayout;
import com.ppx.dailystudy.R;
import com.ppx.dailystudy.common.BaseActivity;

import butterknife.BindView;

/**
 * @Author Shirley
 * @Date：2023/9/27
 * @Desc： TextInputLayout简单使用
 */
public class TextInputLayoutActivity extends BaseActivity {
    @BindView(R.id.user_text_input_layout)
    TextInputLayout user_text_input_layout;

    @Override
    protected int initLayout() {
        return R.layout.activity_textinput_layout;
    }

    @Override
    protected void initView() {
        user_text_input_layout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (user_text_input_layout.getEditText().getText().toString().length() > 11) {
                    user_text_input_layout.setError("密码超过11位数");
                }else{
                    user_text_input_layout.setError("");
                }
            }
        });
    }
}

