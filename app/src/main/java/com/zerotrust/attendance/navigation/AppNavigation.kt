
package com.zerotrust.attendance.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.zerotrust.attendance.LoginScreen
import com.zerotrust.attendance.ui.screens.AdminDashboard
import com.zerotrust.attendance.ui.screens.StudentDashboard
import com.zerotrust.attendance.ui.screens.TeacherDashboard

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login",
        modifier = modifier
    ) {

        composable("login") {
            LoginScreen(
                onSignIn = {
                    navController.navigate("awaiting_role") {
                        popUpTo("login") {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable("awaiting_role") {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                Text("Authentication successful")

                Text(
                    "Your institution account and role must be verified."
                )

                Button(
                    onClick = {
                        FirebaseAuth.getInstance().signOut()

                        navController.navigate("login") {
                            popUpTo("awaiting_role") {
                                inclusive = true
                            }
                        }
                    }
                ) {
                    Text("Sign out")
                }
            }
        }

        // Dashboard routes retained for development.
        // Backend role verification will protect these later.

        composable("student") {
            StudentDashboard()
        }

        composable("teacher") {
            TeacherDashboard()
        }

        composable("admin") {
            AdminDashboard()
        }
    }
}
