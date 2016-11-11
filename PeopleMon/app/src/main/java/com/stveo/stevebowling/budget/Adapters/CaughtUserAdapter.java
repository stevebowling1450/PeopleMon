package com.stveo.stevebowling.budget.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stveo.stevebowling.budget.Components.Utils;
import com.stveo.stevebowling.budget.Models.User;
import com.stveo.stevebowling.budget.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by stevebowling on 11/10/16.
 */

public class CaughtUserAdapter extends RecyclerView.Adapter<CaughtUserAdapter.UserHolder> {

    public ArrayList<User> users;
    private Context context;

    public CaughtUserAdapter(ArrayList<User> users, Context context) {
        this.users = users;
        this.context = context;
    }


    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflateView = LayoutInflater.from(context).inflate(R.layout.caught_user_item, parent, false);
        return new UserHolder(inflateView);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {

        if(position <users.size()){
            User user = users.get(position);
            holder.bindUser(user);
        }
    }

    @Override
    public int getItemCount() {
        return users.size()+1;
    }


    class UserHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.caught_avatar)
        ImageView caughtAvatar;

        @Bind(R.id.caught_username)
        TextView caughtUserName;

        public UserHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        //        Lets put our data in our UI
        public void bindUser(User user){
            caughtUserName.setText(user.getUserName());

            caughtAvatar.setImageBitmap(Utils.decodeImage(user.getAvatarBase()));
        }
    }
}