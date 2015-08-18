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
 * ������ � ��������� ������ � �������.
 * ������ ������ �� url, 
 * ��������� ������ � ���� ������ ��������� json-�������,
 * ����������� ��������������� ��������� � ������ RESPONSE ERROR,  
 * ������� json-������� � ��������� ResponceJob,
 * ���������� ������ MainActivity.dataHeap,
 * 
 * @see ResponceJob
 * ����� ���������� ��������� json-������ �������� job-�������.
 * @see MainActivity
 * ����� ����� ���������: ������������� ������, ������ ������ � �������.
 * </pre>
 *   
 * @author Valeriy Golubkov
 */
public class GetSrvResponce extends AsyncTask<String, Void, Void> 
{
    /**
     * <pre>
     * ��������� ��������� �������� ������ (���� "Server Response") �� �������. 
     * </pre>
     * ��������� ��������� onSuccess() ��� onError() 
     * � ����������� �� �������� �� �������� ���� GetSrvResponce.codeResponse
     */
    private MainActivity.NetworkOperationCallBack callBack;
    
    /**
     * <pre>
     * Response Code - ����� ������� � ������� ���������� ��������
     * {200-��, 500-JobsErr, 404-JobErr, 400-JobDelErr}
     *
     * �� <a href="http://testtask.beta.sibriver.com/">POSSIBLE RESPONSE CODES</a> 
     * � <a href="http://testtask.beta.sibriver.com/">EXAMPLE RESPONSE ERROR</a> � �������� ������� API METHODS
     * ��� ����� ������� ������������� �� �������.
     * </pre>
     */
    private int codeResponse;
    
    /**
     * <pre>
     * ������ �� �������� (MainActivity). 
     * ���������� ��� ����������� ��������� � ������� � �������� ������ ��������.
     * </pre>
     */
     private MainActivity _mainActivity;

     /**
      * �����������
      * 
      * @param _m ������ �� �������� (���������� �����)
      * @param cb ��������� ��������� ������ �� �������� ������� ������ � ������� 
      */
     public GetSrvResponce(MainActivity _m, MainActivity.NetworkOperationCallBack cb) 
     {
         _mainActivity = _m;    
         this.callBack = cb;
     }
    
    /**
     * ������ json ������ � ������� �� ������ http ������  
     * 
     * @param url http ������ �������
     * @return ���������� ������ � ���� json-������
     */
     public JSONObject getJSONFromUrl(String url) {
         InputStream is = null;
         JSONObject jObj = null;
         String json = null;

         try {
             /* ��� ������:
              * ��������� ������ �� ������� � ���� ������,
              * ������������ json �������
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

         // ���� �� �������, ����������������� �� ������
         _mainActivity.swipe.stop();
         // �������� ��������� �� �������, ���������� � ������ �� ��������    
         _mainActivity.dataHeapMinus();
         // ������������ � ����������� ������������������ ������ (Job-�������) 
         _mainActivity.listAdapterLoad();
     }

     /**
      * ������� json-���������:
      * ������������ � ������ ��������� SERVER RESPONSE ���� ������� 200. 
      * ������� ������������ �������� ������ ������ ResponceJob.      
      *  
      * @param json ������ Json-������, ���������� �� �������.    
      * 
      * @see ResponceJob 
      * ����� ���������� ��������� json-������ �������� job-�������
      *
      * �� <a href="http://testtask.beta.sibriver.com/">RESPONSE</a> 
      * � �������� ������� API METHODS
      * ��� ����� ������� ������������� � ����������� �������� ������ json-������.
      */
     public void parseJson(JSONObject json) {
         
         try {
             codeResponse = json.getInt("code");

             // ��� ������� �������� ������ (code==200) ������� ������ ������
             if (json.getString("code").equals("200")) {
                 
                 _mainActivity.dataHeap.clear();
                 JSONArray posts = json.getJSONArray("response");
                 
                 // ����������, ��� ������� ������ 
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
      * ��������� ��������. 
      * ��������� ��� ����������� �������� �������� 
      * (��� ������� ���������� � ������ �������)
      *    
      * @param i �������� � �������������.
      */
     public void sleep (int i) {
         try { Thread.sleep(i); }
         catch (Exception e) {}
     }

     @Override
     protected Void doInBackground(String... params) {

         JSONObject json = null;

         // ����� �� ���������� �������� ������ ��� ����������� ��������� � ��������
         Boolean offlineTestMode = false;

         if (!offlineTestMode) 
             // ��������� JSON ������ �� ������� �� URL
             json = getJSONFromUrl(params[0]);

         else {
             // ������������ ��������� � ��������

             String // �������� ������ ����� 
                 sJson = "{\"response\":[{\"id     ...   �������\"}],\"code\":200}";
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
