import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

fun uniqueCharacters(str: String): Boolean{
    val chArray = str.toCharArray()
    chArray.sort()
    for (i in 0..chArray.size - 2) {
        if (chArray[i] == chArray[i + 1]) return false else continue
    }
    return true
}