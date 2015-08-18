package com.vago72.testtask;

import java.util.ArrayList;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

/**
 * <pre>
 * ����� ����� ���������: ������������� ������, ������ ������ � �������.
 * </pre>
 * @author Valeriy Golubkov
 */

public class MainActivity extends Activity implements
        NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the
     * navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    //////////////////////////////////////////////////////////////////////////////
    /**
     * <pre>
     * ������ �� �������� (MainActivity). 
     * ��������� ��� ����������� ��������� � ������� � �������� ������ ��������.
     * ������������, � ���������, �� ������ Runnable, � ������� this 
     * ������������ ��� ���������� ��������� �� ���������� �������   
     * </pre>
     */
    private MainActivity _mainActivity = this;
    
    /**
     * <pre>
     *  mAdapterLayout ������, ������������ � ��������.
     *  ������� �� ������� �������������� � ������, ��������� ���������� �� ������,
     *  �� �������� ���� ������� ��������� � ������ R.  
     * </pre>
     */
    public int mAdapterLayout = R.layout.list_item;

    /**
     * �������� �������� ������������������ ������  
     */
    public ListView lvMain;

    /**
     * ������� ������������ ������  
     */
    public ListViewAdapter mAdapter = null;
    
    /**
     * ������������� ������ ������� 
     */
    public ListViewData _data[] = null;
    
    /**
     * ������ �������� ������ 
     */
    public Swipe swipe = new Swipe(this);
    
    /**
     * ������ ��������� �� ������: ������� - � ������ �������� ��
     * ������ ������� <a href="http://testtask.beta.sibriver.com/">
     * API METHODS</a> ��� ������������� � ������� ������������ ������.
     */
    public String feedUrl = "http://testtask.beta.sibriver.com/get/jobs/";
    
    /**
     * <pre>
     *    C����� Job-�������, ���������� � �������� ������ �� �������.
     *    ������ �� ��������� ����� ����� ������ ������������ ����������� 
     *    ������������������ ������, 
     *    � ����� �� ����� ������ ������������ ���������� ���������,
     *    ���������� ��� "���������".      
     * </pre>
     */
    public ArrayList<ResponceJob> dataHeap = new ArrayList<ResponceJob>();

    //////////////////////////////////////////////////////////////////////////////
    /**
     * �������, ������������ � ����������� Runnable ������  
     */
    private Handler hChkDelete = new Handler();
    
    /**
     * <pre>
     * ������ ��������� ����� �� ���������� �������� ��������� 
     * �� ������������������ ������. ����������� � ������� ����, ��� 
     * ��������� �������� �������� ��������� ������ ������������ � 
     * extends Fragment ������ (NavigationDrawerFragment),
     * � �� �����, ��� �������� ������ �� ��������� ������ 
     * ������������ � ������ �������� (MainActivity). 
     * </pre>
     */
    private Runnable chkDelete = new Runnable() {
        public void run() {
            CheckBox chkDelete = (CheckBox) findViewById(R.id.delete);
            if (chkDelete.isChecked()) {
                chkDelete.setChecked(false);
                deleteItems();
            }
            // ������� ������ ��������� ��������� ����� - 4 tps
            hChkDelete.postDelayed(this, 250); 
        }
    };

    //////////////////////////////////////////////////////////////////////////////
    
    /**
     * <pre>
     * ������������ id ��������� ���������: 
     * null, ���� ������ �� ���� �������,
     * id1,id2,...,idN � ������ ����� ��������� ���������
     * </pre>
     */
    private String sDeletedItems = null;
    
    /**
     * �������� ��������� �� ������ ������� ������ dataHeap  
     */
    private void deleteItems() {
        ListViewCheck lvCheck = new ListViewCheck(this);
        if (sDeletedItems == null) sDeletedItems = lvCheck.sValue;
        else sDeletedItems += "," + lvCheck.sValue;
        
        if (lvCheck.iKey>0) {
            Toast.makeText(_mainActivity, 
                    getResources().getString(R.string.delete_choosed), Toast.LENGTH_SHORT).show();

            // ��������� �� ������ Job-������ ����� ��������� ������� 
            dataHeapMinus();
            // ���������� ������ ��������
            listAdapterLoad();
        }
        else {
            // ����������� ��������� � ���, ��� �������� ��� ������� �� ������� 
            Toast.makeText(_mainActivity, getResources()
                    .getString(R.string.delete_isnt_choosed), Toast.LENGTH_SHORT).show();
        }
    }
    
    /**
     * <pre>
     * ��������� (remove) �� ������ Job-������ ����� ��������� Job-�������.
     * ������������� � ��������� ������� �� �������� �������������� id 
     * </pre>
     */
    public  void dataHeapMinus() {
        
        if (sDeletedItems == null);
        else {
                /* ��� ������:
                 * ������� ArrayList ������� ������ Job-�������..
                 * � � ����������� �� ���������� ��������������� ���������, 
                 * ���������� ��� �������� - �������� ���� ��������� �� ������ 
                 */
        }
    }

    //////////////////////////////////////////////////////////////////////////////
    /**
     * ��������� - ������������ ��� �������� ���������� � ����� ��������� Job-�������� 
     */
    private PrefDeletedId prefDel = new PrefDeletedId(this);
    
    /**
     * <pre>
     * ��� ������� ����� ������ ������ ���������:
     * ������������� ���������� (������������) � ����� ��������� Job-�������� 
     * </pre>
     */
    protected void onResume()
    {
        super.onResume();
        sDeletedItems = prefDel.getString("sDeletedItems", null); 
    }

    /**
     * <pre>
     * ��� �������� ������� ������ ������ ���������:
     * ���������� ���������� (������������) � ����������� �������� Job-������� 
     * </pre>
     */
    protected void onPause()
    {
        prefDel.setValue("sDeletedItems", sDeletedItems); 
        super.onPause();
    }

    //////////////////////////////////////////////////////////////////////////////
    /**
     * ���������� � ���������� ������������������ ������ Job-�������
     */
    public void listAdapterLoad()
    {
            /* ��� ������:
             * ������� ������� - ������������ ������ ��� ��������,
             * �������� ������� ������,   
             * ���������� ��������, � ��� �������� �� ListView
             */
    
        lvMain.setAdapter(_mainActivity.mAdapter);
    }
    
    //////////////////////////////////////////////////////////////////////////////    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ������������� ������
        swipe.init();

        // ������� � ������ ����������� ������� ��������� �������� �������� Job-�������
        hChkDelete.postDelayed(chkDelete, 0);

        mNavigationDrawerFragment = (NavigationDrawerFragment) 
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.container,
                        PlaceholderFragment.newInstance(position + 1)).commit();
    }

    
    public void onSectionAttached(int number) {
        switch (number) {
        case 1:
            mTitle = getString(R.string.title_section1);
            break;
        case 2:
            mTitle = getString(R.string.title_section2);
            break;
        case 3:
            mTitle = getString(R.string.title_section3);
            break;
        case 4:
            mTitle = getString(R.string.title_section4);
            break;
        case 5:
            mTitle = getString(R.string.title_section5);
            break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container,
                    false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(getArguments().getInt(
                    ARG_SECTION_NUMBER));
        }
    }

    /**
     * <pre>
     * ��������� ����� �� �������� ������ Job-�������. 
     * ����� �������������� �� ��������-������ �������� ������ (list_item.xml) 
     * ������������ ��� �������������� ��������� 
     * � ��������������� ���������� ���� ������� (��������������) �������. 
     * ����� ���������/��������� �������� ���-����� �������� ��������� - 
     * ��� ���������� ����������� �������� �������� ����������� ��������� 
     * �� ������������������ ������
     * </pre>
     *   
     * @param row ������� Job-�������, � ������� ������������ ��������
     */
    public void itemOnClick(View row) {
        CheckBox chk = (CheckBox)row.findViewById(R.id.checkBox1);
        if (chk.isChecked()) {
            chk.setChecked(false);
            row.setBackgroundColor(Color.WHITE);
        }
        else {
            chk.setChecked(true);
            row.setBackgroundColor(Color.GRAY);
        }
    }
    
    /**
     * <pre>
     * ��������� ��� ��������� � �������� ���������� ������ �� �������
     * � ���� ��������������� ���� (���� ���������� �������):
     * {200-��, 500-JobsErr, 404-JobErr, 400-JobDelErr}
     *   
     * onSuccess - 200
     * onError - {500,404,400}
     *
     * �� <a href="http://testtask.beta.sibriver.com/">POSSIBLE RESPONSE CODES</a> 
     * � <a href="http://testtask.beta.sibriver.com/">EXAMPLE RESPONSE ERROR</a> � �������� ������� API METHODS
     * ��� ����� ������� ������������� �� �������.
     * </pre>
     * 
     * @see Swipe
     * �������� ���������� ������ GetSrvResponce
     * @see GetSrvResponce
     * �������� ���������� � �������� ��������� ������������ 
     *
     */
    public static interface NetworkOperationCallBack
    {
        public abstract void onSuccess(String s);
        public abstract void onError(String s);
    }
    
     /**
      * ����� ��������� ���������� �� ��������: 
      * Toast.makeText(...).show();
      */
    public void showInfo(String str) {
        Toast.makeText(_mainActivity, str, Toast.LENGTH_SHORT).show();
    }
    
}
