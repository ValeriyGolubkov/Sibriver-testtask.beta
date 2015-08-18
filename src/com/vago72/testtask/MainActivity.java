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
 * Точка входа прогарммы: инициализация свайпа, запрос данных с сервера.
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
     * Ссылка на активити (MainActivity). 
     * Релизация для возможности обращения и доступа к объектам класса активити.
     * Используется, в частности, из потока Runnable, в котором this 
     * отрабатывает как собственый указатель на внутреннюю область   
     * </pre>
     */
    private MainActivity _mainActivity = this;
    
    /**
     * <pre>
     *  mAdapterLayout лайаут, используемый в адептере.
     *  вынесен на уровень декларирования в классе, поскольку вызывается из класса,
     *  из которого нету прямого обращения к классу R.  
     * </pre>
     */
    public int mAdapterLayout = R.layout.list_item;

    /**
     * Подвязка адаптера кастомизированного список  
     */
    public ListView lvMain;

    /**
     * Адаптер кастомизации списка  
     */
    public ListViewAdapter mAdapter = null;
    
    /**
     * инициализация данных адатера 
     */
    public ListViewData _data[] = null;
    
    /**
     * Объект оработки свайпа 
     */
    public Swipe swipe = new Swipe(this);
    
    /**
     * Ссылка обращения на сервер: хардкод - в рамках текушего ТЗ
     * Смотри область <a href="http://testtask.beta.sibriver.com/">
     * API METHODS</a> для представления о перечне используемых ссылок.
     */
    public String feedUrl = "http://testtask.beta.sibriver.com/get/jobs/";
    
    /**
     * <pre>
     *    Cписок Job-позиций, получаемых в качестве ответа от сервера.
     *    Именно на основании даных этого списка производится заполненние 
     *    кастомизированного списка, 
     *    а также из этого списка производится исключение элементов,
     *    отмеченных как "удаленные".      
     * </pre>
     */
    public ArrayList<ResponceJob> dataHeap = new ArrayList<ResponceJob>();

    //////////////////////////////////////////////////////////////////////////////
    /**
     * хендлер, используемый в одноименном Runnable потоке  
     */
    private Handler hChkDelete = new Handler();
    
    /**
     * <pre>
     * Анализ состояния флага на проведение удаления элементов 
     * из кастомизированного списка. Реализовано в причину того, что 
     * взведение признака удаления элементов списка производится в 
     * extends Fragment класса (NavigationDrawerFragment),
     * в то время, как основная работа по обработке списка 
     * реализованна в классе активити (MainActivity). 
     * </pre>
     */
    private Runnable chkDelete = new Runnable() {
        public void run() {
            CheckBox chkDelete = (CheckBox) findViewById(R.id.delete);
            if (chkDelete.isChecked()) {
                chkDelete.setChecked(false);
                deleteItems();
            }
            // частота опроса состояния взведения флага - 4 tps
            hChkDelete.postDelayed(this, 250); 
        }
    };

    //////////////////////////////////////////////////////////////////////////////
    
    /**
     * <pre>
     * Перечисление id удаленных элементов: 
     * null, если ничего не было удалено,
     * id1,id2,...,idN в случае ранее удалениых элементов
     * </pre>
     */
    private String sDeletedItems = null;
    
    /**
     * Удаление элементов из общего массива данных dataHeap  
     */
    private void deleteItems() {
        ListViewCheck lvCheck = new ListViewCheck(this);
        if (sDeletedItems == null) sDeletedItems = lvCheck.sValue;
        else sDeletedItems += "," + lvCheck.sValue;
        
        if (lvCheck.iKey>0) {
            Toast.makeText(_mainActivity, 
                    getResources().getString(R.string.delete_choosed), Toast.LENGTH_SHORT).show();

            // вынесение из общего Job-списка ранее удаленных позиций 
            dataHeapMinus();
            // обновление списка адаптера
            listAdapterLoad();
        }
        else {
            // отображение сообщения о том, что элементы для удления не указаны 
            Toast.makeText(_mainActivity, getResources()
                    .getString(R.string.delete_isnt_choosed), Toast.LENGTH_SHORT).show();
        }
    }
    
    /**
     * <pre>
     * Вынесение (remove) из общего Job-списка ранее удаленных Job-позиций.
     * Анализируются и выносятся позиции по значению идентификатора id 
     * </pre>
     */
    public  void dataHeapMinus() {
        
        if (sDeletedItems == null);
        else {
                /* код опущен:
                 * перебор ArrayList массива данных Job-позиций..
                 * и в сответствии со значениями идентификаторов элементов, 
                 * отмеченных для удаления - удаление этих элементов их списка 
                 */
        }
    }

    //////////////////////////////////////////////////////////////////////////////
    /**
     * преференс - используется для хранения информации о ранее удаленных Job-позициях 
     */
    private PrefDeletedId prefDel = new PrefDeletedId(this);
    
    /**
     * <pre>
     * При запуске новой сессии работы программы:
     * инициализация информации (перечисление) о ранее удаленных Job-позициях 
     * </pre>
     */
    protected void onResume()
    {
        super.onResume();
        sDeletedItems = prefDel.getString("sDeletedItems", null); 
    }

    /**
     * <pre>
     * При закрытии текущей сессии работы программы:
     * сохранение информации (перечисления) о проведенных удаленях Job-позиций 
     * </pre>
     */
    protected void onPause()
    {
        prefDel.setValue("sDeletedItems", sDeletedItems); 
        super.onPause();
    }

    //////////////////////////////////////////////////////////////////////////////
    /**
     * заполнение и отобржение кастомизированного списка Job-позиций
     */
    public void listAdapterLoad()
    {
            /* код опущен:
             * типовое решение - формирование данных для адаптера,
             * согласно массива данных,   
             * оформление адаптера, и его подвязка на ListView
             */
    
        lvMain.setAdapter(_mainActivity.mAdapter);
    }
    
    //////////////////////////////////////////////////////////////////////////////    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // инициализация свайпа
        swipe.init();

        // запустк в потоке циклической провеки взведения признака удаления Job-позиций
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
     * Обработка клика по элементу списка Job-позиций. 
     * Вызов задекларирован на активити-уровне элемента списка (list_item.xml) 
     * Используется для множественного выделения 
     * с соответствующей подсветкой фона текушей (обрабатываемой) позиции. 
     * Также взводится/снимается значение чек-бокса признака выделения - 
     * для проведения последующей операции удаления выделенныхх элементов 
     * из кастомизированного списка
     * </pre>
     *   
     * @param row текушая Job-позиция, с которой производится операция
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
     * Интерфейс для получения в активити результата ответа от сервера
     * в виде результирующего кода (кода активности сервера):
     * {200-ок, 500-JobsErr, 404-JobErr, 400-JobDelErr}
     *   
     * onSuccess - 200
     * onError - {500,404,400}
     *
     * см <a href="http://testtask.beta.sibriver.com/">POSSIBLE RESPONSE CODES</a> 
     * и <a href="http://testtask.beta.sibriver.com/">EXAMPLE RESPONSE ERROR</a> в описании области API METHODS
     * для более полного представления об ошибках.
     * </pre>
     * 
     * @see Swipe
     * Создание экземпляра класса GetSrvResponce
     * @see GetSrvResponce
     * Передача интерфейса в качестве параметра конструктора 
     *
     */
    public static interface NetworkOperationCallBack
    {
        public abstract void onSuccess(String s);
        public abstract void onError(String s);
    }
    
     /**
      * Вывод строковой информации на активити: 
      * Toast.makeText(...).show();
      */
    public void showInfo(String str) {
        Toast.makeText(_mainActivity, str, Toast.LENGTH_SHORT).show();
    }
    
}
