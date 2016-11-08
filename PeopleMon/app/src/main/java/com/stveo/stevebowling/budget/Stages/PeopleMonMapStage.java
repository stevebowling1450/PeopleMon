package com.stveo.stevebowling.budget.Stages;

import android.app.Application;

import com.stveo.stevebowling.budget.PeopleMonApplication;
import com.stveo.stevebowling.budget.R;
import com.stveo.stevebowling.budget.Riggers.SlideRigger;

/**
 * Created by stevebowling on 10/31/16.
 */

public class PeopleMonMapStage extends IndexedStage {
    private final SlideRigger rigger;

    public PeopleMonMapStage(Application context){
        super(PeopleMonMapStage.class.getName());
        this.rigger=new SlideRigger(context);
    }

    public PeopleMonMapStage(){
        this(PeopleMonApplication.getInstance());
    }

    @Override
    public int getLayoutId() {
        return R.layout.maps_view;
    }

    @Override
    public Rigger getRigger() {
        return rigger;
    }
}
