package com.vago72.testtask;

/**
 * <pre>
 * <b>Типовое решение.</b>
 * Набор данных для кастомизации списка Job-позиций.
 * Набор реквизитов структуры json-данных описания job-позиции 
 *
 * Смотри область <a href="http://testtask.beta.sibriver.com/">
 * RESPONSE DESCRIPTION</a> для более полного представления о наборе.
 *
 * </pre>
 * @author Valeriy Golubkov
 */
public class ListViewData {

    public Integer id;    
    public String name;     
    public Integer status; 
    public String address;    
    public String lat;    
    public String lon;    
    public String created;
    
    /**
     * флаг признака выделения элемента
     */
    public boolean chkVal;

    /**
     * <pre>
     * В настоящей реализации не задествована.
     * 
     * Ссылка на активити (MainActivity). 
     * Релизация для возможности обращения и доступа к объектам класса активити.
     * (для случая условной инициализации в зависимости от значений внешних флагов) 
     * </pre>
     */
    private MainActivity _MainActivity;

    /**
     * Конструктор
     * @param _m ссылка на вызывающий класс (активити)
     */
    public ListViewData(MainActivity _m) {
        _MainActivity = _m;
    }
    
    /**
     * Добавление элемента ResponceJob в массив элементов
     * @param rsp добавляемый элемент ResponceJob 
     */
    public void put(ResponceJob rsp) {

        this.id = rsp.id;    
        this.name = rsp.name;     
        this.status = rsp.status; 
        this.address = rsp.address;    
        this.lat = rsp.lat;    
        this.lon = rsp.lon;    
        this.created = rsp.created;
        
    }


};
