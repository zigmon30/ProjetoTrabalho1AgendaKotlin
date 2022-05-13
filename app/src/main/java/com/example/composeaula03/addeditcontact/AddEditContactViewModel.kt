package com.example.composeaula03.addeditcontact

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composeaula03.data.Contact

class AddEditContactViewModel : ViewModel(){
    val id : MutableLiveData<Int> = MutableLiveData(5)
    val name : MutableLiveData<String> = MutableLiveData("")
    val number : MutableLiveData<String> = MutableLiveData("")
    val address : MutableLiveData<String> = MutableLiveData("")

    fun insertContact(
        onInsertContact: (Contact) -> Unit
    ){
        val newContact = Contact(
            id.value ?: return,
            name.value  ?: return,
            number.value  ?: return,
            address.value  ?: return
        )
        onInsertContact(newContact)
        var tempId: Int = id.value ?: return
        tempId++
        id.value = tempId

        name.value = ""
        number.value = ""
        address.value = ""


    }
}