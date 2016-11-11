package com.stveo.stevebowling.budget.Stages;

import android.app.Application;

import com.stveo.stevebowling.budget.PeopleMonApplication;
import com.stveo.stevebowling.budget.R;
import com.stveo.stevebowling.budget.Riggers.SlideRigger;

/**
 * Created by stevebowling on 11/10/16.
 */

public class CaughtStage extends IndexedStage {
    private final SlideRigger rigger;

    public CaughtStage(Application context) {
        super(CaughtStage.class.getName());
        this.rigger = new SlideRigger(context);
    }

    public CaughtStage() {
        this(PeopleMonApplication.getInstance());
    }

    @Override
    public int getLayoutId() {
        return R.layout.caught_view;
    }

    @Override
    public Rigger getRigger() {
        return rigger;
    }
}