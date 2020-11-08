import kotlin.math.E

fun main() {
    println("CH2")
    /*
       The absolute value of -42 is 42
       The factorial of 7 is 5040
     * */
//    println(factorial(7))
    println(formatResult("factorial", 7, ::factorial))
    println(formatResult("absolute value", -42, ::abs))
    println(formatResult("absolute value", -42) { if (it < 0) -it else it })

    println(
        findFirst(arrayOf(1, 3, 33, 4, 55, 6, 77, 8, 99)) {
            it == 55
        }
    )
}

//fun factorial(i: Int): Int {
//    fun go(n: Int, acc: Int): Int =
//        if (n <= 0) acc
//        else go(n - 1, n * acc)
//    return go(i, 1)
//}

fun factorial(i: Int): Int {
    tailrec fun go(n: Int, acc: Int): Int =
        if (n <= 0) acc
        else go(n - 1, n * acc)
    return go(i, 1)
}

fun abs(n: Int): Int = if (n < 0) -n else n

//object Example {
//
////    private fun factorial(i: Int): Int {
////        fun go(n: Int, acc: Int): Int = if (n <= 0) acc else go(n - 1, n * acc)
////        return go(i, 1)
////    }
//
//    fun formatAbs(x: Int): String {
//        return formatResult("absolute value", x, ::abs)
//    }
//
//    fun formatFactorial(x: Int): String {
//        return formatResult("factorial", x, ::factorial)
//    }
//}

fun formatResult(name: String, n: Int, f: (Int) -> Int): String {
    val msg = "The %s of %d is %d."
    return msg.format(name, n, f(n))
}

//monomorphic
//fun findFirst(ss: Array<String>, key: String): Int {
//    tailrec fun loop(n: Int): Int = when {
//        n >= ss.size -> -1
//        ss[n] == key -> n
//        else -> loop(n + 1)
//    }
//    return loop(0)
//}

//polymorphic
fun <A> findFirst(ss: Array<A>, p: (A) -> Boolean): Int {
    tailrec fun loop(n: Int): Int = when {
        n >= ss.size -> -1
        p(ss[n]) -> n
        else -> loop(n + 1)
    }
    return loop(0)
}