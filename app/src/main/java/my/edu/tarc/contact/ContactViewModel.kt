package my.tarc.mycontact

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ContactViewModel (application: Application): AndroidViewModel(application) {
    //LiveData gives us updated contacts when they change
    //will not destroy data when the layout change configuration
    var contactList : LiveData<List<Contact>>
    private val repository: ContactRepository //instance of repository

    init {
        val contactDao = ContactDatabase.getDatabase(application).contactDao()
        repository = ContactRepository(contactDao)
        contactList = repository.allContacts
    }

    //launch - use to undergo suspend function
    fun addContact(contact: Contact) = viewModelScope.launch{
         repository.add(contact)
    }
}
