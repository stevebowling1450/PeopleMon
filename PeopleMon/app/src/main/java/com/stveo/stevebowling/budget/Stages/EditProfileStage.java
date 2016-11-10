package com.stveo.stevebowling.budget.Stages;

import android.app.Application;

import com.stveo.stevebowling.budget.PeopleMonApplication;
import com.stveo.stevebowling.budget.R;
import com.stveo.stevebowling.budget.Riggers.SlideRigger;

/**
 * Created by stevebowling on 11/9/16.
 */

public class EditProfileStage extends IndexedStage {
    private final SlideRigger rigger;

    public EditProfileStage(Application context){
        super(EditProfileStage.class.getName());
        this.rigger=new SlideRigger(context);
    }

    public EditProfileStage(){
        this(PeopleMonApplication.getInstance());
    }

    @Override
    public int getLayoutId() {
        return R.layout.edit_profile;
    }

    @Override
    public Rigger getRigger() {
        return rigger;
    }

}
