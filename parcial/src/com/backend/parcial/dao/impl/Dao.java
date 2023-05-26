package com.backend.parcial.dao.impl;


import java.util.List;

public interface Dao<P> {
    P registrar(P p);

    List<P> listar();
}



