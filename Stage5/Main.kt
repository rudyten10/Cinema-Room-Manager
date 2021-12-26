package cinema
// Stage 5/5: Errors_

enum class State { MENU, SHOW, STATS, BUY, EXIT }

class Seat() {
    var seatBooked: Char = 'S'
    var seatPrice: Int = 0
    var seatNumber: Int = 0
}

class CinemaManager {
    var isOn = false
    val rows: Int
    val seatsPerRow: Int
    var list = MutableList(0) { MutableList(0) { Seat() } }

    var state: State = State.MENU

    var ticketsSold: Int = 0
    var currentIncome = 0

    init {
        println("Enter the number of rows:")
        rows = readLine()!!.toInt()

        println("Enter the number of seats in each row:")
        seatsPerRow = readLine()!!.toInt()

        list = MutableList(rows) { MutableList(seatsPerRow) { Seat() } }

        setupSystem()
    }

    fun userMenu(): State {
        println()
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")

        when (readLine()!!.toInt()) {
            1 -> state = State.SHOW
            2 -> state = State.BUY
            3 -> state = State.STATS
            0 -> state = State.EXIT
            else -> {
                println("Unknown action, try again")
                state
            }
        }
        return state
    }

    fun runSystem() {
        when (state) {
            State.MENU -> state = userMenu()
            State.SHOW -> state = drawGameBoard()
            State.BUY -> state = buyTicket()
            State.STATS -> state = statistics()
            State.EXIT -> state = exitSystem()
            else -> throw UnsupportedOperationException("Unexpected state")
        }
    }

    fun buyTicket(): State {
        var ticketPrice = 0

        println()
        while (true) {
            println("Enter a row number:")
            var rowNo = readLine()!!.toInt()
            rowNo--

            println("Enter a seat number in that row:")
            var seatNo = readLine()!!.toInt()
            seatNo--

            try {
                if (list[rowNo][seatNo].seatBooked == 'B') {
                    println("That ticket has already been purchased!\n")
                } else {
                    list[rowNo][seatNo].seatBooked = 'B'
                    ticketPrice = list[rowNo][seatNo].seatPrice
                    currentIncome += ticketPrice
                    ticketsSold++
                    break
                }
            } catch (e: Exception){
                println()
                println("Wrong input!")
                println()
            }
        }
        println()
        println("Ticket price: $$ticketPrice")

        return State.MENU
    }

    fun statistics(): State {
        var totalIncome = 0
        var totalSeats = 0
        if (rows * seatsPerRow <= 60) {
            totalIncome = (rows * seatsPerRow * 10)
        }
        else{
            if (rows % 2 == 0){
                totalIncome = (rows * seatsPerRow / 2 * 10) + (rows * seatsPerRow / 2 * 8)
            } else {
                totalIncome = ((rows - 1) * seatsPerRow / 2 * 10) + ((rows - 1) * seatsPerRow / 2 * 8) + (seatsPerRow * 8)
            }
        }
        totalSeats = (rows * seatsPerRow)

        println("Number of purchased tickets: $ticketsSold")
        try {
            println("Percentage: ${"%.2f".format((ticketsSold * 100.0 / totalSeats))}%")
       } catch (e: Exception) {
            println("Percentage: 0.00%")
        }
        println("Current income: $$currentIncome")
        println("Total income: $$totalIncome")

        return State.MENU
    }

    fun setSeatPrice(seatCnt: Int, price: Int){
        var cnt = 0
        out@for (i in list){
            for (j in i){
                if (j.seatPrice == 0) {
                    j.seatPrice = price
                    j.seatNumber = ++cnt
                    if (cnt >= seatCnt) break@out
                }
            }
        }
    }

    fun drawGameBoard(): State {
        println()
        print("Cinema:")
        println()
        print(" ")
        print(" ")
        for (i in 1..seatsPerRow){
            if (i > 9) {
                var a = i.toString().substring(1,2)
                print(a)
            } else {
                print("$i ")
            }
        }
        println()

        var cnt = 1
        for (i in list) {
            print("${cnt}")
            cnt++

            for (j in i){
                print(" ${j.seatBooked}")
            }
            println("")
        }

        return State.MENU
    }

    fun setupSystem() {
        if (rows * seatsPerRow <= 60) {
            setSeatPrice(rows * seatsPerRow, 10)
        }
        else{
            if (rows % 2 == 0){
                setSeatPrice(rows * seatsPerRow / 2, 10)
                setSeatPrice(rows * seatsPerRow / 2, 8)
            } else {
                setSeatPrice(rows / 2 * seatsPerRow, 10)
                setSeatPrice(rows / 2 * seatsPerRow + seatsPerRow, 8)
            }
        }
    }

    fun exitSystem(): State {
        isOn = false

        return State.MENU
    }
}

fun main() {
    CinemaManager().run {
        do runSystem() while (state != State.EXIT)
    }
}
//198 160 132