package com.guaju.dagger2demo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.guaju.dagger2demo.R;
import com.guaju.dagger2demo.presenter.impl.LoginPresenterImpl;
import com.guaju.dagger2demo.view.LoginViewI;

import javax.inject.Inject;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

public class MainActivity extends AppCompatActivity implements LoginViewI {
    @Inject
    LoginPresenterImpl loginPresenter;

//    @BindView(R.id.username)
    EditText username;
//    @BindView(R.id.password)
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
        username= (EditText) findViewById(R.id.username);
        password= (EditText)findViewById(R.id.password);
        DaggerMainActivity_LoginComponent.builder()
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);


        loginPresenter.login(username,password);

    }

    @Override
    public void loginSuccess() {
        startActivity(new Intent(this,SuccessActivity.class));

    }

    @Override
    public void loginError() {
        Toast.makeText(this, "failure", Toast.LENGTH_SHORT).show();
    }

    @Module
    public class LoginModule {
        private final LoginViewI mView;

        public LoginModule(LoginViewI mView) {
            this.mView = mView;
        }

        @Provides
        LoginViewI provideLoginView() {
            return mView;
        }
    }
    @Component(modules = LoginModule.class)
    public interface LoginComponent {
        void inject(MainActivity activity);
    }

}
