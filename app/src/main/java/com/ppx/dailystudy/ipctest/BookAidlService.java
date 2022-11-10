package com.ppx.dailystudy.ipctest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import com.ppx.dailystudy.ipctest.IBookManager;

/**
 * @Author: LuoXia
 * @Date: 2022/11/6 11:09
 * @Description:
 */
public class BookAidlService extends Service {

    private List<Book> bookList = new ArrayList<>();
    private String TAG = "BookAidlService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return iBookManager;
    }


    private final IBookManager.Stub iBookManager = new IBookManager.Stub() {
        @Override
        public List<Book> getBooks() throws RemoteException {
            synchronized (this) {
                if (bookList.size() == 0) {
                    bookList.add(new Book(2, "《嫌疑人X的献身》"));
                }
                return bookList;
            }
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            synchronized (this) {
                if (bookList == null) {
                    bookList = new ArrayList<>();
                }
                if (book == null) {
                    book = new Book();
                    book.setBookId(3);
                    book.setBookName("《百年孤独》");
                }

                bookList.add(book);
                //打印mBooks列表，观察客户端传过来的值
                Log.e(TAG, "invoking addBook() method , now the list is : " + bookList.toString());
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Book book = new Book(1, "《Android开发艺术探索》");
        bookList.add(book);
    }
}