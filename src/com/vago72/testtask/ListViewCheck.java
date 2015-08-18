package com.vago72.testtask;

/**
 * Класс анализатор для упрощения восприятия 
 * отмеченных (выделенных) элементов кастомизированного списка.
 * <pre>
 * int iKey - количество выделенных позиций скписка
 * String sValue - трока перечисления идентификаторов выделенных элементов
 * 
 * Шаблон вызова:
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
     * Ссылка на активити (MainActivity). 
     * Реализация для возможности обращения и доступа к объектам класса активити.
     * </pre>
     */
    private MainActivity _mainActivity;
    
    /**
     * Количество выделенных позиций скписка
     */
    public int iKey = 0;
    
    /**
     * Строка перечисления идентификаторов выделенных элементов  
     */
    public String sValue="";
    
    /**
     * Конструктор, он же метод инициализации значений iKey и sValue
     *   
     * @param _m ссылка на MainActivity
     */
     public ListViewCheck (MainActivity _m) {
        _mainActivity = _m;    
        
        iKey = _mainActivity.mAdapter.iSumChecked; 
        String s = "";
        for (int i=0; i < _mainActivity._data.length; i++ ) {

            /* код опущен:
             * формирование строки перечисления идентификаторов 
             * в соответствии с выделенными (отмеченными) элементами 
             */
        }
        sValue = s; 
    }
}
    