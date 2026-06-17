package com.linkin.stepsandbites.features.home.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linkin.stepsandbites.AppBottomNavigation
import com.linkin.stepsandbites.TopBar
import com.linkin.stepsandbites.features.home.model.HomeFoodItem

@Composable
fun HomeScreen(onNavigateToPlan: (String) -> Unit, viewModel: HomeViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { TopBar() },
        bottomBar = { AppBottomNavigation(currentRoute = "inicio", onNavigate = onNavigateToPlan) }
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
                item { HomeHeaderSection(uiState) }

                item {
                    HomeSectionHeader(title = "Desayuno", icon = "🌅")
                }

                items(uiState.breakfastItems) { item ->
                    HomeFoodCard(item)
                }

                item {
                    HomeSectionHeader(title = "Comida", icon = "🍽️")
                }

                items(uiState.lunchItems) { item ->
                    HomeFoodCard(item)
                }

                item {
                    HomeSectionHeader(title = "Cena", icon = "🌙")
                }

                items(uiState.dinnerItems) { item ->
                    HomeFoodCard(item)
                }

                item { Spacer(modifier = Modifier.height(100.dp)) }
            }

            HomePlanificaBanner(
                onClick = { onNavigateToPlan("WeeklyPlan") },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun HomeHeaderSection(uiState: HomeUiState) {
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
                        Text("${uiState.streakDays} días", fontSize = 13.sp, fontWeight = FontWeight.Bold, lineHeight = 13.sp)
                    }
                }
            }
        }

        Text(
            "Menú del ${uiState.dateText}",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 12.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            HomeInfoCard(Modifier.weight(1f), "Calorías hoy", "${uiState.dailyCalories}")
            HomeInfoCard(Modifier.weight(1f), "Proteína", "${uiState.dailyProtein}g")
            HomeInfoCard(Modifier.weight(1f), "Comidas", uiState.dailyMeals)
        }
    }
}

@Composable
fun HomeInfoCard(modifier: Modifier, label: String, value: String) {
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
fun HomeSectionHeader(title: String, icon: String) {
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
fun HomeFoodCard(item: HomeFoodItem) {
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
fun HomePlanificaBanner(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth().clickable { onClick() },
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
