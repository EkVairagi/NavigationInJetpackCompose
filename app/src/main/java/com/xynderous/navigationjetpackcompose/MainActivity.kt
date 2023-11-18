package com.xynderous.navigationjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Applier
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.xynderous.navigationjetpackcompose.ui.theme.NavigationJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //App()
            SecondWayToNavigate()
        }
    }
}

//One way to use navigation in jetpack compose
@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "registration"){
        composable(route = "registration"){
            RegistrationCompose(navController)
        }
        composable(route = "login"){
            LoginCompose()
        }
        composable(route = "main"){
            //MainCompose(em)
        }
    }
}

//another way to use navigation in jetpack compose

@Composable
fun SecondWayToNavigate() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "registration"){
        composable(route = "registration"){
            RegistrationComposeAW {
                navController.navigate("main/${it}")
            }
        }
        composable(route = "login"){
            LoginCompose()
        }
        composable(route = "main/{email}", arguments = listOf(
            navArgument("email"){
                type = NavType.StringType
            }
        )){

            val email = it.arguments?.getString("email")
            MainCompose(email)
        }
    }
}

@Composable
fun RegistrationComposeAW(onClick: (email:String) -> Unit) {
    Text(
        text = "Registration Screen",
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.clickable {
            onClick(
                "sangamnayak@gmail.com"
            )
        }
    )
}




@Composable
fun RegistrationCompose(navController: NavController) {
    Text(
        text = "Registration Screen",
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.clickable {
            navController.navigate("login")
        }
    )
}

@Composable
fun LoginCompose() {
    Text(text = "Login Screen", style = MaterialTheme.typography.bodyLarge)
}

@Composable
fun MainCompose(email:String?) {
    Text(text = "Main Screen - $email", style = MaterialTheme.typography.bodyLarge)
}

