package com.ex.tainguyen.githuberx.ui.photo.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;

import com.ex.tainguyen.githuberx.AppApplication;
import com.ex.tainguyen.githuberx.R;
import com.ex.tainguyen.githuberx.di.HasComponent;
import com.ex.tainguyen.githuberx.di.components.DaggerUserComponent;
import com.ex.tainguyen.githuberx.di.components.UserComponent;
import com.ex.tainguyen.githuberx.di.modules.PhotoModule;
import com.ex.tainguyen.githuberx.ui.base.AbstractDetailActivity;
import com.ex.tainguyen.githuberx.ui.photo.fragment.PhotoDetailFragment;

public class PhotoDetailActivity
        extends AbstractDetailActivity
        implements HasComponent<UserComponent> {

    public static final String ARG_ITEM_ID = "ARG_ITEM_ID";
    UserComponent mUserComponent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserComponent = initComponent();

        fab.setOnClickListener(view ->
                Snackbar.make(view
                        , "Replace with your own detail action"
                        , Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show());

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.user_detail_container, new PhotoDetailFragment())
                    .commit();
        }
    }

    protected UserComponent initComponent() {
        return DaggerUserComponent.builder()
                .appComponent(((AppApplication) getApplication()).getComponent())
                .photoModule(new PhotoModule(getIntent().getStringExtra(ARG_ITEM_ID)))
                .build();
    }

    @Override
    public UserComponent getComponent() {
        return mUserComponent;
    }
}
