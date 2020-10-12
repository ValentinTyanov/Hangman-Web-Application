// package com.hangman.ws;
//
// import com.hangman.RankingPortType;
// import com.hangman.rankingschema.Ranking;
// import com.hangman.repository.RankingRepository;
// import java.util.ArrayList;
// import java.util.List;
// import javax.jws.WebMethod;
// import javax.jws.WebResult;
// import javax.jws.WebService;
// import org.springframework.beans.factory.annotation.Autowired;
//
// @WebService
// public class RankingWsImpl implements RankingPortType {
//
//  @Autowired private RankingRepository rankingRepository;
//
//  @Override
//  @WebMethod
//  @WebResult(name = "ResponseMessage")
//  public List<Ranking> getTopTenLastMonthRanking() {
//    // TODO Auto-generated method stub
//    return null;
//  }
//
//  @Override
//  @WebMethod
//  @WebResult(name = "ResponseMessage")
//  public List<Ranking> getTopTenRanking() {
//    List<com.hangman.model.Ranking> originalRankings =
//        rankingRepository.findTop10ByOrderByHighScoreDesc();
//    List<Ranking> webServiceRankings = new ArrayList<>();
//
//    for (com.hangman.model.Ranking originalRanking : originalRankings) {
//      String alias = originalRanking.getAlias();
//      int score = originalRanking.getHighScore();
//      Ranking newRanking = new Ranking();
//      newRanking.setAlias(alias);
//      newRanking.setScore(score);
//      webServiceRankings.add(newRanking);
//    }
//    return webServiceRankings;
//  }
// }
