
package com.zerotrust.attendance.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RoleSelectionScreen(
    onRoleSelected: (String) -> Unit
) {
    val colors = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.Center
    ) {

        // Header
        Text(
            text = "ZEROTRUST",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 4.sp,
            color = colors.primary
        )

        Spacer(Modifier.height(22.dp))

        Text(
            text = "Welcome aboard.",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = colors.onBackground
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Choose your account type to continue.",
            fontSize = 15.sp,
            color = colors.onSurfaceVariant,
            lineHeight = 22.sp
        )

        Spacer(Modifier.height(32.dp))

        // Student
        RoleCard(
            title = "Student",
            description = "View attendance, subjects and reports",
            symbol = "🎓",
            accent = Color(0xFF5278F5),
            onClick = { onRoleSelected("student") }
        )

        Spacer(Modifier.height(16.dp))

        // Teacher
        RoleCard(
            title = "Teacher",
            description = "Create sessions and monitor attendance",
            symbol = "📚",
            accent = Color(0xFF159B7B),
            onClick = { onRoleSelected("teacher") }
        )

        Spacer(Modifier.height(16.dp))

        // Administrator
        RoleCard(
            title = "Administrator",
            description = "Manage students, teachers and reports",
            symbol = "🛡️",
            accent = Color(0xFF9464D5),
            onClick = { onRoleSelected("admin") }
        )

        Spacer(Modifier.height(36.dp))

        HorizontalDivider(
            color = colors.outlineVariant.copy(alpha = 0.5f)
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = "ZERO TRUST CLASSROOM ATTENDANCE",
            modifier = Modifier.fillMaxWidth(),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            fontSize = 10.sp,
            letterSpacing = 1.5.sp,
            color = colors.onSurfaceVariant
        )
    }
}

@Composable
private fun RoleCard(
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
                width = 1.dp,
                color = colors.outlineVariant.copy(alpha = 0.55f),
                shape = RoundedCornerShape(22.dp)
            ),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = colors.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp,
            pressedElevation = 6.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Icon container
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .background(
                        color = accent.copy(alpha = 0.13f),
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = symbol,
                    fontSize = 25.sp
                )
            }

            Spacer(Modifier.width(16.dp))

            // Role details
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = colors.onSurface
                )

                Spacer(Modifier.height(5.dp))

                Text(
                    text = description,
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    color = colors.onSurfaceVariant
                )
            }

            Spacer(Modifier.width(8.dp))

            // Arrow indicator
            Text(
                text = "›",
                fontSize = 30.sp,
                color = accent,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
