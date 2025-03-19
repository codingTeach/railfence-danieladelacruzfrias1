import javax.swing.JOptionPane

fun encryptRailFence(text: String, key: Int): String {
    val rail = Array(key) { CharArray(text.length) { '\n' } }
    var dirDown = false
    var row = 0
    var col = 0

    for (char in text) {
        if (row == 0 || row == key - 1) dirDown = !dirDown
        rail[row][col++] = char
        row += if (dirDown) 1 else -1
    }

    return rail.joinToString(separator = "") { it.filter { ch -> ch != '\n' }.joinToString("") }
}

fun decryptRailFence(cipher: String, key: Int): String {
    val rail = Array(key) { CharArray(cipher.length) { '\n' } }
    var dirDown = true
    var row = 0
    var col = 0

    for (i in cipher.indices) {
        if (row == 0) dirDown = true
        if (row == key - 1) dirDown = false
        rail[row][col++] = '*'
        row += if (dirDown) 1 else -1
    }

    var index = 0
    for (i in 0 until key) {
        for (j in cipher.indices) {
            if (rail[i][j] == '*' && index < cipher.length) {
                rail[i][j] = cipher[index++]
            }
        }
    }

    val result = StringBuilder()
    row = 0
    col = 0
    dirDown = true
    for (i in cipher.indices) {
        if (row == 0) dirDown = true
        if (row == key - 1) dirDown = false
        result.append(rail[row][col++])
        row += if (dirDown) 1 else -1
    }

    return result.toString()
}

fun main() {
    val text = JOptionPane.showInputDialog("Ingrese el texto a cifrar:")
    val key = JOptionPane.showInputDialog("Ingrese la clave:").toInt()
    val encryptedText = encryptRailFence(text, key)
    JOptionPane.showMessageDialog(null, "Texto Cifrado: $encryptedText")

    val decryptedText = decryptRailFence(encryptedText, key)
    JOptionPane.showMessageDialog(null, "Texto Descifrado: $decryptedText")
}
