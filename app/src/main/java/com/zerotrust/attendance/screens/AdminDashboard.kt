
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
fun AdminDashboard() {
    val colors = MaterialTheme.colorScheme

    var showStudents by remember { mutableStateOf(false) }
    var showTeachers by remember { mutableStateOf(false) }
    var showReports by remember { mutableStateOf(false) }
    var showSettings by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 22.dp, vertical = 24.dp)
    ) {
        Text(
            "ADMINISTRATION PORTAL",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp,
            color = colors.primary
        )

        Spacer(Modifier.height(12.dp))

        Text(
            "Admin Dashboard",
            fontSize = 29.sp,
            fontWeight = FontWeight.Bold,
            color = colors.onBackground
        )

        Spacer(Modifier.height(6.dp))

        Text(
            "Manage your institution from one place.",
            fontSize = 14.sp,
            color = colors.onSurfaceVariant
        )

        Spacer(Modifier.height(26.dp))

        // Institution overview
        Text(
            "INSTITUTION OVERVIEW",
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
            AdminStatCard(
                title = "Students",
                value = "0",
                symbol = "♟",
                accent = Color(0xFF5278F5),
                modifier = Modifier.weight(1f)
            )

            AdminStatCard(
                title = "Teachers",
                value = "0",
                symbol = "♙",
                accent = Color(0xFF159B7B),
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.height(12.dp))

        AdminStatCard(
            title = "Attendance Sessions",
            value = "0",
            symbol = "▤",
            accent = Color(0xFF9464D5),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(28.dp))

        // Management section
        Text(
            "INSTITUTION MANAGEMENT",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.5.sp,
            color = colors.onSurfaceVariant
        )

        Spacer(Modifier.height(12.dp))

        AdminActionCard(
            title = "Manage Students",
            description = "View and manage student accounts",
            symbol = "♟",
            accent = Color(0xFF5278F5),
            onClick = { showStudents = true }
        )

        Spacer(Modifier.height(12.dp))

        AdminActionCard(
            title = "Manage Teachers",
            description = "View and manage teacher accounts",
            symbol = "♙",
            accent = Color(0xFF159B7B),
            onClick = { showTeachers = true }
        )

        Spacer(Modifier.height(12.dp))

        AdminActionCard(
            title = "Attendance Reports",
            description = "Review institution attendance statistics",
            symbol = "▥",
            accent = Color(0xFF9464D5),
            onClick = { showReports = true }
        )

        Spacer(Modifier.height(12.dp))

        AdminActionCard(
            title = "Institution Settings",
            description = "Configure institution-level settings",
            symbol = "⚙",
            accent = Color(0xFFE49A35),
            onClick = { showSettings = true }
        )

        Spacer(Modifier.height(26.dp))

        // System status
        Text(
            "SYSTEM STATUS",
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
                    Text("◈", fontSize = 22.sp, color = colors.onSurfaceVariant)
                }

                Spacer(Modifier.width(14.dp))

                Column {
                    Text(
                        "Backend connection pending",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = colors.onSurface
                    )

                    Spacer(Modifier.height(4.dp))

                    Text(
                        "Live institution data will appear after integration.",
                        fontSize = 12.sp,
                        color = colors.onSurfaceVariant
                    )
                }
            }
        }

        Spacer(Modifier.height(30.dp))

        HorizontalDivider(
            color = colors.outlineVariant.copy(alpha = 0.5f)
        )

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

    if (showStudents) {
        AdminInfoDialog(
            title = "Manage Students",
            message = "Student account management will be available after backend integration.",
            onDismiss = { showStudents = false }
        )
    }

    if (showTeachers) {
        AdminInfoDialog(
            title = "Manage Teachers",
            message = "Teacher account management will be available after backend integration.",
            onDismiss = { showTeachers = false }
        )
    }

    if (showReports) {
        AdminInfoDialog(
            title = "Attendance Reports",
            message = "Institution-wide attendance reports will appear here after backend integration.",
            onDismiss = { showReports = false }
        )
    }

    if (showSettings) {
        AdminInfoDialog(
            title = "Institution Settings",
            message = "Institution configuration will be available after backend integration.",
            onDismiss = { showSettings = false }
        )
    }
}

@Composable
private fun AdminStatCard(
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
                Text(symbol, fontSize = 20.sp, color = accent)
            }

            Spacer(Modifier.height(14.dp))

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
private fun AdminActionCard(
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

@Composable
private fun AdminInfoDialog(
    title: String,
    message: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = { Text(message) },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("OK")
            }
        }
    )
}
