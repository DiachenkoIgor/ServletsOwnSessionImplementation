package DAO.impl;

import DAO.ProductDAO;
import Entities.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryProductDAO implements ProductDAO {
    private ConcurrentHashMap<Long,Product> productHashMap;

    public MemoryProductDAO() {
productHashMap=new ConcurrentHashMap<>();
productHashMap.put((long)1,new Product(1,"Test1",100));
productHashMap.put((long)2,new Product(2,"Test2",200));
productHashMap.put((long)3,new Product(3,"Test3",300));
productHashMap.put((long)4,new Product(4,"Test4",400));
        }

    public Product selectProductById(long id) {
return productHashMap.get(id);
    }

    public ArrayList<Product> selectAllProducts() {
        ArrayList<Product> arrayList=new ArrayList<>();
        Set<Long> set=productHashMap.keySet();
        Iterator<Long> iterator=set.iterator();
        while (iterator.hasNext()){
            arrayList.add(productHashMap.get(iterator.next()));
        }
        return arrayList;
    }
}
