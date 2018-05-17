import org.assertj.core.api.Assertions
import kotlin.test.Test

class WrapTest{
    @Test
    fun wrapsShortLine() {
        //assign
        var wrapper = Wrapper("This is a string",10)

        //act
        //assert
        Assertions.assertThat(wrapper.wrap()).contains("This is a\nstring")
    }
    @Test
    fun splitsAllWords() {
        //assign
        var wrapper = Wrapper("This is a string",10)

        //act
        //assert
        Assertions.assertThat(wrapper.wrap()).contains("This is a\nstring")
    }
    @Test
    fun wrapsLine() {
        //assign
        var wrapper = Wrapper("This is a longer string that needs to be cut more",10)

        //act
        //assert
        Assertions.assertThat(wrapper.wrap()).contains("This is a\nlonger\nstring\nthat needs\nto be cut\nmore")
    }
    @Test
    fun cutsLongWord() {
        //assign
        var wrapper = Wrapper("This is a longerstringthat needs to be cut more",10)

        //act
        //assert
        Assertions.assertThat(wrapper.wrap()).contains("This is a\nlongerstri\nngthat\nneeds to\nbe cut\nmore")
    }
    @Test
    fun cutsLongWordColumnWidthFive() {
        //assign
        var wrapper = Wrapper("This is a longerstringthat needs to be cut more",5)

        //act
        //assert
        Assertions.assertThat(wrapper.wrap()).contains("This\nis a\nlonge\nrstri\nngtha\nt\nneeds\nto be\ncut\nmore")
    }
//    @Test
//    fun testTheWordChunker(){
//        //assign
//        val wrapper = Wrapper("verylongword",5)
//        //act
//        val wordArray =
//        //assert
//        Assertions.assertThat(wrapper.longWordChunking("verylongword"))
//    }
}

class Wrapper constructor( sentence: String , columnNumber: Int ) {
    val sentence = sentence
    val columnNumber = columnNumber
    fun wrap() : String {
        val wordArray = sentence.split(" ")
        val lineArray = mutableListOf<String>()
        for( index in wordArray.indices ) {

            if ( lineArray.size == 0 ) {
                lineArray.addAll( wordArray[index].chunked( columnNumber ) )
            } else if ( lineArray.last().length + 1 + wordArray[index].length <= columnNumber ){
                lineArray[lineArray.lastIndex] = lineArray.last() + " " + wordArray[index]
            } else if ( wordArray[index].length > columnNumber ) {
                lineArray.addAll( wordArray[index].chunked( columnNumber ) )
            } else {
                lineArray.add( wordArray[index] )
            }
        }
        return lineArray.joinToString("\n")
    }
}
