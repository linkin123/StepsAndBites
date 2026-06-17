package com.linkin.stepsandbites.features.progress.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Whatshot
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
import com.linkin.stepsandbites.features.progress.model.Achievement
import com.linkin.stepsandbites.features.progress.model.DailyProgress

@Composable
fun ProgressScreen(onNavigate: (String) -> Unit, viewModel: ProgressViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { TopBar() },
        bottomBar = { AppBottomNavigation(currentRoute = "progreso", onNavigate = onNavigate) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF9F9F9)),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Text(
                    "Tu Progreso",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                StreakCard(
                    currentStreak = uiState.currentStreak,
                    personalRecord = uiState.personalRecord,
                    totalMeals = uiState.totalMeals
                )
            }

            item {
                WeeklyProgressSection(
                    days = uiState.weeklyProgress,
                    completionPercent = uiState.weeklyCompletionPercent,
                    selectedIndex = uiState.selectedDayIndex,
                    onDaySelected = { viewModel.onEvent(ProgressEvent.SelectDay(it)) }
                )
            }

            item {
                AchievementsSection(achievements = uiState.achievements)
            }

            item {
                StatisticsSection()
            }
            
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun StreakCard(currentStreak: Int, personalRecord: Int, totalMeals: Int) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF66BB6A))
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    shape = CircleShape,
                    color = Color.White.copy(alpha = 0.2f),
                    modifier = Modifier.size(60.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            Icons.Default.Whatshot,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
                Spacer(Modifier.width(16.dp))
                Column {
                    Text("Racha actual", color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
                    Text("$currentStreak", color = Color.White, fontSize = 48.sp, fontWeight = FontWeight.Bold)
                    Text("días consecutivos 🔥", color = Color.White.copy(alpha = 0.9f), fontSize = 14.sp)
                }
            }
            Spacer(Modifier.height(24.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text("Récord personal", color = Color.White.copy(alpha = 0.8f), fontSize = 13.sp)
                    Text("$personalRecord días", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("Comidas totales", color = Color.White.copy(alpha = 0.8f), fontSize = 13.sp)
                    Text("$totalMeals", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
            }
        }
    }
}

@Composable
fun WeeklyProgressSection(
    days: List<DailyProgress>,
    completionPercent: Float,
    selectedIndex: Int,
    onDaySelected: (Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Progreso Semanal", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Column(horizontalAlignment = Alignment.End) {
                    Text("${(completionPercent * 100).toInt()}%", color = Color(0xFF4CAF50), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text("Completado", color = Color.Gray, fontSize = 11.sp)
                }
            }
            
            Spacer(Modifier.height(16.dp))
            
            LinearProgressIndicator(
                progress = { completionPercent },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(CircleShape),
                color = Color(0xFF4CAF50),
                trackColor = Color(0xFFE8F5E9)
            )
            
            Spacer(Modifier.height(24.dp))
            
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                itemsIndexed(days) { index, day ->
                    DayItem(
                        day = day,
                        isSelected = index == selectedIndex,
                        onClick = { onDaySelected(index) }
                    )
                }
            }
        }
    }
}

@Composable
fun DayItem(day: DailyProgress, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor by animateColorAsState(
        if (isSelected) Color(0xFF4CAF50) else Color(0xFFE8F5E9)
    )
    val contentColor by animateColorAsState(
        if (isSelected) Color.White else Color(0xFF2E7D32)
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Surface(
            modifier = Modifier.size(40.dp),
            shape = CircleShape,
            color = backgroundColor
        ) {
            Box(contentAlignment = Alignment.Center) {
                if (day.isCompleted) {
                    Icon(Icons.Default.Check, contentDescription = null, tint = contentColor, modifier = Modifier.size(20.dp))
                } else {
                    Box(modifier = Modifier.size(20.dp).background(Color.Transparent))
                }
            }
        }
        Spacer(Modifier.height(8.dp))
        Text(day.dayName, fontSize = 12.sp, color = if (isSelected) Color.Black else Color.Gray, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal)
    }
}

@Composable
fun AchievementsSection(achievements: List<Achievement>) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("🏆", fontSize = 20.sp)
            Spacer(Modifier.width(8.dp))
            Text("Logros Desbloqueados", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }
        Spacer(Modifier.height(16.dp))
        achievements.forEach { achievement ->
            AchievementCard(achievement)
            Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
fun AchievementCard(achievement: Achievement) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(achievement.color))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(44.dp),
                shape = CircleShape,
                color = Color.White.copy(alpha = 0.5f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(achievement.icon, fontSize = 20.sp)
                }
            }
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(achievement.title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(achievement.description, fontSize = 13.sp, color = Color.Gray)
            }
            Text(achievement.badge, fontSize = 24.sp)
        }
    }
}

@Composable
fun StatisticsSection() {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("📈", fontSize = 20.sp)
            Spacer(Modifier.width(8.dp))
            Text("Estadísticas", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }
        Spacer(Modifier.height(16.dp))
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                StatCard(Modifier.weight(1f), "92%", "Tasa de\ncumplimiento")
                StatCard(Modifier.weight(1f), "1,850", "Cal promedio/día")
            }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                StatCard(Modifier.weight(1f), "120g", "Proteína promedio")
                StatCard(Modifier.weight(1f), "22", "Días este mes")
            }
        }
    }
}

@Composable
fun StatCard(modifier: Modifier, value: String, label: String) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFE8F5E9)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(value, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4CAF50))
            Text(label, fontSize = 12.sp, color = Color.Gray, modifier = Modifier.padding(top = 4.dp), lineHeight = 14.sp)
        }
    }
}
