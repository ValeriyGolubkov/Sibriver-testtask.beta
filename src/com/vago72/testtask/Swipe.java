package com.vago72.testtask;

import android.support.v4.widget.SwipeRefreshLayout;

/**
 * ���������� ��������� �������� "����� ����".  
 * @author Valeriy Golubkov
 */
public class Swipe 
{
    private SwipeRefreshLayout mSwipeRefreshLayout = null;

    /**
     * <pre>
     * ������ �� �������� (MainActivity). 
     * ���������� ��� ����������� ��������� � ������� � �������� ������ ��������.
     * </pre>
     */
    private MainActivity _mainActivity;
    
    /**
     * ����������� ������
     * @param _m ��������� �� ������ �������� (MainActivity)
     */
    public Swipe (MainActivity _m) {
        _mainActivity = _m;    
    }
   
    /**
     * <pre>
     * ������������� ����������� ������.
     * ������ ���������������� �������� �������� (������ ������ � �������), 
     * � ��������� �� ��������� ������� � ���������� ����� ��������.
     * ��. MainActivity.onCreate()
     * 
     * @see MainActivity
     * </pre>
     * 
     */
    public void init() {
        //Initialize swipe to refresh view
        
        /* ��� ������:
         * ������������� ������� ��������,
         * ������������� ��������� ����������� OnRefresh,
         * � ������� ������������ ������ netRequestAction()      
         */
    }

    /**
     * ������ ������� ������ � ������� �� ������� �������.
     */
    private void netRequestAction() {
        //Refreshing data on server
        new GetSrvResponce(_mainActivity, 
                new MainActivity.NetworkOperationCallBack()
        {
            /**
             * ��� �� - ����� ������� ��� 200. 
             */
            public void onSuccess(String str){
                // ��� �� - ������� �������� �� ���������
            }
            /**
             * <pre>
             * ��� �������� ������� != 200:
             *    ��� ��� ������ {500,404,400}, 
             *    ���� -1 - json ����� �� ������� �� �������
             * </pre>
             * �� <a href="http://testtask.beta.sibriver.com/">POSSIBLE RESPONSE CODES</a> 
             * � <a href="http://testtask.beta.sibriver.com/">EXAMPLE RESPONSE ERROR</a> � �������� ������� API METHODS
             * ��� ����� ������� ������������� �� �������.
             */ 
            public void onError(String str) {
                // ����� ��������� �� ��������
                _mainActivity.showInfo(str);
            }
        
        }).execute(_mainActivity.feedUrl);
    }
    
    /**
     * ���������� ������ ��������. ��. ����� GetSrvResponce.onPostExecute()
     * @see GetSrvResponce
     */
    public void stop () {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
    
}

