/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.util.List;

/**
 *
 * @author Aymen
 */
public interface Idao<T> {
    void add (T t);
    void deleteById(int id);
    void updateAllById(T t);
    List<T> displayAll();
    List<T> displayByAll(T t);
    T displayById(int id);
}
