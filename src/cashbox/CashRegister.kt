package cashbox

import info.Info
import java.time.LocalDateTime

const val NUM = 10
const val NUM1 = 3

interface CashRegister {
    fun sell(info: Info, name: String, seat: Int)
    fun ret(info: Info, name: String, seat: Int)
    fun take(info: Info, name: String, seat: Int)
}

class CashRegisterImpl : CashRegister {
    override fun sell(info: Info, name: String, seat: Int) {
        if (!info.information.containsKey(name) || LocalDateTime.now() >= info.information[name]?.first) {
            println("В актуальном расписании нет такого фильма.\n")
            return
        }
        if (seat < 1 || seat > NUM || info.information[name]!!.third[seat - 1] != 1) {
            println("Либо введено некорректное значения места, либо это место занято")
            return
        }
        info.information[name]!!.third[seat - 1] = 2
    }

    override fun ret(info: Info, name: String, seat: Int) {
        if (!info.information.containsKey(name)) {
            println("В актуальном расписании нет такого фильма\n")
            return
        }
        if (LocalDateTime.now() < info.information[name]?.first) {
            info.information[name]!!.third[seat - 1] = 1
        } else {
            println("Нельзя вернуть билет после начала показа.\n")
        }
    }

    override fun take(info: Info, name: String, seat: Int) {
        if (!info.information.containsKey(name) || info.information[name]!!.second < LocalDateTime.now() || info.information[name]!!.first > LocalDateTime.now()) {
            println("В актуальном расписании нет такого фильма, либо сеанс ещё не начался\n")
            return
        }
        if (seat < 1 || seat > 10) {
            println("Введено некорректное место\n")
            return
        }
        info.information[name]!!.third[seat - 1] = NUM1
    }
}