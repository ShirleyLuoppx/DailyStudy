// IMyTestAidl.aidl
package com.ppx.dailystudy;

// Declare any non-default types here with import statements

//通过File-new-AIDL 来新建的aidl文件会有一个默认的basicTypes方法，参数包含的就是aidl所能支持的基本数据类型
//除此之外，aidl还支持 CharSequence, List & Map类型，
interface IMyTestAidl {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
            //short 、 byte 、 char 、charSequence
}