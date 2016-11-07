package com.stveo.stevebowling.budget.Riggers;

import android.app.Application;

import com.davidstemmer.screenplay.stage.rigger.AnimResources;
import com.davidstemmer.screenplay.stage.rigger.TweenRigger;
import com.stveo.stevebowling.budget.R;

/**
 * Created by stevebowling on 10/31/16.
 */

public class SlideRigger extends TweenRigger {
    private static final AnimResources params = new AnimResources();

    static {
        params.forwardIn = R.anim.slide_in_right;
        params.backIn = R.anim.slide_in_left;
        params.backOut = R.anim.slide_in_right;
        params.forwardOut = R.anim.slide_in_left;

    }

    public  SlideRigger(Application context){
        super(context, params);
    }
}
