import kotlin.random.Random

const val LENGTH_OF_NUMBER: Short = 4
const val NUM_OF_ROUNDS: Short = 10
const val BASE_OF_DECIMAL_SYSTEM = 10

fun main() {
    bullsAndCows()
}

fun bullsAndCows() {
    val secretNumber = getRandIntWithUnrepeatedDigits(LENGTH_OF_NUMBER)
    var supposedNumber: String

    var numOfCows: Short
    var numOfBulls: Short

    println("Input supposed number")

    for (i in 0 until NUM_OF_ROUNDS) {
        supposedNumber = readln()
        if (supposedNumber == secretNumber) {
            println("Correct!")
            return
        }
        numOfCows = getNumOfCows(secretNumber, supposedNumber)
        numOfBulls = getNumOfBulls(secretNumber, supposedNumber)
        println("Num of bulls: ${numOfBulls}, num of cows: ${numOfCows - numOfBulls}, rounds left: ${NUM_OF_ROUNDS - i - 1}")
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

