package com.stveo.stevebowling.budget.Stages;

import android.app.Application;

import com.stveo.stevebowling.budget.PeopleMonApplication;
import com.stveo.stevebowling.budget.R;
import com.stveo.stevebowling.budget.Riggers.SlideRigger;

/**
 * Created by stevebowling on 10/31/16.
 */

public class PeopleMonListStage extends IndexedStage {
    private final SlideRigger rigger;

    public PeopleMonListStage(Application context){
        super(PeopleMonListStage.class.getName());
        this.rigger=new SlideRigger(context);
    }

    public PeopleMonListStage(){
        this(PeopleMonApplication.getInstance());
    }

    @Override
    public int getLayoutId() {
        return R.layout.peoplemon_list_view;
    }

    @Override
    public Rigger getRigger() {
        return rigger;
    }
}
