fun main() {
    print("Bem-vindo ao Banco Digital! Qual é o seu nome? ")
    val nome = readLine() ?: "Usuário"
    println("Olá $nome, é um prazer ter você por aqui!\n")

    var saldo = 1500.0

    val extrato = mutableListOf(
        "Compra no Mercado Livre    - R$ 250,00",
        "Depósito via PIX           + R$ 800,00",
        "Compra na Amazon           - R$ 199,90",
        "Depósito salário           + R$ 3.000,00",
        "Compra no iFood            - R$ 45,50",
        "Pagamento de conta de luz  - R$ 120,00"
    )

    inicio(nome, saldo, extrato)
}

fun inicio(nome: String, saldoInicial: Double, extrato: MutableList<String>) {
    var saldo = saldoInicial

    var continuar = true
    while (continuar) {
        println("\nMenu Principal")
        println("1 - Saldo")
        println("2 - Extrato")
        println("3 - Saque")
        println("4 - Depósito")
        println("5 - Transferência")
        println("6 - Sair")
        print("Escolha uma opção: ")

        val opcao = readLine()?.trim()

        when (opcao) {
            "1" -> verSaldo(saldo)
            "2" -> verExtrato(extrato)
            "3" -> {
                val novoSaldo = realizarSaque(saldo)
                saldo = novoSaldo
            }
            "4" -> {
                val resultado = realizarDeposito(saldo, extrato)
                saldo = resultado
            }
            "5" -> {
                val novoSaldo = realizarTransferencia(saldo)
                saldo = novoSaldo
            }
            "6" -> {
                println("\n$nome, foi um prazer ter você por aqui!")
                continuar = false
            }
            else -> erro()
        }
    }
}

fun validarSenha(): Boolean {
    val senhaCorreta = "3589"
    print("Digite sua senha: ")
    val senhaDigitada = readLine()?.trim()
    return senhaDigitada == senhaCorreta
}

fun verSaldo(saldo: Double) {
    println("\nConsulta De Saldo")
    if (!validarSenha()) {
        println("Senha incorreta! Tente novamente.")
        verSaldo(saldo)
        return
    }
    println("Seu saldo atual é: R$ %.2f".format(saldo))
}

fun verExtrato(extrato: MutableList<String>) {
    println("\nExtrato Bancário")
    if (!validarSenha()) {
        println("Senha incorreta! Tente novamente.")
        verExtrato(extrato)
        return
    }
    println("Últimas movimentações:")
    extrato.forEach { println(it) }
}

fun realizarSaque(saldo: Double): Double {
    println("\nSaque")
    if (!validarSenha()) {
        println("Senha incorreta! Tente novamente.")
        return realizarSaque(saldo)
    }

    print("Informe o valor do saque: R$ ")
    val valorStr = readLine()?.trim()
    val valor = valorStr?.toDoubleOrNull()

    if (valor == null) {
        println("Valor inválido. Por favor, informe um número válido.")
        return saldo
    }

    if (valor <= 0) {
        println("Operação não autorizada.")
        return saldo
    }

    if (valor > saldo) {
        println("Operação não autorizada.")
        return saldo
    }

    val novoSaldo = saldo - valor
    println("Saque de R$ %.2f realizado com sucesso!".format(valor))
    println("Saldo atual: R$ %.2f".format(novoSaldo))
    return novoSaldo
}

fun realizarDeposito(saldo: Double, extrato: MutableList<String>): Double {
    println("\n--- DEPÓSITO ---")
    print("Informe o valor do depósito: R$ ")
    val valorStr = readLine()?.trim()
    val valor = valorStr?.toDoubleOrNull()

    if (valor == null) {
        println("Valor inválido. Por favor, informe um número válido.")
        return saldo
    }

    if (valor <= 0) {
        println("Operação não autorizada.")
        return saldo
    }

    val novoSaldo = saldo + valor
    extrato.add("Depósito realizado          + R$ %.2f".format(valor))
    println("Depósito de R$ %.2f realizado com sucesso!".format(valor))
    println("Saldo atual: R$ %.2f".format(novoSaldo))
    return novoSaldo
}

fun realizarTransferencia(saldo: Double): Double {
    println("\n--- TRANSFERÊNCIA ---")
    if (!validarSenha()) {
        println("Senha incorreta! Tente novamente.")
        return realizarTransferencia(saldo)
    }

    print("Informe o número da conta destino: ")
    val contaStr = readLine()?.trim()

    if (contaStr.isNullOrBlank() || !contaStr.all { it.isDigit() }) {
        println("Número de conta inválido. Apenas números são aceitos.")
        return saldo
    }

    print("Informe o valor da transferência: R$ ")
    val valorStr = readLine()?.trim()
    val valor = valorStr?.toDoubleOrNull()

    if (valor == null) {
        println("Valor inválido. Por favor, informe um número válido.")
        return saldo
    }

    if (valor <= 0) {
        println("Operação não autorizada.")
        return saldo
    }

    if (valor > saldo) {
        println("Operação não autorizada.")
        return saldo
    }

    val novoSaldo = saldo - valor
    println("Transferência de R$ %.2f para a conta $contaStr realizada com sucesso!".format(valor))
    println("Saldo atual: R$ %.2f".format(novoSaldo))
    return novoSaldo
}

fun erro() {
    println("\nOpção inválida! Por favor, informe um número entre 1 a 6.")
}