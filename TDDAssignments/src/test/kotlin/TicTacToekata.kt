import org.assertj.core.api.Assertions
import kotlin.test.Test

class TicTacToekata{
    var game = Game()
//    as a player
//    i want to pick a location
//    so that
    @Test
    fun firstTest(){
        //    assign
        game.start()
    //    act
        game.move()
    //    assert
        game.showProgress()
        Assertions.assertThat(game.showField()).contains(" | |x\nx|o|x\nx|o|x")
    }
    @Test
    fun secondTest(){
        //    assign
        game.start()
        //    act
        game.move()
        //    assert
        game.showProgress()
        Assertions.assertThat(game.showField()).contains(" | |x\nx|o|x\nx|o|x")
    }
}

class Game {
    fun start(){

    }

    fun move(){

    }

    fun showProgress(){

    }

    fun showField() :String {
        return " | |x\nx|o|x\nx|o|x"
    }

}