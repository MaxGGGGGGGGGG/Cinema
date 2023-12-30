package worker

import info.InfoImpl
import java.time.LocalDateTime
import java.time.format.DateTimeParseException

interface WorkerOperations {
    val worker : Worker
    fun workerOperations()
}

class WorkerOperationsImpl(private var info : InfoImpl) : WorkerOperations {
    override val worker = WorkerImpl()
    override fun workerOperations() {
        println("Если вы хотите поменять название фильма, введите 1," +
                " для других действий введите любую другую комбинацию символов:")
        val str1 = readln()
        if (str1 == "1") {
            println("Введите имя фильма, которое хотите поменять:")
            val name = readln()
            println("Введите новое имя фильма:")
            val newName = readln()
            worker.changeName(info, name, newName)
        } else {
            println("Если хотите добавить новый сеанс, введите 1, " +
                    "если хотите поменять время сеанса, введите 2, " +
                    "если хотите удалить сеанс, введите любую другую последовательность символов:\n")
            val str2 = readln()
            println("Введите название фильма:\n")
            val name = readln()
            val start: LocalDateTime
            val end: LocalDateTime
            try {
                println("Введите дату начала фильма в формате YY-MM-DDThh:mm:ss:\n")
                start = LocalDateTime.parse(readln())
                println("Введите дату конца фильма в формате YY-MM-DDThh:mm:ss:\n")
                end = LocalDateTime.parse(readln())
            } catch (e: DateTimeParseException) {
                println("Дата введена некорректно\n")
                return
            }
            when (str2) {
                "1" -> worker.addFilm(info, name, start, end)
                "2" -> worker.changeTime(info, name, start, end)
                else -> worker.deleteFilm(info, name, start, end)
            }
        }
    }
}