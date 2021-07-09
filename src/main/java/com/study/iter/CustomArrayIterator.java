package com.study.iter;

import java.util.Iterator;

/**
 * Итератор
 * @param <T> тип объекта, массив которых будет использоваться в итераторе
 */
public class CustomArrayIterator<T> implements Iterator<T> {
    private final T[][] arr; // двумерный массив объектов
    private int indexFirst = 0; // индекс первого ряда
    private int indexSecond = 0; // индекс второго ряда

    public CustomArrayIterator(T[][] arr) {
        this.arr = arr;
    }

    @Override
    public T next() {
        if (arr.length == 0) { //если массив пустой возвращается null
            return null;
        }

        // если индекс текущего элемента в массиве не выходит за пределы текущего подмассива, то вернуть этот элемент
        // и увеличить значение индекса
        if ((arr[indexFirst].length > indexSecond)) {
            return arr[indexFirst][indexSecond++];
        }

        return null;
    }

    @Override
    public boolean hasNext() {
        if (indexSecond == arr[indexFirst].length) { // если индекс текущего элемента равен размеру подмассива
            indexFirst++; // переключиться на следующий подмассив
            indexSecond = 0; //индекс элемента обнулить
        }

        return (indexFirst < arr.length);
    }
}
