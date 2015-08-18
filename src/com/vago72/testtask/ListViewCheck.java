package com.vago72.testtask;

/**
 * ����� ���������� ��� ��������� ���������� 
 * ���������� (����������) ��������� ������������������ ������.
 * <pre>
 * int iKey - ���������� ���������� ������� �������
 * String sValue - ����� ������������ ��������������� ���������� ���������
 * 
 * ������ ������:
 *    CheckList chL = new CheckList(this);
 *    if (chL.iKey>0) {
 *        ...
 *        String [] id = chL.sValue.split("\\,");
 *            ... 
 *    }
 *
 * </pre>
 * @author Valeriy Golubkov
 */


class ListViewCheck {

    /**
     * <pre>
     * ������ �� �������� (MainActivity). 
     * ���������� ��� ����������� ��������� � ������� � �������� ������ ��������.
     * </pre>
     */
    private MainActivity _mainActivity;
    
    /**
     * ���������� ���������� ������� �������
     */
    public int iKey = 0;
    
    /**
     * ������ ������������ ��������������� ���������� ���������  
     */
    public String sValue="";
    
    /**
     * �����������, �� �� ����� ������������� �������� iKey � sValue
     *   
     * @param _m ������ �� MainActivity
     */
     public ListViewCheck (MainActivity _m) {
        _mainActivity = _m;    
        
        iKey = _mainActivity.mAdapter.iSumChecked; 
        String s = "";
        for (int i=0; i < _mainActivity._data.length; i++ ) {

            /* ��� ������:
             * ������������ ������ ������������ ��������������� 
             * � ������������ � ����������� (�����������) ���������� 
             */
        }
        sValue = s; 
    }
}
    