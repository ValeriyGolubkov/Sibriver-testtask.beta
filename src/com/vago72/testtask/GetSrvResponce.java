package com.vago72.testtask;

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * <pre>
 * Запрос и получение данных с сервера.
 * Запрос данных по url, 
 * получение ответа в виде данных структуры json-объекта,
 * отображение информационного сообщения в случае RESPONSE ERROR,  
 * парсинг json-объекта в структуру ResponceJob,
 * компановка списка MainActivity.dataHeap,
 * 
 * @see ResponceJob
 * Набор реквизитов структуры json-данных описания job-позиции.
 * @see MainActivity
 * Точка входа прогарммы: инициализация свайпа, запрос данных с сервера.
 * </pre>
 *   
 * @author Valeriy Golubkov
 */
public class GetSrvResponce extends AsyncTask<String, Void, Void> 
{
    /**
     * <pre>
     * Интерфейс получения обратого ответа (кода "Server Response") от сервера. 
     * </pre>
     * Взведение состояния onSuccess() или onError() 
     * в зависимости от значения от значения кода GetSrvResponce.codeResponse
     */
    private MainActivity.NetworkOperationCallBack callBack;
    
    /**
     * <pre>
     * Response Code - ответ сервера о статусе завершения операции
     * {200-ок, 500-JobsErr, 404-JobErr, 400-JobDelErr}
     *
     * см <a href="http://testtask.beta.sibriver.com/">POSSIBLE RESPONSE CODES</a> 
     * и <a href="http://testtask.beta.sibriver.com/">EXAMPLE RESPONSE ERROR</a> в описании области API METHODS
     * для более полного представления об ошибках.
     * </pre>
     */
    private int codeResponse;
    
    /**
     * <pre>
     * Ссылка на активити (MainActivity). 
     * Реализация для возможности обращения и доступа к объектам класса активити.
     * </pre>
     */
     private MainActivity _mainActivity;

     /**
      * Конструктор
      * 
      * @param _m Ссылка на активити (вызывающий класс)
      * @param cb интерфейс обратного ответа от операции запроса данных у сервера 
      */
     public GetSrvResponce(MainActivity _m, MainActivity.NetworkOperationCallBack cb) 
     {
         _mainActivity = _m;    
         this.callBack = cb;
     }
    
    /**
     * Запрос json данных с сервера по адресу http ссылки  
     * 
     * @param url http адресс сервера
     * @return возвращает данные в виде json-оъекта
     */
     public JSONObject getJSONFromUrl(String url) {
         InputStream is = null;
         JSONObject jObj = null;
         String json = null;

         try {
             /* код опущен:
              * получение данных от сервера в виде строки,
              * формирование json объекта
              */

         } catch (Exception e) {
             Log.e("Error", "Error parsing data " + e.toString());
         }
         return jObj;
     }
    
     @Override
     protected void onProgressUpdate(Void... values) {
     }


     @Override
     protected void onPostExecute(Void result) {

         if (codeResponse==-1)
             callBack.onError( "Check your WiFi connection..." );
         else if (codeResponse==200)
             callBack.onSuccess("");
         else 
             callBack.onError( "ServerRespond: " + codeResponse + " Error" );

         // стоп на троббер, инициалиированный по свайпу
         _mainActivity.swipe.stop();
         // удаление элементов из массива, отмеченных в списке на удаление    
         _mainActivity.dataHeapMinus();
         // формирование и отображение кастомизированного списка (Job-позиций) 
         _mainActivity.listAdapterLoad();
     }

     /**
      * Парсинг json-структуры:
      * Производится в случае получения SERVER RESPONSE кода равного 200. 
      * Парсинг производится согласно набора данных ResponceJob.      
      *  
      * @param json строка Json-данных, полученная от сервера.    
      * 
      * @see ResponceJob 
      * Набор реквизитов структуры json-данных описания job-позиции
      *
      * см <a href="http://testtask.beta.sibriver.com/">RESPONSE</a> 
      * в описании области API METHODS
      * для более полного представления о возврашемой сервером строке json-данных.
      */
     public void parseJson(JSONObject json) {
         
         try {
             codeResponse = json.getInt("code");

             // для успешно принятых данных (code==200) парсинг набора данных
             if (json.getString("code").equals("200")) {
                 
                 _mainActivity.dataHeap.clear();
                 JSONArray posts = json.getJSONArray("response");
                 
                 // собственно, сам парсинг набора 
                 for (int i = 0; i < posts.length(); i++) {
                     JSONObject post = (JSONObject) posts.getJSONObject(i);

                     ResponceJob rJ1 = new ResponceJob();
                     rJ1.id = post.getInt("id");    
                     rJ1.name = post.getString("name");
                     rJ1.status = post.getInt("status");
                     rJ1.address = post.getString("address");
                     rJ1.lat = post.getString("lat");
                     rJ1.lon = post.getString("lon");
                     rJ1.created = post.getString("created");
                     _mainActivity.dataHeap.add(rJ1); 
                 }
             }
                 
         } catch (JSONException e) {
             e.printStackTrace();
         }
     }
         
     /**
      * Временная задержка. 
      * Актуальна для удерживания анимации троббера 
      * (при отладке приложения в режиме оффлайн)
      *    
      * @param i значение в миллисекундах.
      */
     public void sleep (int i) {
         try { Thread.sleep(i); }
         catch (Exception e) {}
     }

     @Override
     protected Void doInBackground(String... params) {

         JSONObject json = null;

         // вилка на хардкодное значение данных для тесирование программы в оффлайне
         Boolean offlineTestMode = false;

         if (!offlineTestMode) 
             // получение JSON строки от сервера по URL
             json = getJSONFromUrl(params[0]);

         else {
             // тестирование программы в оффлайне

             String // усечение строки даных 
                 sJson = "{\"response\":[{\"id     ...   статусы\"}],\"code\":200}";
             try {
                 json = new JSONObject(sJson);
             } catch (JSONException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }
             sleep(6000);
         }
         
         //parsing json data
         if (json == null) codeResponse = -1;
         else parseJson(json);
         
         return null;
     }
         
}
