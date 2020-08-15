package com.hangman.repositories;

import com.hangman.entities.GameStatistic;
import com.hangman.entities.GameStatistic_;
import java.util.Date;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class GameStatisticSpecifications {
  public static Specification<GameStatistic> highScoresSince(Date date) {
    return (root, cq, cb) -> {
      Predicate equalPredicate = cb.greaterThanOrEqualTo(root.<Date>get(GameStatistic_.date), date);
      cq.orderBy(cb.desc(root.get(GameStatistic_.score)));
      return equalPredicate;
    };
  }
}
