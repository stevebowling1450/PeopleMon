package com.stveo.stevebowling.budget.Stages;

import android.app.Application;

import com.stveo.stevebowling.budget.PeopleMonApplication;
import com.stveo.stevebowling.budget.R;
import com.stveo.stevebowling.budget.Riggers.SlideRigger;

/**
 * Created by stevebowling on 10/31/16.
 */

public class RegisterStage extends IndexedStage {
    private final SlideRigger rigger;

    public RegisterStage(Application context){
        super(RegisterStage.class.getName());
        this.rigger=new SlideRigger(context);
    }

    public RegisterStage(){
        this(PeopleMonApplication.getInstance());
    }

    @Override
    public int getLayoutId() {
        return R.layout.registor_view;
    }

    @Override
    public Rigger getRigger() {
        return rigger;
    }
}
