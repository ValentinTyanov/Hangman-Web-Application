package com.hangman.repositories;

import com.hangman.entities.UnusedLetter;
import com.hangman.entities.UnusedLetter_;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UnusedLetterRepositoryImpl implements LetterRepository<UnusedLetter> {

  private EntityManager entityManager;

  @Autowired
  public UnusedLetterRepositoryImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public List<UnusedLetter> findAll() {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<UnusedLetter> criteria = cb.createQuery(UnusedLetter.class);
    Root<UnusedLetter> root = criteria.from(UnusedLetter.class);
    criteria = criteria.select(root);
    return entityManager.createQuery(criteria).getResultList();
  }

  @Override
  public UnusedLetter findById(int id) {
    return entityManager.find(UnusedLetter.class, id);
  }

  @Override
  public void add(UnusedLetter letter) {
    entityManager.persist(letter);
  }

  @Override
  public void update(UnusedLetter letter) {
    entityManager.merge(letter);
  }

  @Override
  public void deleteById(int id) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaDelete<UnusedLetter> criteriaDelete = cb.createCriteriaDelete(UnusedLetter.class);
    Root<UnusedLetter> root = criteriaDelete.from(UnusedLetter.class);
    criteriaDelete = criteriaDelete.where(cb.equal(root.get(UnusedLetter_.id), id));
    entityManager.createQuery(criteriaDelete).executeUpdate();
  }
}
