// IBookManager.aidl
package com.ppx.dailystudy.androiddevelopartisticexplorations.chap02ipc.aidl;

import com.ppx.dailystudy.androiddevelopartisticexplorations.chap02ipc.aidl.Book;
import java.util.List;

// Declare any non-default types here with import statements

interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

     List<Book> getBookLst();
     void addNook(in Book book);
}