package com.hangman.repository;

import com.hangman.model.GameStatistic;
import com.hangman.model.GameStatistic_;
import java.time.LocalDate;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class GameStatisticSpecifications {
  public static Specification<GameStatistic> highScoresSince(LocalDate localDate) {
    return (root, cq, cb) -> {
      Predicate equalPredicate =
          cb.greaterThanOrEqualTo(root.<LocalDate>get(GameStatistic_.gameDate), localDate);

      cq.orderBy(cb.desc(root.get(GameStatistic_.score)));
      return equalPredicate;
    };
  }
}
