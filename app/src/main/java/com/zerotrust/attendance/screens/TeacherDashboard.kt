
package com.zerotrust.attendance.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TeacherDashboard() {
    val colors = MaterialTheme.colorScheme
    var showCreateDialog by remember { mutableStateOf(false) }
    var showClassesDialog by remember { mutableStateOf(false) }
    var showRecordsDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 22.dp, vertical = 24.dp)
    ) {
        Text(
            "TEACHER PORTAL",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp,
            color = colors.primary
        )

        Spacer(Modifier.height(12.dp))

        Text(
            "Your Dashboard",
            fontSize = 29.sp,
            fontWeight = FontWeight.Bold,
            color = colors.onBackground
        )

        Spacer(Modifier.height(6.dp))

        Text(
            "Manage your classrooms and attendance.",
            fontSize = 14.sp,
            color = colors.onSurfaceVariant
        )

        Spacer(Modifier.height(26.dp))

        // Overview statistics
        Text(
            "OVERVIEW",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.5.sp,
            color = colors.onSurfaceVariant
        )

        Spacer(Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TeacherStatCard(
                title = "Active Sessions",
                value = "0",
                symbol = "●",
                accent = Color(0xFF159B7B),
                modifier = Modifier.weight(1f)
            )

            TeacherStatCard(
                title = "Total Students",
                value = "0",
                symbol = "♟",
                accent = Color(0xFF5278F5),
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.height(28.dp))

        // Main action
        Text(
            "CLASSROOM MANAGEMENT",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.5.sp,
            color = colors.onSurfaceVariant
        )

        Spacer(Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(
                containerColor = colors.primary
            ),
            elevation = CardDefaults.cardElevation(3.dp)
        ) {
            Column(Modifier.padding(20.dp)) {
                Text("＋", fontSize = 30.sp, color = colors.onPrimary)

                Spacer(Modifier.height(12.dp))

                Text(
                    "Create Attendance Session",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    color = colors.onPrimary
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    "Start a classroom attendance session using a secure code.",
                    fontSize = 13.sp,
                    lineHeight = 19.sp,
                    color = colors.onPrimary.copy(alpha = 0.85f)
                )

                Spacer(Modifier.height(18.dp))

                Button(
                    onClick = { showCreateDialog = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.surface,
                        contentColor = colors.primary
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        "Create Session",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        // Quick access
        Text(
            "QUICK ACCESS",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.5.sp,
            color = colors.onSurfaceVariant
        )

        Spacer(Modifier.height(12.dp))

        TeacherActionCard(
            title = "My Assigned Classes",
            description = "View your classes and student lists",
            symbol = "▤",
            accent = Color(0xFF5278F5),
            onClick = { showClassesDialog = true }
        )

        Spacer(Modifier.height(12.dp))

        TeacherActionCard(
            title = "Attendance Records",
            description = "Review previous attendance reports",
            symbol = "▥",
            accent = Color(0xFF159B7B),
            onClick = { showRecordsDialog = true }
        )

        Spacer(Modifier.height(24.dp))

        // Active session placeholder
        Text(
            "SESSION STATUS",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.5.sp,
            color = colors.onSurfaceVariant
        )

        Spacer(Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = colors.surface
            ),
            elevation = CardDefaults.cardElevation(1.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .background(
                            colors.surfaceVariant.copy(alpha = 0.55f),
                            RoundedCornerShape(13.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text("◷", fontSize = 22.sp, color = colors.onSurfaceVariant)
                }

                Spacer(Modifier.width(14.dp))

                Column {
                    Text(
                        "No active sessions",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                        color = colors.onSurface
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "Your active classroom sessions will appear here.",
                        fontSize = 12.sp,
                        color = colors.onSurfaceVariant
                    )
                }
            }
        }

        Spacer(Modifier.height(30.dp))

        HorizontalDivider(color = colors.outlineVariant.copy(alpha = 0.5f))

        Spacer(Modifier.height(16.dp))

        Text(
            "ZERO TRUST CLASSROOM ATTENDANCE",
            modifier = Modifier.fillMaxWidth(),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            fontSize = 10.sp,
            letterSpacing = 1.3.sp,
            color = colors.onSurfaceVariant
        )

        Spacer(Modifier.height(12.dp))
    }

    // Temporary dialogs until backend integration
    if (showCreateDialog) {
        AlertDialog(
            onDismissRequest = { showCreateDialog = false },
            title = { Text("Create Attendance Session") },
            text = {
                Text(
                    "Session creation will be connected to the backend. " +
                            "The secure attendance code will be generated by the server."
                )
            },
            confirmButton = {
                TextButton(onClick = { showCreateDialog = false }) {
                    Text("OK")
                }
            }
        )
    }

    if (showClassesDialog) {
        AlertDialog(
            onDismissRequest = { showClassesDialog = false },
            title = { Text("Assigned Classes") },
            text = {
                Text("Your assigned classes will appear here after backend integration.")
            },
            confirmButton = {
                TextButton(onClick = { showClassesDialog = false }) {
                    Text("OK")
                }
            }
        )
    }

    if (showRecordsDialog) {
        AlertDialog(
            onDismissRequest = { showRecordsDialog = false },
            title = { Text("Attendance Records") },
            text = {
                Text("Attendance reports will appear here after backend integration.")
            },
            confirmButton = {
                TextButton(onClick = { showRecordsDialog = false }) {
                    Text("OK")
                }
            }
        )
    }
}

@Composable
private fun TeacherStatCard(
    title: String,
    value: String,
    symbol: String,
    accent: Color,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = colors.surface
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .background(
                        accent.copy(alpha = 0.13f),
                        RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    symbol,
                    fontSize = 20.sp,
                    color = accent
                )
            }

            Spacer(Modifier.height(16.dp))

            Text(
                value,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = colors.onSurface
            )

            Spacer(Modifier.height(4.dp))

            Text(
                title,
                fontSize = 12.sp,
                color = colors.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun TeacherActionCard(
    title: String,
    description: String,
    symbol: String,
    accent: Color,
    onClick: () -> Unit
) {
    val colors = MaterialTheme.colorScheme

    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .border(
                1.dp,
                colors.outlineVariant.copy(alpha = 0.5f),
                RoundedCornerShape(18.dp)
            ),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = colors.surface
        ),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(17.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        accent.copy(alpha = 0.12f),
                        RoundedCornerShape(14.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(symbol, fontSize = 22.sp, color = accent)
            }

            Spacer(Modifier.width(15.dp))

            Column(Modifier.weight(1f)) {
                Text(
                    title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = colors.onSurface
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    description,
                    fontSize = 12.sp,
                    color = colors.onSurfaceVariant
                )
            }

            Text(
                "›",
                fontSize = 28.sp,
                color = accent
            )
        }
    }
}
