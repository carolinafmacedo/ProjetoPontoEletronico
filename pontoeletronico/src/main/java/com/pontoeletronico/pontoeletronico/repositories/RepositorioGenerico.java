package com.pontoeletronico.pontoeletronico.repositories;

import java.sql.SQLException;
import java.util.List;

public interface RepositorioGenerico<T, ID> {
	
    void create(T entity) throws SQLException;
    
    List<T> readAll() throws SQLException;
    
    T read(ID id) throws SQLException;
    
    void update(T entity) throws SQLException;
    
    void delete(ID id) throws SQLException;
    
}
