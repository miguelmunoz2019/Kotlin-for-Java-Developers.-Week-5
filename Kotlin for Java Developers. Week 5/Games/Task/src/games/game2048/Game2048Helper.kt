package games.game2048

/*
 * This function moves all the non-null elements to the beginning of the list
 * (by removing nulls) and merges equal elements.
 * The parameter 'merge' specifies the way how to merge equal elements:
 * it returns a new element that should be present in the resulting list
 * instead of two merged elements.
 *
 * If the function 'merge("a")' returns "aa",
 * then the function 'moveAndMergeEqual' transforms the input in the following way:
 *   a, a, b -> aa, b
 *   a, null -> a
 *   b, null, a, a -> b, aa
 *   a, a, null, a -> aa, a
 *   a, null, a, a -> aa, a
 *
 * You can find more examples in 'TestGame2048Helper'.
*/
fun <T : Any> List<T?>.moveAndMergeEqual(merge: (T) -> T): List<T>
{
    val lista=this.filterNotNull()
    var retornable= mutableListOf<T>()
    var contador=0
    var anadidor=0
    while (contador<lista.size)
    {
        if (contador+1<lista.size)
        {
            if (lista[contador]==lista[contador+1]) {
                retornable.add(anadidor, merge(lista[contador]))
                contador += 2
            }
            else {
                retornable.add(anadidor, (lista[contador]))
                contador += 1
            }
        }
        else {
            retornable.add(anadidor, (lista[contador]))
            contador += 1
        }
        anadidor+=1
    }
    return retornable

}


