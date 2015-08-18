package com.vago72.testtask;

import android.widget.CheckBox;
import android.widget.TextView;

/**
 * <pre>
 * <b>Типовое решение.</b>
 * Поэлементная инициализация кастомизированного списка Job-позиций
 * в соответствии с значениями набора данных.
 * Набор данных:
 *         ListViewData, 
 *         значения полей txtT{1;4} - компоновка комбинаций полей в зависимости от значения statuses
 *
 *  @see ListViewData
 * </pre>
 * Смотри область <a href="http://testtask.beta.sibriver.com/">
 * RESPONSE DESCRIPTION</a> для более полного представления о наборе.
 *
 * @author Valeriy Golubkov
 */
class ListViewDataHolder
{
        TextView id;    
        TextView name;     
        TextView status;  
        TextView address; 
        TextView lat;    
        TextView lon;    
        TextView created;
        CheckBox chk;
        
        /**
         * Поле txtT1 - лексема со знчением "вопрос"; 
         * Цветовая гамма @color/dark_golden_rod
         */
        TextView txtT1;

        /**
         * Поле txtT2 - лексема со знчением "новая"; 
         * Цветовая гамма @color/green
         */
        TextView txtT2;

        /**
         * Поле txtT3 -лексема со знчением "в работе"; 
         * Цветовая гамма @color/dark_goldenrod
         */
        TextView txtT3;

        /**
         * Поле txtT3 - лексема со знчением "выполнено/отменено"; 
         * Цветовая гамма @color/fire_brick
         */
        TextView txtT4;

}
