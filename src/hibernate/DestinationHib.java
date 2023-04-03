package hibernate;

import Personel.Destination;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DestinationHib {
    EntityManager entityManager = null;
    EntityManagerFactory emf = null;

    public DestinationHib(EntityManagerFactory entityManagerFactory) {
        this.emf = entityManagerFactory;
    }
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void createDestination(Destination destination) {
        entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(destination);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) entityManager.close();
        }
    }

    public void updateDestination(Destination destination) {
        entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(destination);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) entityManager.close();
        }
    }

    public void deleteDestination(Destination destination){
        entityManager.getTransaction().begin();
        entityManager.remove(destination);
        entityManager.getTransaction().commit();
    }

    public Destination getDestinationById(int id) {
        entityManager = emf.createEntityManager();
        Destination destination = null;
        try {
            entityManager.getTransaction().begin();
            destination = entityManager.find(Destination.class, id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("No such cargo by given Id");
        }
        return destination;
    }

    public List<Destination> getAllDestination() {
        entityManager = emf.createEntityManager();
        try {
            CriteriaQuery<Object> query = entityManager.getCriteriaBuilder().createQuery();
            query.select(query.from(Destination.class));
            Query q = entityManager.createQuery(query);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return new ArrayList<>();
    }
}
