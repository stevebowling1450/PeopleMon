package com.stveo.stevebowling.budget.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.stveo.stevebowling.budget.Components.Constants;
import com.stveo.stevebowling.budget.Components.Utils;
import com.stveo.stevebowling.budget.MainActivity;
import com.stveo.stevebowling.budget.Models.Account;
import com.stveo.stevebowling.budget.Network.RestClient;
import com.stveo.stevebowling.budget.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by stevebowling on 11/9/16.
 */

public class EditProfileView extends LinearLayout {

    private Context context;

    public String myName;

    public String myAvatar;

    @Bind(R.id.imageView)
    ImageView imageView;

    @Bind(R.id.picButton)
    Button picButton;


    @Bind(R.id.newUserName)
    EditText newName;


    @Bind(R.id.submit_button)
    Button submit;




    public EditProfileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        myProfile();
    }

    @OnClick(R.id.picButton)
    public void pictureTapped() {
        ((MainActivity) context).getImage();
    }


    @OnClick(R.id.submit_button)
    public void editProfile() {
        Account editUserProfile = new Account(newName.getText().toString(), Constants.IMAGE);
        RestClient restClient = new RestClient();
        restClient.getApiSevrice().editProfile(editUserProfile).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {

            }
        });
    }


    public void myProfile() {
        RestClient restClient = new RestClient();
        restClient.getApiSevrice().myInfo().enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful()){
                     Account profile = response.body();
                    newName.setText(profile.getFullName());
                    imageView.setImageBitmap(Utils.decodeImage(profile.getAvatarBase64()));
                        myName = profile.getFullName();
                        myAvatar= profile.getAvatarBase64();
                       // Log.d("%%%%%%", myName + myAvatar);
                    //Toast.makeText(context, "Your Name "+ myName, Toast.LENGTH_SHORT).show();
                    }
                }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
                Log.d("%%%%%%", myName + myAvatar);
            }

        });


    }
}