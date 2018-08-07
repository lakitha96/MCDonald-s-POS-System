/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author lakitha
 */
public abstract class SuperRepositoryImpl<T, ID extends Serializable> implements SuperRepository<T, ID> {

    private Session session;
    private Class<T> entityClass;

    public SuperRepositoryImpl() {
        entityClass = (Class<T>) (((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]);
        System.out.println(entityClass);
    }

    @Override
    public void setSession(Session session) throws Exception {
        this.session = session;
    }

    @Override
    public boolean save(T t) throws Exception {
        return (session.merge(t) != null);
    }

    @Override
    public void delete(T t) throws Exception {
        session.delete(t);
    }

    @Override
    public void update(T t) throws Exception {
        session.merge(t);
    }

    @Override
    public List<T> findAll() throws Exception {
        System.err.println(entityClass.getName());
        return session.createQuery("FROM " + entityClass.getSimpleName()).list();

    }

    @Override
    public T findByID(ID id) throws Exception {
        return session.get(entityClass, id);
    }

    @Override
    public T findByName(String queryText) throws Exception {
        System.err.println(entityClass.getName());
        return (T) session.createQuery(queryText).list().get(0);
    }

    @Override
    public T findByIDS(String id) throws Exception {
        return session.get(entityClass, id);
    }

    @Override
    public ID queryText(String text) throws Exception {
        Long count = (Long) session.createQuery("select count(1) from  Orders")
                .getSingleResult();
        Integer i = count.intValue();
        ID id = (ID) i;
        return id;
        //return (ID) session.createSQLQuery(text);
    }

    @Override
    public List findByDate(String queryText) throws Exception {
        return session.createQuery(queryText).list();
    }

}
