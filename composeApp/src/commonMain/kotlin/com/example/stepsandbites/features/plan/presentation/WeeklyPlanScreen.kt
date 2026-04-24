package com.example.stepsandbites.features.plan.presentation

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stepsandbites.AppBottomNavigation
import com.example.stepsandbites.features.plan.model.DayInfo
import com.example.stepsandbites.features.plan.model.Dish
import com.example.stepsandbites.features.plan.model.MealType
import com.example.stepsandbites.features.plan.model.NutritionSummary
import kotlinx.coroutines.delay

@Composable
fun WeeklyPlanScreen(
    onNavigateToHome: (String) -> Unit,
    viewModel: PlanViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = { PlanHeader() },
            bottomBar = { AppBottomNavigation(currentRoute = "WeeklyPlan", onNavigate = onNavigateToHome) }
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color(0xFFF9F9F9)),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                item {
                    DaySelector(
                        days = uiState.days,
                        selectedDayIndex = uiState.selectedDayIndex,
                        onDaySelected = { viewModel.onEvent(PlanEvent.SelectDay(it)) }
                    )
                }

                item {
                    if (uiState.days.isNotEmpty()) {
                        val day = uiState.days[uiState.selectedDayIndex]
                        Text(
                            text = "${day.name}, ${day.number}-13 de Abril",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                }

                item {
                    NutritionSummaryCard(uiState.nutritionSummary)
                }

                MealType.entries.forEach { mealType ->
                    item {
                        MealSection(
                            mealType = mealType,
                            dishes = uiState.allDishes.filter { it.mealType == mealType },
                            selectedDishIds = uiState.selectedDishIds,
                            onToggleDish = { viewModel.onEvent(PlanEvent.ToggleDish(it)) }
                        )
                    }
                }
                
                item { Spacer(modifier = Modifier.height(20.dp)) }
            }
        }

        AnimatedVisibility(
            visible = uiState.message != null,
            enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut(),
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            uiState.message?.let { msg ->
                TopBanner(message = msg, onDismiss = { viewModel.onEvent(PlanEvent.ClearMessage) })
            }
        }
    }
}

@Composable
fun PlanHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE8F5E9))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column {
                Text(
                    "VitaFood",
                    color = Color(0xFF4CAF50),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    "Nutrición Inteligente",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
            Icon(
                Icons.Outlined.ShoppingCart,
                contentDescription = "Cart",
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Plan Semanal",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            "Organiza tus comidas para toda la semana",
            color = Color.Gray,
            fontSize = 14.sp
        )
    }
}

@Composable
fun DaySelector(
    days: List<DayInfo>,
    selectedDayIndex: Int,
    onDaySelected: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(days.size) { index ->
            val isSelected = index == selectedDayIndex
            val backgroundColor by animateColorAsState(
                if (isSelected) Color(0xFF4CAF50) else Color(0xFFE8F5E9)
            )
            val textColor = if (isSelected) Color.White else Color.Black

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(backgroundColor)
                    .clickable { onDaySelected(index) }
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(days[index].name, color = textColor, fontSize = 12.sp)
                Text(
                    days[index].number.toString(),
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun NutritionSummaryCard(summary: NutritionSummary) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9).copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SummaryItem(value = "${summary.totalCalories}", label = "Cal", valueColor = Color(0xFFE65100))
            SummaryItem(value = "${summary.totalProtein}g", label = "Prot", valueColor = Color(0xFFD32F2F))
            SummaryItem(value = "${summary.totalCarbs}g", label = "Carbs", valueColor = Color(0xFFF57C00))
            SummaryItem(value = "${summary.totalFat}g", label = "Grasas", valueColor = Color(0xFF7B1FA2))
            val priceFormatted = (summary.totalPrice * 100).toInt() / 100.0
            SummaryItem(value = "$$priceFormatted", label = "Total", valueColor = Color(0xFF2E7D32))
        }
    }
}

@Composable
fun SummaryItem(value: String, label: String, valueColor: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontWeight = FontWeight.Bold, color = valueColor, fontSize = 16.sp)
        Text(label, fontSize = 11.sp, color = Color.Gray)
    }
}

@Composable
fun MealSection(
    mealType: MealType,
    dishes: List<Dish>,
    selectedDishIds: Set<Int>,
    onToggleDish: (Dish) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(mealType.icon, fontSize = 20.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(mealType.displayName, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            dishes.forEach { dish ->
                DishItem(
                    dish = dish,
                    isSelected = selectedDishIds.contains(dish.id),
                    onToggle = { onToggleDish(dish) }
                )
                if (dish != dishes.last()) {
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
fun DishItem(
    dish: Dish,
    isSelected: Boolean,
    onToggle: () -> Unit
) {
    val backgroundColor by animateColorAsState(
        if (isSelected) Color(0xFFE8F5E9) else Color.Transparent
    )
    val borderColor = if (isSelected) Color(0xFF4CAF50).copy(alpha = 0.3f) else Color.Transparent

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .border(1.dp, borderColor, RoundedCornerShape(16.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF5F5F5)),
            contentAlignment = Alignment.Center
        ) {
            Text(dish.emoji, fontSize = 32.sp)
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(dish.name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text("${dish.calories} cal • $${dish.price}", fontSize = 12.sp, color = Color.Gray)
            if (isSelected) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = null,
                        tint = Color(0xFF2E7D32),
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        " Seleccionado",
                        color = Color(0xFF2E7D32),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
        IconButton(onClick = onToggle) {
            Icon(
                imageVector = if (isSelected) Icons.Default.Close else Icons.Default.Add,
                contentDescription = if (isSelected) "Remove" else "Add",
                tint = if (isSelected) Color.Red else Color(0xFF4CAF50)
            )
        }
    }
}

@Composable
fun TopBanner(message: String, onDismiss: () -> Unit) {
    LaunchedEffect(message) {
        delay(2500)
        onDismiss()
    }
    Surface(
        modifier = Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        color = Color(0xFF323232),
        shape = RoundedCornerShape(12.dp),
        shadowElevation = 6.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Info, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = message,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
