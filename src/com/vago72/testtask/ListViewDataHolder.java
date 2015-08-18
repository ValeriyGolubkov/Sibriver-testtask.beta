package com.vago72.testtask;

import android.widget.CheckBox;
import android.widget.TextView;

/**
 * <pre>
 * <b>������� �������.</b>
 * ������������ ������������� ������������������ ������ Job-�������
 * � ������������ � ���������� ������ ������.
 * ����� ������:
 *         ListViewData, 
 *         �������� ����� txtT{1;4} - ���������� ���������� ����� � ����������� �� �������� statuses
 *
 *  @see ListViewData
 * </pre>
 * ������ ������� <a href="http://testtask.beta.sibriver.com/">
 * RESPONSE DESCRIPTION</a> ��� ����� ������� ������������� � ������.
 *
 * @author Valeriy Golubkov
 */
class ListViewDataHolder
{
        TextView id;    
        TextView name;     
        TextView status;  
        TextView address; 
        TextView lat;    
        TextView lon;    
        TextView created;
        CheckBox chk;
        
        /**
         * ���� txtT1 - ������� �� �������� "������"; 
         * �������� ����� @color/dark_golden_rod
         */
        TextView txtT1;

        /**
         * ���� txtT2 - ������� �� �������� "�����"; 
         * �������� ����� @color/green
         */
        TextView txtT2;

        /**
         * ���� txtT3 -������� �� �������� "� ������"; 
         * �������� ����� @color/dark_goldenrod
         */
        TextView txtT3;

        /**
         * ���� txtT3 - ������� �� �������� "���������/��������"; 
         * �������� ����� @color/fire_brick
         */
        TextView txtT4;

}
