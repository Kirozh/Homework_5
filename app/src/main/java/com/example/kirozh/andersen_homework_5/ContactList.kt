package com.example.kirozh.andersen_homework_5

/**
 * @author Kirill Ozhigin on 11.10.2021
 */
class ContactList {
    companion object {
        var contacts = mutableListOf(
            Contact(0, "Саша", "Александров", "+7(911)-111-11-11"),
            Contact(1, "Вася", "Васин", "+7(911)-111-11-12"),
            Contact(2, "Петя", "Петров", "+7(911)-111-11-13"),
            Contact(3, "Коля", "Николаев", "+7(911)-111-11-14")
        )
    }
}