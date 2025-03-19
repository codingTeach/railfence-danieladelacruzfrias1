import javax.swing.JOptionPane

fun railFenceEncrypt(text: String, rails: Int): String {
    if (rails <= 1) return text

    val fence = Array(rails) { StringBuilder() }
    var rail = 0
    var direction = 1

    for (char in text) {
        fence[rail].append(char)
        rail += direction
        if (rail == 0 || rail == rails - 1) direction *= -1
    }

    return fence.joinToString("")
}

fun railFenceDecrypt(cipherText: String, rails: Int): String {
    if (rails <= 1) return cipherText

    val pattern = mutableListOf<Int>()
    var rail = 0
    var direction = 1

    for (i in cipherText.indices) {
        pattern.add(rail)
        rail += direction
        if (rail == 0 || rail == rails - 1) direction *= -1
    }

    val fence = Array(rails) { StringBuilder() }
    val indices = pattern.sorted().withIndex()
    var index = 0

    for ((i, railIndex) in indices) {
        fence[railIndex].append(cipherText[i])
    }

    val decryptedText = StringBuilder()
    for (i in pattern.indices) {
        decryptedText.append(fence[pattern[i]].first())
        fence[pattern[i]].deleteCharAt(0)
    }

    return decryptedText.toString()
}

fun main() {
    val options = arrayOf("Cifrar", "Descifrar", "Salir")

    while (true) {
        val choice = JOptionPane.showOptionDialog(
            null, "Seleccione una opción:", "Cifrado Rail Fence",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
            null, options, options[0]
        )

        if (choice == 2 || choice == JOptionPane.CLOSED_OPTION) break

        val text = JOptionPane.showInputDialog("Ingrese el texto:")
        if (text.isNullOrEmpty()) continue

        val railsInput = JOptionPane.showInputDialog("Ingrese el número de rieles:")
        val rails = railsInput?.toIntOrNull() ?: continue

        val result = if (choice == 0) railFenceEncrypt(text, rails)
        else railFenceDecrypt(text, rails)

        JOptionPane.showMessageDialog(null, "Resultado: $result")
    }
}