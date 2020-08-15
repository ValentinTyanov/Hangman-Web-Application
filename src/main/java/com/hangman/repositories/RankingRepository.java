package com.hangman.repositories;

import com.hangman.entities.Ranking;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, String> {

  Ranking findByAlias(String alias);

  List<Ranking> findTop10ByOrderByHighScoreDesc();
}
