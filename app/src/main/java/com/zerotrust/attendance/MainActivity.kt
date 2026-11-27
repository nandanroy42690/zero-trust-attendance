
package com.zerotrust.attendance

import android.os.Bundle
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.zerotrust.attendance.navigation.AppNavigation
import com.zerotrust.attendance.ui.theme.ZeroTrustAttendanceTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val preferences = getSharedPreferences(
            "zero_trust_settings",
            MODE_PRIVATE
        )

        val savedTheme = preferences.getString(
            "theme_mode",
            "system"
        ) ?: "system"

        setContent {
            var themeMode by rememberSaveable {
                mutableStateOf(savedTheme)
            }

            val systemDark = isSystemInDarkTheme()

            val darkTheme = when (themeMode) {
                "light" -> false
                "dark" -> true
                else -> systemDark
            }

            ZeroTrustAttendanceTheme(
                darkTheme = darkTheme
            ) {
                AppRoot(
                    themeMode = themeMode,
                    onThemeChange = { newMode ->
                        themeMode = newMode

                        preferences.edit()
                            .putString("theme_mode", newMode)
                            .apply()
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppRoot(
    themeMode: String,
    onThemeChange: (String) -> Unit
) {
    var menuExpanded by remember {
        mutableStateOf(false)
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "ZeroTrust",
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    Box {
                        IconButton(
                            onClick = {
                                menuExpanded = true
                            },
                            modifier = Modifier.size(48.dp)
                        ) {
                            Image(
                                painter = painterResource(
                                    id = when (themeMode) {
                                        "dark" -> R.drawable.theme_moon
                                        "light" -> R.drawable.theme_sun
                                        else -> R.drawable.theme_system
                                    }
                                ),
                                contentDescription = "Change theme",
                                modifier = Modifier.size(40.dp),
                                contentScale = ContentScale.Fit
                            )
                        }

                        DropdownMenu(
                            expanded = menuExpanded,
                            onDismissRequest = {
                                menuExpanded = false
                            }
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Text("System default")
                                },
                                onClick = {
                                    onThemeChange("system")
                                    menuExpanded = false
                                }
                            )

                            DropdownMenuItem(
                                text = {
                                    Text("Light mode")
                                },
                                onClick = {
                                    onThemeChange("light")
                                    menuExpanded = false
                                }
                            )

                            DropdownMenuItem(
                                text = {
                                    Text("Dark mode")
                                },
                                onClick = {
                                    onThemeChange("dark")
                                    menuExpanded = false
                                }
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->

        AppNavigation(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun LoginScreen(
    onSignIn: () -> Unit
) {
    var email by rememberSaveable {
        mutableStateOf("")
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }

    var message by rememberSaveable {
        mutableStateOf("")
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    val auth = remember {
        FirebaseAuth.getInstance()
    }

    val colors = MaterialTheme.colorScheme

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "ZERO TRUST",
                fontSize = 15.sp,
                color = colors.primary,
                fontWeight = FontWeight.Bold,
                letterSpacing = 4.sp
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Attendance System",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = colors.onBackground
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Secure. Simple. Reliable.",
                fontSize = 14.sp,
                color = colors.onSurfaceVariant
            )

            Spacer(Modifier.height(36.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = colors.surface
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 3.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {

                    Text(
                        text = "Welcome back",
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold,
                        color = colors.onSurface
                    )

                    Spacer(Modifier.height(6.dp))

                    Text(
                        text = "Sign in to continue to your account",
                        fontSize = 13.sp,
                        color = colors.onSurfaceVariant
                    )

                    Spacer(Modifier.height(24.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                            message = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Email address")
                        },
                        singleLine = true,
                        enabled = !isLoading,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(Modifier.height(16.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            message = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Password")
                        },
                        singleLine = true,
                        enabled = !isLoading,
                        visualTransformation =
                            PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(Modifier.height(8.dp))

                    TextButton(
                        onClick = {
                            message =
                                "Password reset will be connected in the next step."
                        },
                        modifier = Modifier.align(Alignment.End),
                        enabled = !isLoading
                    ) {
                        Text("Forgot password?")
                    }

                    Spacer(Modifier.height(12.dp))

                    Button(
                        onClick = {
                            val enteredEmail = email.trim()

                            when {
                                enteredEmail.isBlank() ||
                                        password.isBlank() -> {
                                    message =
                                        "Please enter your email and password."
                                }

                                !Patterns.EMAIL_ADDRESS
                                    .matcher(enteredEmail)
                                    .matches() -> {
                                    message =
                                        "Please enter a valid email address."
                                }

                                else -> {
                                    message = ""
                                    isLoading = true

                                    auth.signInWithEmailAndPassword(
                                        enteredEmail,
                                        password
                                    ).addOnCompleteListener { task ->

                                        isLoading = false

                                        if (task.isSuccessful) {
                                            password = ""
                                            onSignIn()
                                        } else {
                                            message =
                                                task.exception?.localizedMessage
                                                    ?: "Sign in failed. Please try again."
                                        }
                                    }
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        enabled = !isLoading,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colors.primary
                        )
                    ) {

                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(22.dp),
                                color = colors.onPrimary,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Text(
                                text = "Sign In",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    if (message.isNotEmpty()) {
                        Spacer(Modifier.height(16.dp))

                        Text(
                            text = message,
                            color = colors.error,
                            fontSize = 13.sp
                        )
                    }
                }
            }

            Spacer(Modifier.height(28.dp))

            Text(
                text = "ZERO TRUST CLASSROOM ATTENDANCE",
                fontSize = 10.sp,
                color = colors.onSurfaceVariant,
                letterSpacing = 1.sp
            )
        }
    }
}
