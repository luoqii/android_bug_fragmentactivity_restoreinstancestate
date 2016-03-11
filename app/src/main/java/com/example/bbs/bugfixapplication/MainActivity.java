package com.example.bbs.bugfixapplication;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MainActivity extends FragmentActivity {

    protected ViewGroup mFragContainer;
    private Fragment mFragment;
    private static int sCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragContainer = (ViewGroup) findViewById(R.id.fragemnt_container);
//        initView();
        sCount++;

//        setTitle(getClass().getSimpleName() + ":" + getFragmentTitle());
    }

    private void initView() {
        Fragment fragment = new SimpleFragment();
        Bundle args = new Bundle();
        args.putString(SimpleFragment.KEY_TEXT, "acitivity A");
        fragment.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragemnt_container, fragment)
                .commit();
    }

    String getFragmentTitle(){
        return getClass().getSimpleName();
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.add:
                if (null == mFragment) {
                    mFragment = initFragment();
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.fragemnt_container, mFragment)
                            .commit();
                }
                break;
            case R.id.show:
                if (null != mFragment){
                    getSupportFragmentManager().beginTransaction()
                            .show(mFragment)
                            .commit();
                }
                break;
            case R.id.hide:
                if (null != mFragment){
                    getSupportFragmentManager().beginTransaction()
                            .hide(mFragment)
                            .commit();
                }
                break;
            case R.id.add_and_hide:
                if (null == mFragment){

                    mFragment = initFragment();
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.fragemnt_container, mFragment)
                            .hide(mFragment)
                            .commit();
                }
                break;
            case R.id.remove:
                if (null != mFragment){
                    getSupportFragmentManager().beginTransaction()
                            .remove(mFragment)
                            .commit();
                    mFragment = null;
                }
                break;

            case R.id.new_activity:
                try {
                    startActivity(new Intent(this, Class.forName("com.example.bbs.bugfixapplication.MainActivity$SimpleActivity" + sCount)));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.npe:
                String npe = null;
                if (npe.length() == 0){

                }
                break;
        }
    }

    private Fragment initFragment() {
        SimpleFragment f = new SimpleFragment();
        Bundle args = new Bundle();
        args.putString(SimpleFragment.KEY_TEXT, getFragmentTitle());
        f.setArguments(args);

        return f;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class SimpleFragment extends Fragment {
        public static final  java.lang.String KEY_TEXT = "KEY_TEXT";
        private CharSequence mText;

        @Override
        public void setArguments(Bundle args) {
            super.setArguments(args);
            mText = args.getString(KEY_TEXT);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//            return super.onCreateView(inflater, container, savedInstanceState);
            TextView v = new TextView(getActivity());
            v.setText(mText);
            return v;
        }
    }

    public static class SimpleActivity1 extends MainActivity {}
    public static class SimpleActivity2 extends MainActivity {}
    public static class SimpleActivity3 extends MainActivity {}
    public static class SimpleActivity4 extends MainActivity {}
    public static class SimpleActivity5 extends MainActivity {}
    public static class SimpleActivity6 extends MainActivity {}
}
