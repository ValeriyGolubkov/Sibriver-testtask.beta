package com.vago72.testtask;

/**
 * Набор реквизитов структуры json-данных описания job-позиции
 * Смотри область <a href="http://testtask.beta.sibriver.com/">
 * RESPONSE DESCRIPTION</a> для более полного представления о наборе.
 * 
 * @author Valeriy Golubkov
 */
public class ResponceJob {

    Integer id;     // 7
    String  name;   // "Do something"
    Integer status; // {1;4}
    String  address;// "Tomsk, Kievskaya st. 93"
    String  lat;    // or Float    23.3436324234
    String  lon;    // or Float    56.343243424
    String  created;// "17.07.2015"

}