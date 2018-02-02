fun main(args: Array<String>) {
    val tree = createTree(args[0])
    val totalUnknownSum = findTotalUnknowns(tree!!.left) - findTotalUnknowns(tree!!.right)
    val totalKnownSum = findTotalValues(tree!!.right) - findTotalValues(tree!!.left)
    val result = totalKnownSum / totalUnknownSum
    println("x=$result")
}

fun createTree(s: String?): Node? {
    s?.let {
        if (s.contains("=")) {
            val leftRightList = s.split("=")
            return Node("=", createTree(leftRightList[0]), createTree(leftRightList[1]))
        }
        if (s.contains("+")) {
            val leftRightList = s.split("+")
            return Node("+", createTree(leftRightList[0]), createTree(leftRightList[1]))
        } else if (s.contains("-")) {
            val leftRightList = s.split("-")
            return Node("-", createTree(leftRightList[0]), createTree(leftRightList[1]))
        } else {
            return Node(s)
        }
    }
    return null
}

fun findTotalUnknowns(node: Node?): Int {
    node?.let {
        if (node?.left == null && node?.right == null) {
            when (it.value) {
                "x" -> return 1
                else -> return 0
            }
        } else {
            when (it.value) {
                "+" -> return findTotalUnknowns(it.left) + findTotalUnknowns(it.right)
                "-" -> return findTotalUnknowns(it.left) - findTotalUnknowns(it.right)
                else -> return 0
            }
        }
    } ?: return 0
}

fun findTotalValues(node: Node?): Int {
    node?.let {
        if (node.left == null && node.right == null) {
            when (it.value) {
                "x" -> return 0
                else -> return it.value.toInt()
            }
        } else {
            when (it.value) {
                "+" -> return findTotalValues(it.left) + findTotalValues(it.right)
                "-" -> return findTotalValues(it.left) - findTotalValues(it.right)
                else -> return 0
            }
        }

    } ?: return 0
}
