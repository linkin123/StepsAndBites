package com.example.stepsandbites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                "VitaFood",
                color = Color(0xFF4CAF50),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Nutrición Inteligente",
                color = Color.Gray,
                style = MaterialTheme.typography.bodySmall,
                fontSize = 11.sp
            )
        }
        Icon(
            Icons.Outlined.ShoppingCart,
            contentDescription = "Cart",
            modifier = Modifier.size(24.dp),
            tint = Color.Black
        )
    }
}

@Composable
fun AppBottomNavigation(currentRoute: String, onNavigate: (String) -> Unit) {
    Surface(
        shadowElevation = 8.dp,
        tonalElevation = 8.dp
    ) {
        Column {
            HorizontalDivider(
                color = Color.LightGray.copy(alpha = 0.5f),
                thickness = 1.dp
            )
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 0.dp,
                modifier = Modifier
            ) {
                val selectedColor = Color(0xFF4CAF50)
                val unselectedColor = Color.Gray

                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, null) },
                    label = { Text("Inicio", fontSize = 11.sp) },
                    selected = currentRoute == "inicio",
                    onClick = { onNavigate("home") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = selectedColor,
                        selectedTextColor = selectedColor,
                        unselectedIconColor = unselectedColor,
                        unselectedTextColor = unselectedColor,
                        indicatorColor = Color(0xFFE8F5E9)
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.CalendarMonth, null) },
                    label = { Text("Plan", fontSize = 11.sp) },
                    selected = currentRoute == "plan",
                    onClick = { onNavigate("WeeklyPlan") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = selectedColor,
                        selectedTextColor = selectedColor,
                        unselectedIconColor = unselectedColor,
                        unselectedTextColor = unselectedColor,
                        indicatorColor = Color(0xFFE8F5E9)
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.AutoMirrored.Filled.TrendingUp, null) },
                    label = { Text("Progreso", fontSize = 11.sp) },
                    selected = currentRoute == "progreso",
                    onClick = { onNavigate("progreso") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = selectedColor,
                        selectedTextColor = selectedColor,
                        unselectedIconColor = unselectedColor,
                        unselectedTextColor = unselectedColor,
                        indicatorColor = Color(0xFFE8F5E9)
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.History, null) },
                    label = { Text("Historial", fontSize = 11.sp) },
                    selected = currentRoute == "historial",
                    onClick = { onNavigate("historial") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = selectedColor,
                        selectedTextColor = selectedColor,
                        unselectedIconColor = unselectedColor,
                        unselectedTextColor = unselectedColor,
                        indicatorColor = Color(0xFFE8F5E9)
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Outlined.Person, null) },
                    label = { Text("Perfil", fontSize = 11.sp) },
                    selected = currentRoute == "perfil",
                    onClick = { onNavigate("perfil") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = selectedColor,
                        selectedTextColor = selectedColor,
                        unselectedIconColor = unselectedColor,
                        unselectedTextColor = unselectedColor,
                        indicatorColor = Color(0xFFE8F5E9)
                    )
                )
            }
        }
    }
}