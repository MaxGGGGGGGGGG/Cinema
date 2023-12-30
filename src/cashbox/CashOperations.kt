package cashbox

import info.InfoImpl

interface CashOperations {
    val cashRegister : CashRegister
    fun cashOperations()
}

class CashOperationsImpl(private var info : InfoImpl) : CashOperations {
    override val cashRegister = CashRegisterImpl()
    override fun cashOperations() {
        println("Если хотите продать билет, введите 1," +
                " если хотите сделать возврат, введите 2," +
                " если хотите отметить место занятым, введите любую другую последовательность символов:\n")
        val str1 = readln()
        println("Введите название фильма:\n")
        val name = readln()
        println("Введите номер билета:\n")
        val seat: Int
        try {
            seat = readln().toInt()
        } catch (e: NumberFormatException) {
            println("В качестве номера билета необходимо ввести натуральное число от 1 до 10\n")
            return
        }
        when (str1) {
            "1" -> cashRegister.sell(info, name, seat)
            "2" -> cashRegister.ret(info, name, seat)
            else -> cashRegister.take(info, name, seat)
        }
    }
}