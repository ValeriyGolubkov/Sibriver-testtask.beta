package com.vago72.testtask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * <pre>
 * <b>Типовое решение.</b>
 * Адаптер заполнения кастомизированного списка Job-позиций.
 * Отрбтка кастомизации списка под текуший набор данных.
 * </pre>
 * @author Valeriy Golubkov
 */
public class ListViewAdapter extends ArrayAdapter<ListViewData> 
{
    /**
     * Идентификатор лэйаута inflater-интерации
     */
    private int layoutResourceId;
    
    /**
     * Counter отмеченных элементов кастомизированого списка.
     * Глвное - отметить. А затем уже можно выполнить действие 
     * над группой отмеченных элементов - например удалить их все.
     * 
     */
    public int iSumChecked=0;

    /**
     * <pre>
     * Ссылка на активити (MainActivity). 
     * Релизация для возможности обращения и доступа к объектам класса активити.
     * </pre>
     */
    private MainActivity _mainActivity;

    /**
     * Конструктор
     *
     * @param context указатель на вызывающий класс 
     * @param layoutResourceId идентификатор лэйаута inflater-интерации 
     * @param data массив элементов для интеграции в список
     */
    ListViewAdapter(Context context, int layoutResourceId, ListViewData[] data) 
    {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        _mainActivity = (MainActivity) context;
        _mainActivity._data = data;
    };
  
    /**
     * Листенер обрабоки нажатия на элемент списка (выделение элемента) 
     */
    private OnCheckedChangeListener myCheckChangList = new OnCheckedChangeListener() { 
      @Override
      public void onCheckedChanged(CompoundButton buttonView,
              boolean isChecked) {

          /* код опущен:
           * проверка состояния чекбокса .chkVal 
           * в влучае взведенного состояния - инкрементация iSumChecked 
           * в обратном случае - декрементация
           */
      }
    };
    
    /**
     * Геттер элемента списка
     * 
     * @param position запрашиваемая позиция  
     * @return возврашает getItem(position)
     */
    public ListViewData getListItem(int position) {    
        return ((ListViewData) getItem(position));  
    }

    @Override
    public View getView(int position, View row, ViewGroup parent) {
        ListViewDataHolder holder = null;
 
        if(row == null)
        {
            LayoutInflater inflater = _mainActivity.getLayoutInflater(); 
            row = inflater.inflate(layoutResourceId, parent, false);
 
            holder = new ListViewDataHolder();

                /* код опущен:
                 * подвязка элементам holder 
                 * соответствующих реквизитов элемента списка,
                 * позвязка листенера нажатия на элемент списка,
                 * выставление значения выделения элемента в значение false
                 */
        }
        else
        {
            holder = (ListViewDataHolder)row.getTag();
        }
        
        ListViewData lvd = _mainActivity._data[position];
        
            /* код опущен:
             * инициализация элементов holder в воответствии со значением данных адатера   
             */

        // --  --  --  --  --  --  --  --  --  --  --  -- 
        
        /*
         * ручная манимуляция с сокрытием полей и с переопределением лексем статуса
         * в ТЗ ничего не сказано по этому поводу, поэтому 
         * "кручу-верчу отобразить хочу"... :)  
         * В случе чего, отображение может быть пересмотрено и переоформлено 
         * в соответствии с замечаниями постановщика/пользователяя
         */ 

        // расшифровка состояния статуса в по значению его кода
        holder.status.setText(statuseLookUp(lvd.status));
        
        // манипуляции с составляющими компонетами элемента Job-позиции:
        // не все данные нужны для отображеия в списке кастомизации
        
        {
            ((TextView)row.findViewById(R.id.txtId)).setVisibility(View.GONE);
            ((TextView)row.findViewById(R.id.txtStatus)).setVisibility(View.GONE);
            ((TextView)row.findViewById(R.id.txtLat)).setVisibility(View.GONE);
            ((TextView)row.findViewById(R.id.txtLon)).setVisibility(View.GONE);
            ((TextView)row.findViewById(R.id.checkBox1)).setVisibility(View.GONE);

            holder.txtT1 = (TextView)row.findViewById(R.id.txtT1);
            holder.txtT2 = (TextView)row.findViewById(R.id.txtT2);
            holder.txtT3 = (TextView)row.findViewById(R.id.txtT3);
            holder.txtT4 = (TextView)row.findViewById(R.id.txtT4);
            holder.txtT1.setText(_mainActivity.getResources().getString(R.string.JobStaruses4Q)); 
            holder.txtT2.setText(_mainActivity.getResources().getString(R.string.JobStaruses0)); 
            holder.txtT3.setText(_mainActivity.getResources().getString(R.string.JobStaruses1));
            holder.txtT4.setText(_mainActivity.getResources().getString(R.string.JobStaruses2));

            // ((TextView)row.findViewById(R.id.txtT1)).setVisibility(View.GONE); вопрос
            ((TextView)row.findViewById(R.id.txtT2)).setVisibility(View.GONE); // новая
            ((TextView)row.findViewById(R.id.txtT3)).setVisibility(View.GONE); // в работе
            ((TextView)row.findViewById(R.id.txtT4)).setVisibility(View.GONE); // выполнено
            switch(lvd.status) {
            case (0):
                ((TextView)row.findViewById(R.id.txtT2)).setVisibility(View.VISIBLE); // новая
                break;
            case (1):
                ((TextView)row.findViewById(R.id.txtT3)).setVisibility(View.VISIBLE); // в работе
                break;
            case (2):
                ((TextView)row.findViewById(R.id.txtT4)).setVisibility(View.VISIBLE); // выполнено
                break;
            case (3):
                holder.txtT4.setText(_mainActivity.getResources().getString(R.string.JobStaruses3)); // отменено
                ((TextView)row.findViewById(R.id.txtT4)).setVisibility(View.VISIBLE); // 
                break;
            }
            
            
        }

        return row;
    }
    
    /**
     * LookUp - расшифровка состояния статуса по значению его кода
     * 
     * Смотри область <a href="http://testtask.beta.sibriver.com/">
     * POSSIBLE JOB STATUSES</a> для более полного представления о значениях статуса.
     * 
     */
    private String statuseLookUp(int code) {
        String s = "";
        switch(code) 
        {
            case 0: 
                s = _mainActivity.getResources().getString(R.string.JobStaruses0); 
                break;
            case 1:
                s = _mainActivity.getResources().getString(R.string.JobStaruses1); 
                break;
            case 2:
                s = _mainActivity.getResources().getString(R.string.JobStaruses2); 
                break;
            case 3:
                s = _mainActivity.getResources().getString(R.string.JobStaruses3); 
                break;
        }
        return s;
    }

    @Override
    public int getViewTypeCount() {
       if(getCount()==0)
          return super.getViewTypeCount();
        else
          return getCount();
    }

    
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    
}
