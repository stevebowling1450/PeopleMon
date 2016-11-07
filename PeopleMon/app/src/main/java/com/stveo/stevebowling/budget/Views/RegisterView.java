package com.stveo.stevebowling.budget.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Patterns;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.stveo.stevebowling.budget.Models.Account;
import com.stveo.stevebowling.budget.Network.RestClient;
import com.stveo.stevebowling.budget.PeopleMonApplication;
import com.stveo.stevebowling.budget.R;
import com.stveo.stevebowling.budget.Stages.PeopleMonListStage;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import flow.Flow;
import flow.History;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.stveo.stevebowling.budget.Components.Constants.apiKey;
import static com.stveo.stevebowling.budget.Components.Constants.avatar;


public class RegisterView extends LinearLayout {
    private Context context;

    @Bind(R.id.username_field)
    EditText usernameField;

    @Bind(R.id.password_field)
    EditText passwordField;

    @Bind(R.id.email_field)
    EditText emailField;

    @Bind(R.id.confirm_field)
    EditText confirmField;

    @Bind(R.id.register_button)
    Button registerButton;

    @Bind(R.id.spinner)
    ProgressBar spinner;

    public RegisterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    @OnClick(R.id.register_button)
    public void register(){
        InputMethodManager imm =(InputMethodManager)context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(usernameField.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(passwordField.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(emailField.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(confirmField.getWindowToken(), 0);

        String username= usernameField.getText().toString();
        String password= passwordField.getText().toString();
        String email= emailField.getText().toString();
        String confirm= confirmField.getText().toString();

        if (username.isEmpty() || password.isEmpty() || email.isEmpty()|| confirm.isEmpty()) {
            Toast.makeText(context, R.string.provide_username,
                    Toast.LENGTH_LONG).show();
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(context, R.string.enter_valid_email, Toast.LENGTH_SHORT).show();

        }else if (!password.equals(confirm)) {
                Toast.makeText(context, R.string.match_passwords, Toast.LENGTH_SHORT).show();

        } else {

                registerButton.setEnabled(false);
                spinner.setVisibility(VISIBLE);

                Account account = new Account(email,username,avatar, apiKey,password);
                RestClient restClient = new RestClient();
                restClient.getApiSevrice().register(account).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                           // Account regUser = response.body();
                           // UserStore.getInstance().setToken(regUser.getToken());
                           // UserStore.getInstance().setTokenExpiration(regUser.getExpiration());

                            Flow flow = PeopleMonApplication.getMainFlow();
                            History newHistory = History.single(new PeopleMonListStage());
                            flow.setHistory(newHistory, Flow.Direction.REPLACE);



                        } else {
                            resetView();
                            Toast.makeText(context, "Registration Failed" + ": " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        resetView();
                        Toast.makeText(context, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }


    private void resetView(){

        registerButton.setEnabled(true);
        spinner.setVisibility(GONE);

    }
}
