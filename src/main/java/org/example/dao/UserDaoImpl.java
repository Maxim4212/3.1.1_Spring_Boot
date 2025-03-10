package org.example.dao;

import org.example.exception.UserNotFoundException;
import org.example.model.User;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(long id) {
        User user = entityManager.find(User.class, id);
        if (user == null) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        return user;
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void removeUser(long id) {
        User user = getUserById(id);
        if (user == null) {
            throw new UserNotFoundException("User with id " + id + " not found, cannot delete.");
        }
        entityManager.remove(user);
    }

    @Override
    public void updateUser(User user) {
        User existingUser = getUserById(user.getId());
        if (existingUser == null) {
            throw new UserNotFoundException("User with id " + user.getId() + " not found, cannot update.");
        }
        entityManager.merge(user);
    }
}
