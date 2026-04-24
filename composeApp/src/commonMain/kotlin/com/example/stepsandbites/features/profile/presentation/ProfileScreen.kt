package com.example.stepsandbites.features.profile.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stepsandbites.AppBottomNavigation
import com.example.stepsandbites.TopBar
import com.example.stepsandbites.features.profile.model.UserGoal

@Composable
fun ProfileScreen(onNavigate: (String) -> Unit, viewModel: ProfileViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { TopBar() },
        bottomBar = { AppBottomNavigation(currentRoute = "perfil", onNavigate = onNavigate) }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues).background(Color(0xFFF9F9F9))) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Address Section
                item {
                    AddressCard(uiState.address)
                }

                // Goal Section
                item {
                    GoalSection(
                        selectedGoal = uiState.goal,
                        calories = uiState.dailyCalories,
                        onGoalSelected = { viewModel.onEvent(ProfileEvent.UpdateGoal(it)) },
                        onCaloriesChanged = { viewModel.onEvent(ProfileEvent.UpdateCalories(it)) }
                    )
                }

                // Preferences Section
                item {
                    PreferencesSection(
                        selectedPreferences = uiState.preferences,
                        onTogglePreference = { viewModel.onEvent(ProfileEvent.TogglePreference(it)) }
                    )
                }

                // Notifications Section
                item {
                    NotificationsSection(
                        mealReminders = uiState.mealReminders,
                        offers = uiState.offersAndPromos,
                        tips = uiState.nutritionalTips,
                        onMealRemindersChanged = { viewModel.onEvent(ProfileEvent.UpdateMealReminders(it)) },
                        onOffersChanged = { viewModel.onEvent(ProfileEvent.UpdateOffers(it)) },
                        onTipsChanged = { viewModel.onEvent(ProfileEvent.UpdateTips(it)) }
                    )
                }

                item {
                    Button(
                        onClick = { /* Save changes */ },
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5ABF86))
                    ) {
                        Text("Guardar Cambios", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
                
                item { Spacer(modifier = Modifier.height(16.dp)) }
            }
        }
    }
}

@Composable
fun AddressCard(address: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(20.dp))
            Spacer(Modifier.width(12.dp))
            Text(address, color = Color.Gray, fontSize = 14.sp)
        }
    }
}

@Composable
fun GoalSection(
    selectedGoal: UserGoal,
    calories: String,
    onGoalSelected: (UserGoal) -> Unit,
    onCaloriesChanged: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.TrackChanges, null, tint = Color(0xFF4CAF50), modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(8.dp))
                Text("Mi Objetivo", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            
            Spacer(Modifier.height(16.dp))
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                GoalItem(Modifier.weight(1f), "Bajar grasa", "📉", selectedGoal == UserGoal.LOSE_FAT) {
                    onGoalSelected(UserGoal.LOSE_FAT)
                }
                GoalItem(Modifier.weight(1f), "Mantener peso", "⚖️", selectedGoal == UserGoal.MAINTAIN_WEIGHT) {
                    onGoalSelected(UserGoal.MAINTAIN_WEIGHT)
                }
                GoalItem(Modifier.weight(1f), "Ganar músculo", "💪", selectedGoal == UserGoal.GAIN_MUSCLE) {
                    onGoalSelected(UserGoal.GAIN_MUSCLE)
                }
            }
            
            Spacer(Modifier.height(24.dp))
            
            Text("Meta de Calorías Diarias", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = calories,
                onValueChange = onCaloriesChanged,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFF9F9F9),
                    focusedContainerColor = Color(0xFFF9F9F9),
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color(0xFF4CAF50)
                )
            )
        }
    }
}

@Composable
fun GoalItem(modifier: Modifier, label: String, emoji: String, isSelected: Boolean, onClick: () -> Unit) {
    val borderColor by animateColorAsState(if (isSelected) Color(0xFF4CAF50) else Color(0xFFF0F0F0))
    val backgroundColor = if (isSelected) Color(0xFFE8F5E9).copy(alpha = 0.3f) else Color.White

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .border(1.dp, borderColor, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(emoji, fontSize = 24.sp)
        Spacer(Modifier.height(8.dp))
        Text(label, fontSize = 12.sp, fontWeight = FontWeight.Medium, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PreferencesSection(selectedPreferences: Set<String>, onTogglePreference: (String) -> Unit) {
    val options = listOf("Sin gluten", "Vegano", "Vegetariano", "Sin lácteos", "Bajo en sodio", "Keto")
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Info, null, tint = Color(0xFF4CAF50), modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(8.dp))
                Text("Preferencias Alimenticias", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            
            Spacer(Modifier.height(16.dp))
            
            androidx.compose.foundation.layout.FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                options.forEach { option ->
                    val isSelected = selectedPreferences.contains(option)
                    FilterChip(
                        selected = isSelected,
                        onClick = { onTogglePreference(option) },
                        label = { Text(option) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Color(0xFF5ABF86),
                            selectedLabelColor = Color.White,
                            containerColor = Color.White,
                            labelColor = Color.Black
                        ),
                        border = FilterChipDefaults.filterChipBorder(
                            enabled = true,
                            selected = isSelected,
                            borderColor = Color(0xFFF0F0F0),
                            selectedBorderColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun NotificationsSection(
    mealReminders: Boolean,
    offers: Boolean,
    tips: Boolean,
    onMealRemindersChanged: (Boolean) -> Unit,
    onOffersChanged: (Boolean) -> Unit,
    onTipsChanged: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Notifications, null, tint = Color(0xFF4CAF50), modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(8.dp))
                Text("Notificaciones", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            
            Spacer(Modifier.height(16.dp))
            
            NotificationToggle(
                title = "Recordatorios de comida",
                description = "Te avisamos cuando sea hora de comer",
                checked = mealReminders,
                onCheckedChange = onMealRemindersChanged
            )
            
            NotificationToggle(
                title = "Ofertas y promociones",
                description = "Recibe descuentos exclusivos",
                checked = offers,
                onCheckedChange = onOffersChanged
            )
            
            NotificationToggle(
                title = "Consejos nutricionales",
                description = "Tips para mejorar tu alimentación",
                checked = tips,
                onCheckedChange = onTipsChanged
            )
        }
    }
}

@Composable
fun NotificationToggle(title: String, description: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .toggleable(
                value = checked,
                onValueChange = onCheckedChange,
                role = Role.Checkbox
            )
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(description, color = Color.Gray, fontSize = 12.sp)
        }
        Checkbox(
            checked = checked,
            onCheckedChange = null, // Handled by toggleable
            colors = CheckboxDefaults.colors(checkedColor = Color(0xFF4C8DF5))
        )
    }
}
