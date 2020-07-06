import static org.assertj.core.api.Assertions.assertThat;
import com.hangman.entities.Game;
import com.hangman.repositories.GameRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GameRepositoryTests {

  private GameRepositoryImpl gameRepository = new GameRepositoryImpl();

  @Test
  public void shouldAdd_Game_To_Collection_And_Retrieve_Game_ById() {
    Game game = new Game();
    game.setId("asdfg2334");
    gameRepository.addGame(game);
    assertThat(gameRepository.getGame("asdfg2334")).isEqualTo(game);
  }
}
