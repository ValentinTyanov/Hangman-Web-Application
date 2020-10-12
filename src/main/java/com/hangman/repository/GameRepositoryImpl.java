package com.hangman.repository;

import com.hangman.model.Game;
import com.hangman.model.Game_;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GameRepositoryImpl implements GameRepository {

  private EntityManager entityManager;

  @Autowired
  public GameRepositoryImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public List<Game> findAll() {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Game> cq = cb.createQuery(Game.class);
    Root<Game> root = cq.from(Game.class);
    CriteriaQuery<Game> all = cq.select(root);
    return entityManager.createQuery(all).getResultList();
  }

  @Override
  public List<Game> findActiveGames() {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Game> criteria = cb.createQuery(Game.class);
    Root<Game> game = criteria.from(Game.class);
    List<Predicate> predicates = new ArrayList<>();
    predicates.add(cb.gt(game.get(Game_.attemptsLeft), 0));
    predicates.add(cb.like(game.get(Game_.wordInProgress), "%\\_%"));
    criteria = criteria.select(game).where(predicates.toArray(new Predicate[] {}));
    return entityManager.createQuery(criteria).getResultList();
  }

  @Override
  public Game findById(String id) {
    return entityManager.find(Game.class, id);
  }

  @Override
  public void create(Game game) {
    entityManager.persist(game);
  }

  @Override
  public void update(Game game) {
    entityManager.merge(game);
  }

  @Override
  public void deleteById(String id) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaDelete<Game> criteriaDelete = cb.createCriteriaDelete(Game.class);
    Root<Game> root = criteriaDelete.from(Game.class);
    criteriaDelete = criteriaDelete.where(cb.equal(root.get(Game_.id), id));
    entityManager.createQuery(criteriaDelete).executeUpdate();
  }

  @Override
  public boolean hasSolvedPuzzle(String id) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Game> criteria = cb.createQuery(Game.class);
    Root<Game> game = criteria.from(Game.class);
    List<Predicate> predicates = new ArrayList<>();
    predicates.add(cb.equal(game.get(Game_.id), id));
    predicates.add(cb.like(game.get(Game_.wordInProgress), "%\\_%"));
    criteria = criteria.select(game).where(predicates.toArray(new Predicate[] {}));
    return entityManager.createQuery(criteria).getResultList().isEmpty();
  }

  @Override
  public boolean hasFailedPuzzle(String id) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Game> criteria = cb.createQuery(Game.class);
    Root<Game> game = criteria.from(Game.class);
    List<Predicate> predicates = new ArrayList<>();
    predicates.add(cb.equal(game.get(Game_.id), id));
    predicates.add(cb.gt(game.get(Game_.attemptsLeft), 0));
    criteria = criteria.select(game).where(predicates.toArray(new Predicate[] {}));
    return entityManager.createQuery(criteria).getResultList().isEmpty();
  }
}
