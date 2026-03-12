package com.qaflow.desktopdemo.repository;

import com.qaflow.desktopdemo.config.HibernateUtil;
import com.qaflow.desktopdemo.model.ToDo;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ToDoRepository {

    public List<ToDo> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from ToDo order by id", ToDo.class).list();
        }
    }

    public ToDo save(ToDo toDo) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(toDo);
            transaction.commit();
            return toDo;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public ToDo update(ToDo toDo) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            ToDo merged = (ToDo) session.merge(toDo);
            transaction.commit();
            return merged;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void delete(ToDo toDo) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(session.contains(toDo) ? toDo : session.merge(toDo));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }
}