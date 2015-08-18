package com.vago72.testtask;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Работа с преференс.
 * Реализация методов извлечения и сохранения строковых значений. 
 * @author Valeriy Golubkov
 */
public class PrefDeletedId {

    String APP_PREFERENCES = "PrefDeletedId";
    private SharedPreferences mSettings = null;

    /**
     * <pre>
     * Ссылка на активити. 
     * Реализация для возможности обращения и доступа к объектам класса активити.
     * </pre>
     */
    // вызывающий класс - MainActivity
    private Context _this = null; 
    
    public PrefDeletedId(Context a) { _this = a; }
    
    /**
     * Реализация метода сохранения строки в файле преференс.
     * 
     * @param param имя строковой перемнной
     * @param str значение строковой переменной
     */
    public void setValue(String param, String str) {

        /* код опушен:
         * инициализация SharedPreferences, и его Editor-объекта
         * занесение значения строки в editor,
         * сохранение строки в файле Preferences   
         */
    }

    /**
     * Реализация метода извлечения строки из соответствующего поля файла преференс
     * 
     * @param param имя строковой перемнной
     * @param def значение присваиваемое по умолчанию, если такой переменной в преференс нету  
     * @return значение строковой переменной
     */
    public String getString(String param, String def) {

        String ret = def;

        /* код опушен:
         * инициализация SharedPreferences с последующим чтением 
         * значения строки из строковой переменной  
         */
        
        return ret;
    }
    
};

