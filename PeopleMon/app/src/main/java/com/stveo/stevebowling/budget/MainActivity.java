package com.stveo.stevebowling.budget;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.davidstemmer.flow.plugin.screenplay.ScreenplayDispatcher;
import com.stveo.stevebowling.budget.Components.Utils;
import com.stveo.stevebowling.budget.Network.UserStore;
import com.stveo.stevebowling.budget.Stages.EditProfileStage;
import com.stveo.stevebowling.budget.Stages.LoginStage;
import com.stveo.stevebowling.budget.Stages.PeopleMonMapStage;
import butterknife.Bind;
import butterknife.ButterKnife;
import flow.Flow;
import flow.History;

public class MainActivity extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE=1;
    private String TAG = "MainActivity";
    private Flow flow;
    private ScreenplayDispatcher dispatcher;


    @Bind(R.id.container)
    RelativeLayout container;

    private Menu menu;
    public Bundle savedInstanceState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        if (Build.VERSION.SDK_INT >= 23){
            if (!(ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_GRANTED)){
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
            }
            if (!(ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_GRANTED)){
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            }
        }


        if (Build.VERSION.SDK_INT >= 23){
            if (!(ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED)){
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
            if (!(ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_GRANTED)){
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }

        flow = PeopleMonApplication.getMainFlow();
        dispatcher = new ScreenplayDispatcher(this, container);
        dispatcher.setUp(flow);

        if (UserStore.getInstance().getToken() == null ||
                UserStore.getInstance().getTokenExpiration() == null) {
            History newHistory = History.single(new LoginStage());
            flow.setHistory(newHistory, Flow.Direction.REPLACE);
        }
    }

    @Override
    public void onBackPressed() {
        if (!flow.goBack()) {
            flow.removeDispatcher(dispatcher);
            flow.setHistory(History.single(new PeopleMonMapStage()),
                    Flow.Direction.BACKWARD);
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Flow flow = PeopleMonApplication.getMainFlow();
        switch (item.getItemId()) {
            case R.id.editProfile:
                History newHistory = flow.getHistory().buildUpon()
                       .push(new EditProfileStage())
                        .build();
                flow.setHistory(newHistory, Flow.Direction.FORWARD);
                return true;
            case R.id.logout:
                UserStore.getInstance().setToken(null);
                History logoutHistory = History.single(new LoginStage());
                flow.setHistory(logoutHistory, Flow.Direction.REPLACE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getImage(){
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            Bitmap image = BitmapFactory.decodeFile(picturePath);
            Utils.encodeTobase64(image);
            imageView.setImageBitmap(image);

        }
    }
}
