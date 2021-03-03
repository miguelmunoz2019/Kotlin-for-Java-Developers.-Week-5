package games.gameOfFifteen

/*
 * This function should return the parity of the permutation.
 * true - the permutation is even
 * false - the permutation is odd
 * https://en.wikipedia.org/wiki/Parity_of_a_permutation

 * If the game of fifteen is started with the wrong parity, you can't get the correct result
 *   (numbers sorted in the right order, empty cell at last).
 * Thus the initial permutation should be correct.
 */
fun isEven(permutation: List<Int>): Boolean {
    var cantidad = 0
    var contador=0
    while (contador<permutation.size-1)
    {
        var contador2=contador+1
        while (contador2<permutation.size)
        {
            if (permutation.get(contador2)<permutation.get(contador))
                cantidad+=1
            contador2+=1
        }
        contador+=1
    }
return cantidad%2==0
}