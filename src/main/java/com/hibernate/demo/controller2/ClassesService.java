package com.hibernate.demo.controller2;

import com.hibernate.demo.domain.Province;
import com.hibernate.demo.domain2.Classes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author wanli zhou
 * @created 2017-10-09 11:11 PM.
 */
@Service
@Transactional(value = "secondTransaction")
public class ClassesService {

    @PersistenceContext(unitName = "secondPersistenceUnit", name = "secondEntityManager")
    private EntityManager em;


    public Classes getClassesById(Integer id) {
        return em.createQuery("select c FROM Classes c where c.id = :id", Classes.class)
                .setParameter("id", id)
                .getResultList().stream().findFirst().orElse(null);
    }
}
