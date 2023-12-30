package info

import java.time.LocalDateTime

const val NUM = 9

interface GetInformation {
    fun getSchedule(info: Info)
    fun getSeats(info: Info, name: String)
}

class GetInformationImpl : GetInformation {
    override fun getSchedule(info: Info) {
        var flag = true
        for (x in info.information) {
            if (x.value!!.first > LocalDateTime.now()) {
                println("Название: ${x.key}, начало: ${x.value!!.first}, окончание: ${x.value!!.second}")
                flag = false
            }
        }
        if (flag) {
            println("На данный момент нет актуальных сеансов\n")
        }
    }
    override fun getSeats(info: Info, name: String) {
        if (info.information.containsKey(name) && info.information[name]!!.first > LocalDateTime.now()) {
            var str = "Свободны следующие места:"
            for(i in 0..NUM) {
                if (info.information[name]!!.third[i] == 1) {
                    str += " ${i + 1}"
                }
            }
            println(str)
        } else {
            println("В расписании нет такого фильма\n")
        }
    }
}