package com.example.stepsandbites

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun App() {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color(0xFF2E7D32),
            onPrimary = Color.White,
            primaryContainer = Color(0xFFE8F5E9),
            onPrimaryContainer = Color(0xFF1B5E20),
            surface = Color.White,
        )
    ) {
        Scaffold(
            topBar = { TopBar() },
            bottomBar = { BottomNav() }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color(0xFFF9F9F9))
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    item { HeaderSection() }

                    item {
                        SectionHeader(title = "Desayuno", icon = "🌅")
                    }

                    items(desayunoItems) { item ->
                        FoodCard(item)
                    }

                    item {
                        SectionHeader(title = "Comida", icon = "🍽️")
                    }

                    items(comidaItems) { item ->
                        FoodCard(item)
                    }

                    item {
                        SectionHeader(title = "Cena", icon = "🌙")
                    }

                    items(cenaItems) { item ->
                        FoodCard(item)
                    }

                    item {
                        SectionHeader(title = "Snacks", icon = "🥜")
                    }

                    items(snackItems) { item ->
                        FoodCard(item)
                    }

                    item {
                        SectionHeader(title = "Bebidas", icon = "🥤")
                    }

                    items(bebidasItems) { item ->
                        FoodCard(item)
                    }

                    // Extra padding for the floating banner
                    item { Spacer(modifier = Modifier.height(100.dp)) }
                }

                // Green Banner at the bottom of the list
                PlanificaBanner(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                )
            }
        }
    }
}


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
fun HeaderSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE8F5E9))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Buen día 👋", color = Color.Gray, fontSize = 14.sp)
            Surface(
                shape = RoundedCornerShape(24.dp),
                color = Color.White,
                shadowElevation = 1.dp
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Whatshot,
                        contentDescription = null,
                        tint = Color(0xFFFF7043),
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Column {
                        Text("Racha", fontSize = 9.sp, color = Color.Gray, lineHeight = 10.sp)
                        Text("12 días", fontSize = 13.sp, fontWeight = FontWeight.Bold, lineHeight = 13.sp)
                    }
                }
            }
        }

        Text(
            "Menú del Lunes 6 de Abril",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 12.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            InfoCard(Modifier.weight(1f), "Calorías hoy", "1,850")
            InfoCard(Modifier.weight(1f), "Proteína", "120g")
            InfoCard(Modifier.weight(1f), "Comidas", "0/3")
        }
    }
}

@Composable
fun InfoCard(modifier: Modifier, label: String, value: String) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(label, fontSize = 11.sp, color = Color.Gray)
            Text(value, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4CAF50))
        }
    }
}

@Composable
fun SectionHeader(title: String, icon: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(icon, fontSize = 20.sp)
        Spacer(Modifier.width(8.dp))
        Text(
            title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Icon(
            Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun FoodCard(item: FoodItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFF5F5F5))
            ) {
                Text(item.emoji, modifier = Modifier.align(Alignment.Center), fontSize = 48.sp)
            }

            Spacer(Modifier.width(16.dp))

            Column {
                Text(item.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(
                    item.description,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    maxLines = 2,
                    lineHeight = 16.sp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Whatshot, null, tint = Color(0xFFFF7043), modifier = Modifier.size(14.dp))
                    Text(" ${item.calories} cal", fontSize = 11.sp, color = Color.Gray)
                    Spacer(Modifier.width(8.dp))
                    Text("P: ${item.protein}g", fontSize = 11.sp, color = Color(0xFFE53935))
                    Spacer(Modifier.width(6.dp))
                    Text("C: ${item.carbs}g", fontSize = 11.sp, color = Color(0xFFFB8C00))
                    Spacer(Modifier.width(6.dp))
                    Text("G: ${item.fat}g", fontSize = 11.sp, color = Color(0xFF8E24AA))
                }

                Spacer(Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("$${item.price}", fontWeight = FontWeight.Bold, color = Color(0xFF4CAF50), fontSize = 18.sp)
                    Surface(
                        color = Color(0xFFE8F5E9),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            item.tag,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                            fontSize = 10.sp,
                            color = Color(0xFF2E7D32),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PlanificaBanner(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Color(0xFF43A047),
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(44.dp),
                shape = CircleShape,
                color = Color.White.copy(alpha = 0.2f)
            ) {
                Icon(
                    Icons.Default.AccessTime,
                    null,
                    tint = Color.White,
                    modifier = Modifier.padding(10.dp)
                )
            }
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text("Planifica tu semana", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text("Ahorra tiempo y dinero", color = Color.White.copy(alpha = 0.9f), fontSize = 13.sp)
            }
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null, tint = Color.White)
        }
    }
}

@Composable
fun BottomNav() {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp,
        modifier = Modifier.height(80.dp)
    ) {
        val selectedColor = Color(0xFF4CAF50)
        val unselectedColor = Color.Gray

        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text("Inicio", fontSize = 11.sp) },
            selected = true,
            onClick = {},
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
            selected = false,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.AutoMirrored.Filled.TrendingUp, null) },
            label = { Text("Progreso", fontSize = 11.sp) },
            selected = false,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.History, null) },
            label = { Text("Historial", fontSize = 11.sp) },
            selected = false,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.Person, null) },
            label = { Text("Perfil", fontSize = 11.sp) },
            selected = false,
            onClick = {}
        )
    }
}

data class FoodItem(
    val emoji: String,
    val name: String,
    val description: String,
    val calories: Int,
    val protein: Int,
    val carbs: Int,
    val fat: Int,
    val price: Double,
    val tag: String
)

val desayunoItems = listOf(
    FoodItem(
        "🥣",
        "Bowl de Açaí Proteico",
        "Bowl energético con açaí orgánico, proteína de alta calidad y frutas frescas",
        420, 25, 48, 12, 8.99, "Alto en proteína"
    ),
    FoodItem(
        "🍳",
        "Omelette de Claras con Vegetales",
        "Omelette ligero cargado de proteína y vegetales frescos",
        280, 32, 12, 8, 7.99, "Bajo en carbohidratos"
    )
)

val comidaItems = listOf(
    FoodItem(
        "🍗",
        "Pollo a la Parrilla con Quinoa",
        "Plato balanceado con proteína magra, granos integrales y vegetales",
        520, 45, 42, 15, 10.99, "Equilibrado"
    ),
    FoodItem(
        "🥗",
        "Bowl Buddha Vegano",
        "Mezcla nutritiva de garbanzos, camote, aguacate and kale con aderezo de tahini",
        450, 18, 55, 20, 9.50, "Vegano"
    )
)

val cenaItems = listOf(
    FoodItem(
        "🐟",
        "Salmón con Vegetales Asados",
        "Cena ligera rica en omega-3 y antioxidantes",
        450, 38, 22, 24, 15.99, "Rico en omega-3"
    ),
    FoodItem(
        "🦃",
        "Pavo con Camote y Ensalada",
        "Cena balanceada con carbohidratos complejos y proteína magra",
        420, 40, 35, 12, 13.99, "Bajo en grasa"
    )
)

val snackItems = listOf(
    FoodItem(
        "🥜",
        "Energy Balls Proteicas",
        "Snack energético perfecto pre o post-workout",
        180, 12, 18, 8, 4.99, "Sin azúcar añadida"
    ),
    FoodItem(
        "🥕",
        "Hummus con Vegetales",
        "Snack nutritivo bajo en calorías",
        150, 8, 15, 7, 5.99, "Vegano"
    )
)

val bebidasItems = listOf(
    FoodItem(
        "🥤",
        "Smoothie Verde Detox",
        "Bebida depurativa cargada de nutrientes",
        120, 4, 25, 1, 6.50, "Natural"
    )
)
