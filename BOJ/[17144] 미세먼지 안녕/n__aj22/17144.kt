private val dx = intArrayOf(1, 0, -1, 0)
private val dy = intArrayOf(0, 1, 0, -1)

private lateinit var air: Array<Pair<Int, Int>>
private lateinit var arr: Array<IntArray>
private fun main(){
    val (r, c, t) = readLine()!!.split(' ').map{it.toInt()}
    arr = Array(r){IntArray(c)}
    air = Array(2){Pair(-1, -1)}

    for(i in 0 until r){
        readLine()!!.split(' ').map{it.toInt()}.forEachIndexed { j, value ->
            arr[i][j] = value
            if (value == -1 && air[0].first == -1){
                air[0] = Pair(i, j) //y, x
                arr[i][j] = 0
            }
            else if(value == -1 && air[0].first != -1){
                air[1] = Pair(i, j)
                arr[i][j] = 0
            }
        }
    }

    for(i in 0 until t){
        spreadDust(r, c)
        moveDust(r, c)
    }

    var totalsum = 0
    for (i in arr){
        totalsum+=i.sum()
    }
    print(totalsum)
}

private fun isin(y: Int, x:Int, r: Int, c: Int): Boolean{
    if(x in 0 until c && y in 0 until r) return true
    return false
}

private fun spreadDust(r: Int, c:Int){
    val tmpArr = deepCopy(r, c)
    for(i in 0 until r) {
        for(j in 0 until c){
            val dust = tmpArr[i][j]/5
            for (k in 0 until 4){
                val (curX, curY) = intArrayOf(j+dx[k], i+dy[k])
                when(isin(curY, curX, r, c)){
                    true->
                    {
                        if(curY == air[0].first&&curX == air[0].second) continue
                        if(curY == air[1].first&&curX == air[1].second) continue
                        arr[curY][curX] +=dust
                        arr[i][j] -= dust
                    }
                }
            }
        }
    }
}

private fun moveDust(r: Int, c: Int){
    val tmpArr = deepCopy(r, c)
    //윗칸
    var start_direction = 0
    var (nowy, nowx) = intArrayOf(air[0].first, air[0].second)
    while(true){
        var (nexty, nextx) = intArrayOf(nowy+dy[start_direction], nowx+dx[start_direction])
        if(!isin(nexty, nextx, r, c)){
            start_direction = (start_direction+3)%4
            nexty = nowy+dy[start_direction]
            nextx = nowx+dx[start_direction]
        }
        else if(nexty == air[0].first && nextx == air[0].second) break

        arr[nexty][nextx] = tmpArr[nowy][nowx]
        nowy = nexty
        nowx = nextx
    }
    var (nowy2, nowx2) = intArrayOf(air[1].first, air[1].second)
    start_direction = 0
    //아랫칸

    while(true){
        var (nexty, nextx) = intArrayOf(nowy2+dy[start_direction], nowx2+dx[start_direction])
        if(!isin(nexty, nextx, r, c)){
            start_direction = (start_direction+1)%4
            nexty = nowy2 + dy[start_direction]
            nextx = nowx2 + dx[start_direction]
        }
        else if(nexty == air[1].first && nextx == air[1].second) break

        arr[nexty][nextx] = tmpArr[nowy2][nowx2]
        nowy2 = nexty
        nowx2 = nextx
    }
}

private fun deepCopy(r: Int, c: Int): Array<IntArray>{
    var tmp = Array(r){ IntArray(c) }
    for (i in 0 until r){
        for(j in 0 until c){
            tmp[i][j] = arr[i][j]
        }
    }
    return tmp
}