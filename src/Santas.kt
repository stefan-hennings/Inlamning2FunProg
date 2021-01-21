val santaMap: Map<String, List<String>> = hashMapOf(
    "Tomten" to listOf("Glader", "Butter"),
    "Glader" to listOf("Tröger", "Trötter", "Blyger"),
    "Butter" to listOf("Rådjuret", "Nyckelpigan", "Haren", "Räven"),
    "Trötter" to listOf("Skumtomten"),
    "Skumtomten" to listOf("Dammråttan"),
    "Räven" to listOf("Gråsuggan", "Myran"),
    "Myran" to listOf("Bladlusen")
)

/*fun findSuperiors(input : String) : List<String> {
    fun addSuperior(underling : String, superiorsList : MutableList<String>) : List<String> {
        println("Checking $underling")
        return try {
            val superior = santaMap.filterValues { it.contains(underling)}
                .keys
                .first()
            println("Superior is $superior")
            superiorsList.add(superior)
            addSuperior(superior, superiorsList)
        } catch (e: NoSuchElementException) {
            superiorsList
        }
    }
    val allSuperiors = mutableListOf<String>()
    addSuperior(input, allSuperiors)
    return allSuperiors
}*/

fun findSuperiors(input: String): List<String> {
    fun addSuperior(underling: String, superiorsList: MutableList<String>): List<String> {
        if (!santaMap.flatMap { it.value }.contains(underling)) return superiorsList
        val superior = santaMap.filterValues { it.contains(underling) }
            .keys
            .first()
        superiorsList.add(superior)
        return addSuperior(superior, superiorsList)
    }

    val allSuperiors = mutableListOf<String>()
    addSuperior(input, allSuperiors)
    return allSuperiors
}

fun findUnderlings(input: String): List<String> {
    fun addUnderlings(superior: String, underlingList: MutableList<String>): List<String> {
        if (!santaMap.containsKey(superior)) return underlingList
        santaMap[superior]?.forEach {
            underlingList.add(it)
            addUnderlings(it, underlingList)
        }
        return underlingList
    }

    val allUnderlings = mutableListOf<String>()
    addUnderlings(input, allUnderlings)
    return allUnderlings
}

fun main() {
    println(findSuperiors("Räven"))
    println(findUnderlings("Räven"))
}