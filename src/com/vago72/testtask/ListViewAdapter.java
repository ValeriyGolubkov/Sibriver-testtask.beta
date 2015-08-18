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
 * <b>������� �������.</b>
 * ������� ���������� ������������������ ������ Job-�������.
 * ������� ������������ ������ ��� ������� ����� ������.
 * </pre>
 * @author Valeriy Golubkov
 */
public class ListViewAdapter extends ArrayAdapter<ListViewData> 
{
    /**
     * ������������� ������� inflater-���������
     */
    private int layoutResourceId;
    
    /**
     * Counter ���������� ��������� ����������������� ������.
     * ������ - ��������. � ����� ��� ����� ��������� �������� 
     * ��� ������� ���������� ��������� - �������� ������� �� ���.
     * 
     */
    public int iSumChecked=0;

    /**
     * <pre>
     * ������ �� �������� (MainActivity). 
     * ��������� ��� ����������� ��������� � ������� � �������� ������ ��������.
     * </pre>
     */
    private MainActivity _mainActivity;

    /**
     * �����������
     *
     * @param context ��������� �� ���������� ����� 
     * @param layoutResourceId ������������� ������� inflater-��������� 
     * @param data ������ ��������� ��� ���������� � ������
     */
    ListViewAdapter(Context context, int layoutResourceId, ListViewData[] data) 
    {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        _mainActivity = (MainActivity) context;
        _mainActivity._data = data;
    };
  
    /**
     * �������� �������� ������� �� ������� ������ (��������� ��������) 
     */
    private OnCheckedChangeListener myCheckChangList = new OnCheckedChangeListener() { 
      @Override
      public void onCheckedChanged(CompoundButton buttonView,
              boolean isChecked) {

          /* ��� ������:
           * �������� ��������� �������� .chkVal 
           * � ������ ����������� ��������� - ������������� iSumChecked 
           * � �������� ������ - �������������
           */
      }
    };
    
    /**
     * ������ �������� ������
     * 
     * @param position ������������� �������  
     * @return ���������� getItem(position)
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

                /* ��� ������:
                 * �������� ��������� holder 
                 * ��������������� ���������� �������� ������,
                 * �������� ��������� ������� �� ������� ������,
                 * ����������� �������� ��������� �������� � �������� false
                 */
        }
        else
        {
            holder = (ListViewDataHolder)row.getTag();
        }
        
        ListViewData lvd = _mainActivity._data[position];
        
            /* ��� ������:
             * ������������� ��������� holder � ������������ �� ��������� ������ �������   
             */

        // --  --  --  --  --  --  --  --  --  --  --  -- 
        
        /*
         * ������ ����������� � ��������� ����� � � ���������������� ������ �������
         * � �� ������ �� ������� �� ����� ������, ������� 
         * "�����-����� ���������� ����"... :)  
         * � ����� ����, ����������� ����� ���� ������������ � ������������� 
         * � ������������ � ����������� ������������/�������������
         */ 

        // ����������� ��������� ������� � �� �������� ��� ����
        holder.status.setText(statuseLookUp(lvd.status));
        
        // ����������� � ������������� ����������� �������� Job-�������:
        // �� ��� ������ ����� ��� ���������� � ������ ������������
        
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

            // ((TextView)row.findViewById(R.id.txtT1)).setVisibility(View.GONE); ������
            ((TextView)row.findViewById(R.id.txtT2)).setVisibility(View.GONE); // �����
            ((TextView)row.findViewById(R.id.txtT3)).setVisibility(View.GONE); // � ������
            ((TextView)row.findViewById(R.id.txtT4)).setVisibility(View.GONE); // ���������
            switch(lvd.status) {
            case (0):
                ((TextView)row.findViewById(R.id.txtT2)).setVisibility(View.VISIBLE); // �����
                break;
            case (1):
                ((TextView)row.findViewById(R.id.txtT3)).setVisibility(View.VISIBLE); // � ������
                break;
            case (2):
                ((TextView)row.findViewById(R.id.txtT4)).setVisibility(View.VISIBLE); // ���������
                break;
            case (3):
                holder.txtT4.setText(_mainActivity.getResources().getString(R.string.JobStaruses3)); // ��������
                ((TextView)row.findViewById(R.id.txtT4)).setVisibility(View.VISIBLE); // 
                break;
            }
            
            
        }

        return row;
    }
    
    /**
     * LookUp - ����������� ��������� ������� �� �������� ��� ����
     * 
     * ������ ������� <a href="http://testtask.beta.sibriver.com/">
     * POSSIBLE JOB STATUSES</a> ��� ����� ������� ������������� � ��������� �������.
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
