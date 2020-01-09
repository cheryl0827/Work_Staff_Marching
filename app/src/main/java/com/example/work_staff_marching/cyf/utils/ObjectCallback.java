package com.example.work_staff_marching.cyf.utils;

import java.lang.reflect.Type;

public interface ObjectCallback<T>  {

    T convert(String response, Type type);
}
