package com.guaju.dagger2demo.presenter.impl;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import com.guaju.dagger2demo.presenter.LoginPresenterI;
import com.guaju.dagger2demo.view.LoginViewI;

import javax.inject.Inject;

/**
 * Created by root on 17-6-26.
 */

public class LoginPresenterImpl implements LoginPresenterI {
    private LoginViewI loginView;

    @Inject
      LoginPresenterImpl(LoginViewI loginView){
        this.loginView=loginView;
    }
    @Override
      public void login(final EditText edU,final EditText edP) {
    /**
     * to do something
     *
     */
        edU.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String username = s.toString();
                if(!TextUtils.isEmpty(username)){

                }
                else{
                    loginView.loginError();
                }

            }
        });
        edP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String password = s.toString();
                if (!TextUtils.isEmpty(password)){
                    if ("username".equals(edU.getText().toString().trim())&&
                            "password".equals(password)){
                    loginView.loginSuccess();
                    }else{
                        loginView.loginError();
                    }
                } else{
                    loginView.loginError();
                }
            }
        });
    }
}
