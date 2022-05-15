package com.example.composeaula03.contactlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composeaula03.data.Contact

class ContactListViewModel : ViewModel() {

    private val _contactList: MutableLiveData<List<Contact>> = MutableLiveData(
        listOf(
            Contact(
                0,
                "Douglas Kelczeski",
                "(47) 9 9658932",
                " Rua Projetada 2, n° 16, São Cristóvão Três Barras -SC"
            ),
            Contact(
                1,
                "Elenita Zaluski Kelczeski",
                "(47) 9 9658932",
                " Rua Projetada 2, n° 16, São Cristóvão Três Barras -SC"
            ),
            Contact(
                2,
                "Josuel Kelczeski",
                "(47) 9 9658932",
                " Rua Projetada 2, n° 16, São Cristóvão Três Barras -SC"
            ),
            Contact(
                3,
                "Simão  Kelczeski",
                "(47) 9 9658932",
                " Rua Projetada 2, n° 16, São Cristóvão Três Barras -SC"
            ),
            Contact(
                4,
                "Adriano José Kelczeski",
                "(47) 9 9658932",
                " Rua Projetada 2, n° 16, São Cristóvão Três Barras -SC"
            ),



            )
    )

    private val _filterBy: MutableLiveData<String> = MutableLiveData("")

    val filterBy: LiveData<String>
        get() = _filterBy

    val contactList : LiveData<List<Contact>>
        get() {
            return if (_filterBy.value == "")
                _contactList
            else{
                val list: List<Contact> = _contactList.value?.filter {contact ->  
                    contact.name.contains(_filterBy.value ?: "")
                } ?: listOf()
                MutableLiveData(list)
            }
        }

    fun updateFilter(
        newFilter: String
    ) {
        _filterBy.value = newFilter
    }

    fun insertContact(contact: Contact) {
        val list: MutableList<Contact> = _contactList.value?.toMutableList() ?: return
        list.add(contact)
        _contactList.value = list
    }

    fun getContact(id: Int): Contact {
        _contactList.value?.forEach{contact ->
            if(id == contact.id)
                return contact

        }
        return Contact(
            id -1,
            "",
            "",
            ""
        )
    }
}