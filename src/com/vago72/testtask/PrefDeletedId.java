package com.vago72.testtask;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * ������ � ���������.
 * ���������� ������� ���������� � ���������� ��������� ��������. 
 * @author Valeriy Golubkov
 */
public class PrefDeletedId {

    String APP_PREFERENCES = "PrefDeletedId";
    private SharedPreferences mSettings = null;

    /**
     * <pre>
     * ������ �� ��������. 
     * ���������� ��� ����������� ��������� � ������� � �������� ������ ��������.
     * </pre>
     */
    // ���������� ����� - MainActivity
    private Context _this = null; 
    
    public PrefDeletedId(Context a) { _this = a; }
    
    /**
     * ���������� ������ ���������� ������ � ����� ���������.
     * 
     * @param param ��� ��������� ���������
     * @param str �������� ��������� ����������
     */
    public void setValue(String param, String str) {

        /* ��� ������:
         * ������������� SharedPreferences, � ��� Editor-�������
         * ��������� �������� ������ � editor,
         * ���������� ������ � ����� Preferences   
         */
    }

    /**
     * ���������� ������ ���������� ������ �� ���������������� ���� ����� ���������
     * 
     * @param param ��� ��������� ���������
     * @param def �������� ������������� �� ���������, ���� ����� ���������� � ��������� ����  
     * @return �������� ��������� ����������
     */
    public String getString(String param, String def) {

        String ret = def;

        /* ��� ������:
         * ������������� SharedPreferences � ����������� ������� 
         * �������� ������ �� ��������� ����������  
         */
        
        return ret;
    }
    
};

