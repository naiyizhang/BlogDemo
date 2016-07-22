// ICalcInterface.aidl
package com.zhg.demo.aidl;

// Declare any non-default types here with import statements

interface ICalcInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);


    int add(int a,int b);
    int sub(int a,int b);
}
