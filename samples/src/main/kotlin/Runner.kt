fun main() {

    val pairs = List(5) { providePair() }
    pairs.forEachIndexed { index, pair ->
        println("i: $index, 1: ${pair.first}, 2:${pair.second}")
    }

    val (objs1, objs2) = pairs.unzip()

    println("")
    objs1.forEachIndexed { index, obj ->
        println("i: $index, 1: $obj")
    }
    objs2.forEachIndexed { index, obj ->
        println("i: $index, 1: $obj")
    }

    println("")
    val reduced = objs2.reduce { c1, c2 -> c1.combine(c2) }
    println("reduced: $reduced")
}


data class Object1(val num: String)
data class Object2(val num: String)

fun Object2.combine(obj2:Object2): Object2 {
    return Object2(this.num + obj2.num)
}
fun providePair(): Pair<Object1, Object2> {
    return Pair(Object1("1"), Object2("2"))
}