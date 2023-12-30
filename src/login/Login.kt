package login
interface Login {
     fun authorize(login: String, password: String) : Boolean
     fun register(login: String, password: String) : Boolean
}

class LoginImpl : Login {
    private var members: MutableMap<String, String> = mutableMapOf()
    override fun authorize(login: String, password: String) : Boolean {
        if (!members.containsKey(login)) {
            println("Нет пользователя с таким логином\n")
            return false
        }
        if (members[login] != password) {
            println("Неверный пароль\n")
        }
        return members[login] == password
    }

    override fun register(login: String, password: String) : Boolean {
        if (members.containsKey(login)) {
            println("Этот логин уже занят\n")
            return false
        }
        members[login] = password
        return true
    }
}