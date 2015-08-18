package com.vago72.testtask;

import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Реализация обработки движения "свайп вниз".  
 * @author Valeriy Golubkov
 */
public class Swipe 
{
    private SwipeRefreshLayout mSwipeRefreshLayout = null;

    /**
     * <pre>
     * Ссылка на активити (MainActivity). 
     * Реализация для возможности обращения и доступа к объектам класса активити.
     * </pre>
     */
    private MainActivity _mainActivity;
    
    /**
     * Конструктор класса
     * @param _m указатель на объект активити (MainActivity)
     */
    public Swipe (MainActivity _m) {
        _mainActivity = _m;    
    }
   
    /**
     * <pre>
     * Инициализация обработчика свайпа.
     * Запуск соответствующего фонового процесса (запрос данных с сервера), 
     * и листенера на обработку фитбэка о завершении этого процесса.
     * см. MainActivity.onCreate()
     * 
     * @see MainActivity
     * </pre>
     * 
     */
    public void init() {
        //Initialize swipe to refresh view
        
        /* код опушен:
         * инициализация лейаута троббера,
         * инициализация листерена обработчика OnRefresh,
         * в котором производится запуск netRequestAction()      
         */
    }

    /**
     * Запуск запроса данных у сервера со стороны клиента.
     */
    private void netRequestAction() {
        //Refreshing data on server
        new GetSrvResponce(_mainActivity, 
                new MainActivity.NetworkOperationCallBack()
        {
            /**
             * все ок - ответ сервера код 200. 
             */
            public void onSuccess(String str){
                // все ок - никаких действий не требуется
            }
            /**
             * <pre>
             * код операции сервера != 200:
             *    это код ошибки {500,404,400}, 
             *    либо -1 - json ответ от сервера не получен
             * </pre>
             * см <a href="http://testtask.beta.sibriver.com/">POSSIBLE RESPONSE CODES</a> 
             * и <a href="http://testtask.beta.sibriver.com/">EXAMPLE RESPONSE ERROR</a> в описании области API METHODS
             * для более полного представления об ошибках.
             */ 
            public void onError(String str) {
                // вывод сообщения на активити
                _mainActivity.showInfo(str);
            }
        
        }).execute(_mainActivity.feedUrl);
    }
    
    /**
     * Завершение работы троббера. см. вызов GetSrvResponce.onPostExecute()
     * @see GetSrvResponce
     */
    public void stop () {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
    
}

