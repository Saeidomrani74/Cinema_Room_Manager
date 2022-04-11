package cinema

fun main() {
    takeNum()
}

fun drawSeats(r: Int, c: Int, bought: MutableList<MutableList<Int>>) {
    println("Cinema:")
    val seats = MutableList(r + 1) { MutableList(c + 1) { " " } }
    for (i in 0..r) {
        for (j in 0..c) {
            when (i) {
                0 -> if (j > 0) seats[0][j] = "$j"
                else -> if (j == 0) {
                    seats[i][0] = "$i"
                } else {
                    seats[i][j] = "S"
                }
            }
        }
    }
//    if (rb > 0) seats[rb][cb] = "B"

    for (i in 0..r) {
        for (j in 0..c) {
            if (bought[i][j] == 1) {
                if (j == c) println("B") else print("B ")
            } else if (j == c) println(seats[i][c]) else print("${seats[i][j]} ")
        }
    }
}

fun takeNum() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val cols = readln().toInt()
//    profit(rows, cols)
    menu(rows, cols)
//    drawSeats(rows, cols, 0, 0)
//    price(rows, cols)
}

fun profit(r: Int, c: Int): Int {
    return if (r * c > 60) {
        ((r / 2) * c * 10) + (((r + 1) / 2) * c * 8)
    } else 10 * r * c
//    println("Total income:\n" + "\$$profit")
}

fun price(r: Int, c: Int, bought: MutableList<MutableList<Int>>)
        : MutableList<MutableList<Int>> {
    while (true) {
        println("Enter a row number:")
        val rs = readln().toInt()
        println("Enter a seat number in that row:")
        val cs = readln().toInt()

        try {
            bought[rs][cs]
        } catch (e: Exception) {
            println("Wrong input!")
            continue
        }

        if (bought[rs][cs] == 1) println(
            "That ticket has already been purchased!"
        ) else {
            val prc = if (r * c > 60) {
                if (rs > r / 2) 8 else 10
            } else 10
            println("Ticket price: \$$prc")
            bought[rs][cs] = 1
            break
        }
    }

    return bought
//    drawSeats(r, c, rs, cs)
}

fun menu(r: Int, c: Int) {
    var bought = MutableList(r + 1) { MutableList(c + 1) { 0 } }
    while (true) {
        println(
            "1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit"
        )
        when (readln().toInt()) {
            1 -> drawSeats(r, c, bought)
            2 -> bought = price(r, c, bought)
            3 -> statistics(r, c, bought)
            0 -> break
        }
    }
}

fun statistics(r: Int, c: Int, bought: MutableList<MutableList<Int>>) {
    var sold = 0
    var income = 0

    for (i in 0..r) {
        for (j in 0..c) {
            if (bought[i][j] == 1) {
                val price = if (r * c > 60) {
                    if (i > r / 2) 8 else 10
                } else 10
                income += price
            }
        }
    }

    for (i in 0..r) {
        for (j in 0..c) {
            if (bought[i][j] == 1) sold++
        }
    }
    val formPer = "%.2f".format(sold.toDouble() * 100 / (r * c))
//        print(formPer) // 0.00
    println(
        "Number of purchased tickets: $sold\n" +
                "Percentage: $formPer%\n" +
                "Current income: \$$income\n" +
                "Total income: \$${profit(r, c)}\n"
    )
}
