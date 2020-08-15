package com.hangman.repositories;

import com.hangman.entities.GameStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GameStatisticRepository
    extends JpaRepository<GameStatistic, Integer>, JpaSpecificationExecutor<GameStatistic> {}
