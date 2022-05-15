package com.example.composeaula03.contactlist

import android.R
import android.provider.ContactsContract
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.example.composeaula03.data.Contact


@Composable
fun ContactListScreen(
    navController: NavController,
    contactListViewModel: ContactListViewModel,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(route = "addeditcontact")
               /* contactListViewModel.insertContact(
                    Contact(
                        10,
                        "test",
                        "123",
                        "test"
                    )

                )*/
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Contact")
                
            }
        }
    ) {
        val contactList by contactListViewModel.contactList.observeAsState(listOf())
        val filter by contactListViewModel.filterBy.observeAsState("")

        Column() {
            SearchContact(
                filter,
                contactListViewModel::updateFilter
            )
            ContactList(
                contacts = contactList,
                navController = navController
            )
        }
    }
}

@Composable
fun SearchContact(
    filter: String,
    onFilterChange: (String) -> Unit

) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        label = {
                Row() {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")

                }

        },
        value = filter,
        onValueChange = onFilterChange
    )
    
}

@Composable
fun ContactList(
    contacts: List<Contact>,
    navController: NavController
) {
    LazyColumn(){
        items(contacts){  contact ->
            ContactEntry(contact = contact) {
                navController.navigate("addeditcontact")
            }

        }

    }

}

@Composable
fun ContactEntry(
    contact: Contact,
    onEdit: () -> Unit
) {
    var expanded by remember {
        mutableStateOf(value = false)
    }
    Card(
        modifier = Modifier
            .padding(1.dp)
            .clickable {
                expanded = !expanded
            }
            .animateContentSize(
                spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.DarkGray),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Box(
                    modifier = Modifier
                        .padding(6.dp)
                        .clip(CircleShape)
                        .size(60.dp)
                        .background(Color.Green),
                    contentAlignment = Alignment.Center

                ) {
                    Text(
                        text = "${contact.name[0].uppercase()}",
                        style = MaterialTheme.typography.h3
                    )

                }

                Text(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .weight(weight = 1f),
                    text = contact.name,
                    style = MaterialTheme.typography.h6

                )
                if(expanded) {
                    Icon(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(40.dp)
                            .clickable {
                                onEdit()
                            }
                            .background(Color.Transparent),
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit")
                }



            }
            if (expanded) {
                Text(
                    modifier = Modifier.padding(start = 6.dp),
                    text = contact.number,
                    style = MaterialTheme.typography.subtitle2

                )
                Text(
                    modifier = Modifier.padding(start = 6.dp, bottom = 6.dp, end = 6.dp),
                    text = contact.address,
                    style = MaterialTheme.typography.subtitle2

                )
            }

        }


    }

}
/*
@Preview
@Composable
fun ContactListPreview() {
    val viewModel: ContactListViewModel = viewModel()
    val contactList by viewModel.contactList.observeAsState()
    ContactList(contacts = contactList ?: listOf())
}

@Preview
@Composable
fun ContactListScreenPreview() {
    val viewModel: ContactListViewModel = viewModel()
    ContactListScreen(rememberNavController(), viewModel)

}

@Preview
@Composable
fun ContactEntryPreview() {
    ContactEntry(
        Contact(
            0,
            "Douglas Kelczeski",
            "(47) 9 9658932",
            " Rua Projetada 2, n° 16, São Cristóvão Três Barras -SC"
        )
    ))
}

*/