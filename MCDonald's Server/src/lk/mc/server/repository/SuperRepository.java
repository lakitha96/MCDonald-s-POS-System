/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.repository;

import java.util.List;
import lk.mc.common.dto.CustomerDTO;
import org.hibernate.Session;

/**
 *
 * @author lakitha
 */
public interface SuperRepository<T, ID> {

    public void setSession(Session session) throws Exception;

    public boolean save(T t) throws Exception;

    public void delete(T t) throws Exception;

    public void update(T t) throws Exception;
    
    public T findByName(String queryText) throws Exception;
    
    public T findByID(ID id) throws Exception;
    
    public T findByIDS(String id) throws Exception;
    
    public List<T> findAll() throws Exception;
    
    public ID queryText(String query) throws Exception;
    
    public List findByDate(String queryText) throws Exception;

}
