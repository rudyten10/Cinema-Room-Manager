package cinema
// Stage 2/5: Sold_

class Cinema(var rows:Int, var seats:Int) {
    var list2 = MutableList(rows) { MutableList(seats) { 'S' } }

    fun getUserInput(){
        println("Enter the number of rows:")
        val rows = readLine()!!.toInt()

        println("Enter the number of seats in each row:")
        val seats = readLine()!!.toInt()

        var value = 0

        if (rows * seats <= 60)
            value = (rows * seats  * 10)
        else{
            if (rows % 2 == 0){
                value = (rows * seats / 2 * 10) + (rows * seats / 2 * 8)
            } else {
                value = ((rows - 1) * seats / 2 * 10) + ((rows - 1) * seats / 2 * 8) + (seats * 8)
            }
        }

        println("Total income:")
        println("$$value")
        //drawGameBoard()
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
        for (i in list2) {
            print("${cnt}")
            cnt++

            for (j in i){
                print(" $j")
            }
            println("")
        }
    }
}

fun main() {
    val obj = Cinema(7, 8)
    //obj.setUpBoard()
    obj.getUserInput()
}