import cashbox.CashOperationsImpl
import info.GetInformationImpl
import info.InfoImpl
import login.LoginServiceImpl
import worker.WorkerOperationsImpl

fun main() {
    val loginServiceImpl = LoginServiceImpl()
    val info = InfoImpl(mutableMapOf())
    val cashOperationsImpl = CashOperationsImpl(info)
    val workerOperationsImpl = WorkerOperationsImpl(info)
    val getInformation = GetInformationImpl()
    if (loginServiceImpl.login()) return
    while (true) {
        println("Если хотите провести кассовую операцию, нажмите 1," +
                " если хотите получить какую-либо информацию, нажмите 2," +
                " если хотите внести какие-либо изменения в сеансы, нажмите 3," +
                " если хотите закончить, введите любую другую комбинацию символов")
        val str = readln()
        when (str) {
            "1" -> {
                cashOperationsImpl.cashOperations()
            }
            "2" -> {
                println("Если хотите получить список сеансов, введите 1," +
                        " если хотите посмотреть свободные места, введите любую другую комбинацию символов:\n")
                val str1 = readln()
                when (str1) {
                    "1" -> getInformation.getSchedule(info)
                    else -> {
                        println("Введите название фильма:\n")
                        val film = readln()
                        getInformation.getSeats(info, film)
                    }
                }
            }
            "3" -> {
                workerOperationsImpl.workerOperations()
            }
            else -> return
        }
    }
}