package login

interface LoginService {
    val login : Login
    fun login() : Boolean
}

class LoginServiceImpl : LoginService {
    override val login = LoginImpl()
    override fun login() : Boolean {
        while (true) {
            println("Если хотите выйти, введите 0, если хотите авторизоваться, введите 1, " +
                    "если хотите зарегистрироваться, введите любую другую последовательность символов:\n")
            val str = readln()
            if (str == "0") {
                return true
            }
            println("Введите логин:\n")
            val log = readln()
            println("Введите пароль:\n")
            val password = readln()
            val flag = if (str == "1") {
                login.authorize(log, password)
            } else {
                login.register(log, password)
            }
            if (flag) {
                break
            }
        }
        return false
    }
}