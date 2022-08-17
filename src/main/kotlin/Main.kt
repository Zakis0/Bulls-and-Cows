import kotlin.random.Random

const val LENGTH_OF_NUMBER: Short = 4
const val NUM_OF_ROUNDS: Short = 10

const val BASE_OF_DECIMAL_SYSTEM = 10

fun main() {
    bullsAndCows()
}

fun bullsAndCows() {
    var supposedNumber: String

    var numOfCows: Short
    var numOfBulls: Short
    var roundsLeft: Short

    val secretNumber = getRandIntWithUnrepeatedDigits(LENGTH_OF_NUMBER)

    println(secretNumber)

    println("Input supposed $LENGTH_OF_NUMBER-digit number")

    for (i in 0 until NUM_OF_ROUNDS) {
        supposedNumber = getNumber()
        if (supposedNumber == secretNumber) {
            println("Correct!")
            return
        }
        numOfBulls = getNumOfBulls(secretNumber, supposedNumber)
        numOfCows = (getNumOfCows(secretNumber, supposedNumber) - numOfBulls).toShort()
        roundsLeft = (NUM_OF_ROUNDS - i - 1).toShort()
        println("Num of bulls: $numOfBulls, num of cows: $numOfCows, rounds left: $roundsLeft")
    }
    println("Fail")
}

fun getRandIntWithUnrepeatedDigits(len: Short): String {
    var result = ""
    var newDigit: Int
    for (i in 0 until len) {
        do newDigit = Random.nextInt(0, BASE_OF_DECIMAL_SYSTEM)
        while (result.contains(newDigit.toString()))
        result += newDigit.toString()
    }
    if (result[0] == '0')
        return getRandIntWithUnrepeatedDigits(len)
    return result
}

fun getNumber(): String {
    val number = readln()
    if (!isNumberString(number) || number.length != LENGTH_OF_NUMBER.toInt()) {
        println("Incorrect input")
        return getNumber()
    }
    return number
}

fun isNumberString(string: String?): Boolean {
    return if (string.isNullOrEmpty()) false else string.all { Character.isDigit(it) }
}

fun getNumOfCows(secretNumber: String, supposedNumber: String): Short {
    var result: Short = 0
    for (i in supposedNumber)
        if (secretNumber.contains(i))
            result++
    return result
}

fun getNumOfBulls(secretNumber: String, supposedNumber: String): Short {
    var result: Short = 0
    for (i in secretNumber.indices)
        if (secretNumber[i] == supposedNumber[i])
            result++
    return result
}

