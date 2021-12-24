package cinema
// Stage 1/5: Arrangement

class Cinema(var rows:Int, var seats:Int) {
    var list2 = MutableList(rows) { MutableList(seats) { 'S' } }

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
    obj.setUpBoard()
}