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
        Assertions.assertThat(number).isEqualTo("1")
    }

    @Test
    fun twoIsTwo(){
        val number  = reader.transform(" _ \n _|\n|_ ")
        Assertions.assertThat(number).isEqualTo("2")
    }

    @Test
    fun worksWithTwoNumbers(){
        //act
        val doubleNumber = reader.transform(
                        "    _ \n" +
                        "  | _|\n" +
                        "  ||_ ")
        //Assert
        Assertions.assertThat(doubleNumber).isEqualTo("12")
    }
    @Test
    fun worksWithMultipleNumbers(){
        //act
        val longNumber = reader.transform(
                        "    _  _  _  _  _  _     _ \n"+
                        "|_||_|| || ||_   |  |  ||_ \n"+
                        "  | _||_||_||_|  |  |  | _|\n"
        )
        //assert
        Assertions.assertThat(longNumber).isEqualTo("490067715")
    }
    @Test
    fun worksWithMistakes(){
        //act
        val errorNumber = reader.transform(
                        "    _  _     _  _  _  _  _ \n"+
                        "  | _| _||_| _ |_   ||_||_|\n"+
                        "  ||_  _|  | _||_|  ||_| _ \n"
        )
        //assert
        Assertions.assertThat(errorNumber).isEqualTo("1234?678? ILL")

    }
    @Test
    fun worksWithCheckSum(){
        //act
        val checkSumNoFail = reader.transform(
                        " _  _     _  _        _  _ \n"+
                        "|_ |_ |_| _|  |  ||_||_||_ \n"+
                        "|_||_|  | _|  |  |  | _| _|"
        )
        //assert
        Assertions.assertThat(checkSumNoFail).isEqualTo("664371495")
    }
    @Test
    fun fixBrokenSix(){
        //act
        val unbrokenReturn = reader.transform(
                        " _        _  _        _  _ \n"+
                        "|_ |_ |_| _|  |  ||_||_||_ \n"+
                        "|_||_|  | _|  |  |  | _| _|"
        )
        //assert
        Assertions.assertThat(unbrokenReturn).isEqualTo("664371495")
    }
    @Test
    fun fixBrokenFour(){
        //act
        val unbrokenReturn = reader.transform(
                        " _  _     _  _        _  _ \n"+
                        "|_ |_  _| _|  |  ||_||_||_ \n"+
                        "|_||_|  | _|  |  |  | _| _|"
        )
        //assert
        Assertions.assertThat(unbrokenReturn).isEqualTo("664371495")
    }
    @Test
    fun fixBrokenOne(){
        //act
        val unbrokenReturn = reader.transform(
                        " _  _     _  _        _  _ \n"+
                        "|_ |_ |_| _|  | _||_||_||_ \n"+
                        "|_||_|  | _|  |  |  | _| _|"
        )
        //assert
        Assertions.assertThat(unbrokenReturn).isEqualTo("664371495")
    }
    @Test
    fun doesNotWorksWithCheckSum(){
        //act
        val checkSumFail = reader.transform(
                        " _  _  _  _  _  _  _  _    \n"+
                        "| || || || || || || ||_   |\n"+
                        "|_||_||_||_||_||_||_| _|  |"
        )
        //assert
        Assertions.assertThat(checkSumFail).isEqualTo("000000051 ILL")
    }
}

class AccountNumberReader {
    fun transform(read:String) : String {
        val lines = read.split("\n")
        val arrayOfNumbers = mutableListOf<String>()
        var outcome = mutableListOf<String>()
        val checkSum = mutableListOf<Int>()
        val compareList = listOf(
                " _ | ||_|","     |  |"," _  _||_ ",
                " _  _| _|","   |_|  |"," _ |_  _|",
                " _ |_ |_|"," _   |  |"," _ |_||_|",
                " _ |_| _|")
        for (index in lines.indices) {
            var i = 0
            while(i < (lines[index].length/3)){
                arrayOfNumbers.add(lines[index].slice(IntRange(start = i*3, endInclusive = (i*3 + 2))))
                i++
            }
        }
        var i = 0
        var illegible = false
        val numberOfNumbers = arrayOfNumbers.size/3
        while ( i < numberOfNumbers) {
            val number = arrayOfNumbers[(i)]+arrayOfNumbers[(i+numberOfNumbers)]+arrayOfNumbers[(i+numberOfNumbers*2)]
            val convertedNumber = compareList.indexOf(number)
            if (convertedNumber == -1){
                val fixedNumber = fixNumber(number)
                if(fixedNumber != -1){
                    outcome.add(fixedNumber.toString())
                } else {
                    outcome.add("?")
                    illegible = true
                }
            } else {
                checkSum.add(convertedNumber)
                outcome.add(convertedNumber.toString())
            }
            i++
        }

        if(illegible) {
            outcome.add(" ILL")
        } else {
            if(checksum(checkSum)){
                val fixedCheckSum = fixChecksum(checkSum)
                if(fixedCheckSum==checkSum){
                    outcome.add(" ILL")
                } else {
                    outcome = mutableListOf()
                    for(index in fixedCheckSum.indices){
                        outcome.add(fixedCheckSum[index].toString())
                    }
                }
            }
        }
        return outcome.joinToString("")
    }

    private
    fun checksum(numberArray :MutableList<Int>) : Boolean {
        var iterator = 0
        var accumulator = 0
        while ( iterator < numberArray.size){
            accumulator += numberArray[iterator]*(numberArray.size-iterator)
            iterator++
        }
        return accumulator.rem(11) == 0
    }

    private
    fun fixNumber(number : String) : Int {
        if(number == "   |_ |_|"){
            return 6
          } else if(number == "    _|  |") {
            return 4
          } else  {
            return -1
         }
    }

    private
    fun fixChecksum(numberArray :MutableList<Int>) :MutableList<Int> {
        var i = 0
        while( i < numberArray.size ){

            i++
        }
        return numberArray
    }
}
// 0 ? 6:9:8
// 1 ? 7
// 3 ? 9
// 5 ? 6:9
// 6 ? 5:8
// 7 ? 1
// 8 ? 0:6:9
// 9 ? 5:8:3
//" _  _  _  _  _  _  _  _    \n"+
//"| || || || || || || ||_   |\n"+
//"|_||_||_||_||_||_||_| _|  |"
//
//"    _  _     _  _  _  _  _ \n"+
//"  | _| _||_||_ |_   ||_||_|\n"+
//"  ||_  _|  | _||_|  ||_| _|"

//
// _|       |      |
//  |  &&  _| && | |
//
// &&   |

//    _      _
//2 =|_|     _|
//   |_  && |_| &&