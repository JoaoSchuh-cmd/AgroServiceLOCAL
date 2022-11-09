package com.example.a1agroservice.dao;

import java.util.ArrayList;

public interface GenericDao<Objeto> {
    boolean insert(Objeto obj);
    boolean update(long oldObjId, Objeto obj);
    boolean delete(Objeto obj);
    ArrayList<Objeto> getAll();
    Objeto getById(int id);
}
