fun main() {

}

//class CreditCard {
//    fun charge(price: Float): Unit = TODO()
//}
//
//data class Coffee(val price: Float = 2.50F)

//class Cafe {
//
//    fun buy(cc: CreditCard): Coffee {
//        val cup = Coffee()
//        // Charge credit card with the coffee’s price.
//        //  A side effect!
//        cc.charge(cup.price)
//        return cup
//    }
//}

// --
//data class Coffee(val price: Float = 2.95F)
//
//class CreditCard
//
//class Payments {
//    fun charge(cc: CreditCard, price: Float): Unit = TODO()
//}
//
//class Cafe {
//    fun buyCoffee(cc: CreditCard, p: Payments): Coffee {
//        val cup = Coffee()
//        // hough side effects still occur
//        p.charge(cc, cup.price)
//        return cup
//    }
//}


// --

class CreditCard

data class Coffee(val price: Float = 2.50F)

data class Charge(val cc: CreditCard, val amount: Float) {

    fun combine(other: Charge): Charge =
        if (cc == other.cc)
            Charge(cc, amount + other.amount)
        else throw Exception(
            "Cannot combine charges to different cards"
        )
}

fun List<Charge>.coalesce(): List<Charge> =
    this.groupBy { it.cc }.values
        .map { it.reduce { a, b -> a.combine(b) } }

// Here we’ve separated the concern of
// creating a charge from the processing or
// interpretation of that charge.
class Cafe {

    fun buyCoffee(cc: CreditCard): Pair<Coffee, Charge> = TODO()

    fun buyCoffees(
        cc: CreditCard,
        n: Int
    ): Pair<List<Coffee>, Charge> {

        val purchases: List<Pair<Coffee, Charge>> =
            List(n) { buyCoffee(cc) }

        val (coffees, charges) = purchases.unzip()

        return Pair(
            coffees,
            charges.reduce { c1, c2 -> c1.combine(c2) }
        )
    }
}