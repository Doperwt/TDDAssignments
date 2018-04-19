import org.assertj.core.api.Assertions
import kotlin.test.Test

class BankOCRKata {
    //Assign
    var reader = AccountNumberReader()
    @Test
    fun oneIsOne(){
        //act
        val number = reader.transform("   \n  |\n  |")
        //assert
        Assertions.assertThat(number).isEqualTo(1)
    }

    @Test
    fun twoIsTwo(){
        val number  = reader.transform(" _ \n _|\n|_ ")
        Assertions.assertThat(number).isEqualTo(2)
    }

    @Test
    fun WorksWithTwoNumbers(){
        //act
        val doubleNumber = reader.transform(
                "    _ \n" +
                "  | _|\n" +
                "  ||_ ")
        //Assert
        Assertions.assertThat(doubleNumber).isEqualTo(12)
    }
}

class AccountNumberReader {
    fun transform(read:String) : Int {
        var lines = read.split("\n")
        val slicedLine = mutableListOf<String>()
        var outcome = 0
        for (index in lines.indices) {
            slicedLine.add(lines[index].slice(IntRange(start = 0, endInclusive = 2)))
        }
        if (slicedLine.joinToString(separator = "\n") == "   \n  |\n  |") {
            outcome = 1
        }
        if (slicedLine.joinToString(separator = "\n") == " _ \n _|\n|_ ") {
            outcome = 2
        }

        var joined = slicedLine.joinToString(separator = "\n")



        return outcome
    }
}