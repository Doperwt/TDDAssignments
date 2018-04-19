//import org.assertj.core.api.Assertions
//import kotlin.test.Test
//
//class WrapTest{
//    @Test
//    fun wrapsShortLine() {
//        //assign
//        var wrapper = Wrapper("This is a string")
//
//        //act
//        //assert
//        Assertions.assertThat(wrapper.wrap()).contains("This is\n a string")
//    }
//    @Test
//    fun splitsAllWords() {
//        //assign
//        var wrapper = Wrapper("This is a string")
//
//        //act
//        //assert
//        Assertions.assertThat(wrapper.wrap()).contains("This\nis\na\nstring")
//    }
//    @Test
//    fun wrapsLine() {
//        //assign
//        var wrapper = Wrapper("This is a longer string that needs to be cut more")
//
//        //act
//        //assert
//        Assertions.assertThat(wrapper.wrap()).contains("This is a\nlonger string\n that needs\n to be cut\n more")
//    }
//}

//class Wrapper(sentence:String) {
//    val sentence = sentence
//    fun wrap() : String {
//        val cutString = sentence.split( ' ')
//        var reassignedString = mutableListOf<String>()
//        for(index in cutString.indices){
//            if(cutString[index].length > 10){
//
//            } else if(cutString[index].length < 10 && ) {
//
//            }
//        }
//
//        return reassignedString.join("\n")
//    }
//}