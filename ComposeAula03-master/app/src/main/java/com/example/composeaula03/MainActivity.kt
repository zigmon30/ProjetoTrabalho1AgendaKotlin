package com.example.composeaula03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import com.example.composeaula03.addeditcontact.AddEditContactScreen
import com.example.composeaula03.addeditcontact.AddEditContactViewModel
import com.example.composeaula03.contactlist.ContactListScreen
import com.example.composeaula03.contactlist.ContactListViewModel
import com.example.composeaula03.ui.theme.ComposeAula03Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val contactListViewModel: ContactListViewModel by viewModels()
        val addEditContactListViewModel: AddEditContactViewModel by viewModels()

        setContent {
            ComposeAula03Theme {
                // A surface container using the 'background' color from the theme
                MyApp(
                    contactListViewModel,
                    addEditContactListViewModel
                )
            }
        }
    }
}

@Composable
fun MyApp(
    contactListViewModel: ContactListViewModel,
    addContactListViewModel: AddEditContactViewModel
) {
    val navController = rememberNavController()

    Scaffold(){
        NavHost(navController = navController, startDestination = "contactlist"){
            composable("contactlist"){
                ContactListScreen(navController, contactListViewModel)
            }
            composable(
                route = "addeditcontact?id={id}",
                arguments = listOf(navArgument("id"){
                    defaultValue = -1
                    type = NavType.IntType
                }
                )

            ){  navBackStackEntry ->
                val id = navBackStackEntry.arguments?.getInt("id") ?: -1
                val contact = contactListViewModel.getContact(id)
                AddEditContactScreen(
                    navController,
                    addContactListViewModel,
                    contactListViewModel::insertContact,
                    contactListViewModel::updateContact,

                    contact
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    ComposeAula03Theme {

    }
}