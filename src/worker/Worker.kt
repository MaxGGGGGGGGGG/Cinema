package worker

import info.Info
import java.time.LocalDateTime

const val SIZE = 10

interface Worker {
    fun addFilm(info: Info, name: String, start: LocalDateTime, end: LocalDateTime)
    fun deleteFilm(info: Info, name: String, start: LocalDateTime, end: LocalDateTime)
    fun changeTime(info: Info, name: String, start: LocalDateTime, end: LocalDateTime)
    fun changeName(info: Info, oldName: String, newName: String)
}

class WorkerImpl : Worker {
    override fun addFilm(info: Info, name: String, start: LocalDateTime, end: LocalDateTime) {
        var flag = false
        if (end <= start) {
            println("Конец фильма не может быть раньше его начала\n")
            return
        }
        for(x in info.information) {
            if (x.key == name) {
                println("Сеанс с таким фильмом уже есть\n")
                flag = true
            }
            val cond1 = x.value!!.first < start && start < x.value!!.second
            val cond2 = x.value!!.first < end && end < x.value!!.second
            if (cond1 || cond2) {
                println("Новый сеанс не должен пересекаться с другими сеансами\n")
                flag = true
            }
            if (flag) {
                return
            }
        }
        val arr: Array<Int> = Array(SIZE) { 1 }
        info.information[name] = Triple(start, end, arr)
    }
    override fun deleteFilm(info: Info, name: String, start: LocalDateTime, end: LocalDateTime) {
        if (end <= start) {
            println("Конец фильма не может быть раньше его начала\n")
            return
        }
        if (info.information.containsKey(name) &&
            info.information[name]?.first == start &&
            info.information[name]?.second == end) {
            info.information.remove(name)
        } else {
            println("Нет сеанса с фильмом $name\n")
        }
    }
    override fun changeTime(info: Info, name: String, start: LocalDateTime, end: LocalDateTime) {
        if (end <= start) {
            println("Конец фильма не может быть раньше его начала\n")
            return
        }
        if (info.information.containsKey(name)) {
            deleteFilm(info, name, start, end)
            addFilm(info, name, start, end)
        } else {
            println("Нет сеанса с фильмом $name\n")
        }
    }
    override fun changeName(info: Info, oldName: String, newName: String) {
        if (info.information.containsKey(oldName)) {
            info.information[newName] = info.information[oldName]
            info.information.remove(oldName)
        } else {
            println("Нет сеанса с фильмом $oldName\n")
        }
    }
}