package cinema
// Stage 4/5: Menu, please_
/////////////////////////////////////////////////////////////////////////
// My First time using enum. Hopefully am using them correctly. I looked at
// several other projects to see how they are used. Turns out everyone has
// their own ideas
/////////////////////////////////////////////////////////////////////////
enum class State { MENU, SHOW, BUY, EXIT }

class Seat() {
    var seatBooked: Char = 'S'
    var seatPrice: Int = 0
    var seatNumber: Int = 0
}

class Cinema { //>>>(var rows:Int, var seatsPerRow:Int)
    var isOn = false
    val rows: Int
    val seatsPerRow: Int
    var list = MutableList(0) { MutableList(0) { Seat() } }

    var state: State = State.MENU

    init {
        println("Enter the number of rows:")
        rows = readLine()!!.toInt()

        println("Enter the number of seats in each row:")
        seatsPerRow = readLine()!!.toInt()

        list = MutableList(rows) { MutableList(seatsPerRow) { Seat() } }

        setupSystem()
    }


    fun userMenu(): State { //: Int
        println()
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("0. Exit")

        //>>>return
        when (readLine()!!.toInt()) {
            1 -> state = State.SHOW
            2 -> state = State.BUY
            0 -> state = State.EXIT
            else -> {
                println("Unknown action, try again")
                state
            }
        }
        return state
    }

    fun runSystem() {
        //>>>state =
        when (state) {
            State.MENU -> state = userMenu()
            State.SHOW -> state = drawGameBoard()
            State.BUY -> state = buyTicket()
            State.EXIT -> state = exitSystem()
            else -> throw UnsupportedOperationException("Unexpected state")
        }
    }

    fun buyTicket(): State {
        println("Enter a row number:")
        var rowNo = readLine()!!.toInt()
        rowNo--

        println("Enter a seat number in that row:")
        var seatNo = readLine()!!.toInt()
        seatNo--

        list[rowNo][seatNo].seatBooked = 'B'

        val ticketPrice = list[rowNo][seatNo].seatPrice

        println("Ticket price: $$ticketPrice")

        return State.SHOW
    }

    fun setSeatPrice(seatCnt: Int, price: Int){
        var cnt = 0
        for (i in list){
            for (j in i){
                if (j.seatPrice == 0) {
                    j.seatPrice = price
                    j.seatNumber = ++cnt
                    if (cnt > seatCnt) break
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
        //isOn = true

        //var value = 0
        if (rows * seatsPerRow <= 60) {
            //value = (rows * seatsPerRow * ticketPrice)
            setSeatPrice(rows * seatsPerRow, 10)
        }
        else{
            if (rows % 2 == 0){
                //value = (rows * seatsPerRow / 2 * 10) + (rows * seatsPerRow / 2 * 8)
                setSeatPrice(rows * seatsPerRow / 2, 10)
                setSeatPrice(rows * seatsPerRow / 2, 8)
            } else {
                //value = ((rows - 1) * seatsPerRow / 2 * 10) + ((rows - 1) * seatsPerRow / 2 * 8) + (seatsPerRow * 8)
                setSeatPrice((rows - 1) * seatsPerRow / 2, 10)
                setSeatPrice((rows - 1) * seatsPerRow / 2 + seatsPerRow, 8)
            }
        }
        //println("Total income:")
        //println("$$value")
    }

    fun exitSystem(): State {
        isOn = false

        return State.MENU
    }
}

fun main() {
    Cinema().run {
        do runSystem() while (state != State.EXIT)
    }

}
//160 132