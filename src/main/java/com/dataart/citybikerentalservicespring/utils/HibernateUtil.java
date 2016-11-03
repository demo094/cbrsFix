package com.dataart.citybikerentalservicespring.utils;

import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by mkrasowski on 21.09.2016.
 */
public class HibernateUtil {

    public static <T> List<T> listAndCast(Query query){
        @SuppressWarnings("unchecked")
                List<T> list = query.list();
        return list;
    }
}
