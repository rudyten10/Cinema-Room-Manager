package cinema
// Stage 3/5: Tickets

class Seat() {
    var seatBooked: Char = 'S'
    var seatPrice: Int = 0
    var seatNumber: Int = 0
}

class Cinema(var rows:Int, var seats:Int) {
    var isOn = false
    var list = MutableList(rows) { MutableList(seats) { Seat() } }

    fun getUserInput() {
        isOn = true

        var ticketPrice = 0
        var value = 0

        if (rows * seats <= 60) {
            value = (rows * seats * ticketPrice)
            setSeatPrice(rows * seats, 10)
        }
        else{
            if (rows % 2 == 0){
                value = (rows * seats / 2 * 10) + (rows * seats / 2 * 8)
                setSeatPrice(rows * seats / 2, 10)
                setSeatPrice(rows * seats / 2, 8)
            } else {
                value = ((rows - 1) * seats / 2 * 10) + ((rows - 1) * seats / 2 * 8) + (seats * 8)
                setSeatPrice((rows - 1) * seats / 2, 10)
                setSeatPrice((rows - 1) * seats / 2 + seats, 8)
            }
        }

        //println("Total income:")
        //println("$$value")
        drawGameBoard()

        println("Enter a row number:")
        var rowNo = readLine()!!.toInt()
        rowNo--

        println("Enter a seat number in that row:")
        var seatNo = readLine()!!.toInt()
        seatNo--

        list[rowNo][seatNo].seatBooked = 'B'

        ticketPrice = list[rowNo][seatNo].seatPrice

        println("Ticket price: $$ticketPrice")

        drawGameBoard()
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

    fun setUpBoard(){
        drawGameBoard()
    }

    fun drawGameBoard() {
        print("Cinema:")
        println()
        print(" ")
        print(" ")
        for (i in 1..seats){
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
    }
}

fun main() {
    println("Enter the number of rows:")
    val rows = readLine()!!.toInt()

    println("Enter the number of seats in each row:")
    val seats = readLine()!!.toInt()

    val obj = Cinema(rows, seats)
    //obj.setUpBoard()
    obj.getUserInput()

//    while (obj.isOn) {
//    }
}
// 117