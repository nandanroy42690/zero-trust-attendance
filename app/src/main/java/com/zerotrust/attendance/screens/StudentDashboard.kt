
package com.zerotrust.attendance.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StudentDashboard() {

    val colors = MaterialTheme.colorScheme

    var showCodeDialog by remember { mutableStateOf(false) }
    var attendanceCode by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var showInfoDialog by remember { mutableStateOf("") }

    // Placeholder values until backend integration
    val attendancePercentage = 0
    val presentCount = 0
    val absentCount = 0
    val totalClasses = presentCount + absentCount

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {

        // Header
        Text(
            text = "STUDENT PORTAL",
            color = colors.primary,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Your Dashboard",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = colors.onBackground
        )

        Spacer(Modifier.height(6.dp))

        Text(
            text = "Here's your attendance overview.",
            fontSize = 14.sp,
            color = colors.onSurfaceVariant
        )

        Spacer(Modifier.height(26.dp))

        // Attendance summary card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = colors.primaryContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(22.dp)
            ) {

                Text(
                    text = "OVERALL ATTENDANCE",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.3.sp,
                    color = colors.onPrimaryContainer
                )

                Spacer(Modifier.height(14.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "$attendancePercentage%",
                            fontSize = 42.sp,
                            fontWeight = FontWeight.Bold,
                            color = colors.onPrimaryContainer
                        )

                        Text(
                            text = "Attendance percentage",
                            fontSize = 13.sp,
                            color = colors.onPrimaryContainer.copy(
                                alpha = 0.8f
                            )
                        )
                    }

                    CircularProgressIndicator(
                        progress = { attendancePercentage / 100f },
                        modifier = Modifier.size(62.dp),
                        strokeWidth = 7.dp,
                        color = colors.primary,
                        trackColor = colors.onPrimaryContainer.copy(
                            alpha = 0.15f
                        )
                    )
                }

                Spacer(Modifier.height(18.dp))

                HorizontalDivider(
                    color = colors.onPrimaryContainer.copy(alpha = 0.2f)
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    text = "Your attendance statistics will appear here.",
                    fontSize = 12.sp,
                    color = colors.onPrimaryContainer.copy(alpha = 0.8f)
                )
            }
        }

        Spacer(Modifier.height(22.dp))

        // Statistics
        Text(
            text = "Attendance Statistics",
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            color = colors.onBackground
        )

        Spacer(Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            StatisticCard(
                title = "Present",
                value = presentCount.toString(),
                modifier = Modifier.weight(1f),
                accent = colors.primary
            )

            StatisticCard(
                title = "Absent",
                value = absentCount.toString(),
                modifier = Modifier.weight(1f),
                accent = colors.error
            )

            StatisticCard(
                title = "Total",
                value = totalClasses.toString(),
                modifier = Modifier.weight(1f),
                accent = colors.tertiary
            )
        }

        Spacer(Modifier.height(28.dp))

        // Mark attendance section
        Text(
            text = "Mark Attendance",
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            color = colors.onBackground
        )

        Spacer(Modifier.height(6.dp))

        Text(
            text = "Have your teacher's attendance code ready.",
            fontSize = 13.sp,
            color = colors.onSurfaceVariant
        )

        Spacer(Modifier.height(14.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = colors.surface
            ),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Text(
                    text = "Enter classroom code",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = colors.onSurface
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    text = "Your teacher will provide a temporary six-digit code.",
                    fontSize = 12.sp,
                    color = colors.onSurfaceVariant
                )

                Spacer(Modifier.height(16.dp))

                Button(
                    onClick = {
                        attendanceCode = ""
                        message = ""
                        showCodeDialog = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text(
                        text = "Enter Attendance Code",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(Modifier.height(28.dp))

        // Quick access
        Text(
            text = "Quick Access",
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            color = colors.onBackground
        )

        Spacer(Modifier.height(12.dp))

        QuickAccessCard(
            title = "Attendance History",
            description = "View your previous attendance records",
            symbol = "◷",
            onClick = {
                showInfoDialog = "Attendance History"
            }
        )

        Spacer(Modifier.height(12.dp))

        QuickAccessCard(
            title = "My Subjects",
            description = "View enrolled subjects and attendance",
            symbol = "▤",
            onClick = {
                showInfoDialog = "My Subjects"
            }
        )

        Spacer(Modifier.height(28.dp))

        Text(
            text = "ZERO TRUST CLASSROOM ATTENDANCE",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 10.sp,
            letterSpacing = 1.2.sp,
            color = colors.onSurfaceVariant
        )

        Spacer(Modifier.height(12.dp))
    }

    // Attendance code dialog
    if (showCodeDialog) {
        AlertDialog(
            onDismissRequest = {
                showCodeDialog = false
            },
            title = {
                Text(
                    text = "Enter attendance code",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column {
                    Text(
                        text = "Enter the six-digit code displayed by your teacher.",
                        color = colors.onSurfaceVariant,
                        fontSize = 13.sp
                    )

                    Spacer(Modifier.height(16.dp))

                    OutlinedTextField(
                        value = attendanceCode,
                        onValueChange = { newValue ->
                            attendanceCode = newValue
                                .filter { it.isDigit() }
                                .take(6)
                            message = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text("000000")
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.NumberPassword
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )

                    if (message.isNotEmpty()) {
                        Spacer(Modifier.height(10.dp))

                        Text(
                            text = message,
                            color = colors.error,
                            fontSize = 12.sp
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        message = if (attendanceCode.length != 6) {
                            "Please enter a valid six-digit code."
                        } else {
                            "Backend connection is pending. Attendance has not been submitted."
                        }
                    }
                ) {
                    Text("Submit")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showCodeDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }

    // Placeholder dialogs for future screens
    if (showInfoDialog.isNotEmpty()) {
        AlertDialog(
            onDismissRequest = {
                showInfoDialog = ""
            },
            title = {
                Text(showInfoDialog)
            },
            text = {
                Text(
                    "This feature will be connected when the relevant screens and backend APIs are implemented."
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showInfoDialog = ""
                    }
                ) {
                    Text("OK")
                }
            }
        )
    }
}

@Composable
private fun StatisticCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    accent: androidx.compose.ui.graphics.Color
) {
    val colors = MaterialTheme.colorScheme

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colors.surface
        ),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(
                        accent,
                        RoundedCornerShape(50)
                    )
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = value,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = colors.onSurface
            )

            Text(
                text = title,
                fontSize = 11.sp,
                color = colors.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun QuickAccessCard(
    title: String,
    description: String,
    symbol: String,
    onClick: () -> Unit
) {
    val colors = MaterialTheme.colorScheme

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = colors.surface
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(46.dp)
                    .background(
                        colors.secondaryContainer,
                        RoundedCornerShape(14.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = symbol,
                    fontSize = 25.sp,
                    color = colors.onSecondaryContainer
                )
            }

            Spacer(Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.onSurface
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = description,
                    fontSize = 11.sp,
                    color = colors.onSurfaceVariant
                )
            }

            Text(
                text = "›",
                fontSize = 28.sp,
                color = colors.primary
            )
        }
    }
}
