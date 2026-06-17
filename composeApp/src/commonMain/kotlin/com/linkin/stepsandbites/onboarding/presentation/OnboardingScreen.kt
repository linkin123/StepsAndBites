package com.linkin.stepsandbites.onboarding.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linkin.stepsandbites.onboarding.model.OnboardingData
import com.linkin.stepsandbites.onboarding.model.UserGoal

private val VitaGreen = Color(0xFF22A861)
private val VitaGreenLight = Color(0xFFF0FAF5)
private val VitaGreenMid = Color(0xFFD6F2E5)
private val TextPrimary = Color(0xFF1A1A1A)
private val TextSecondary = Color(0xFF777777)
private val CardBorder = Color(0xFFDDDDDD)

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel,
    onFinished: () -> Unit
) {
    val step by viewModel.currentStep.collectAsState()
    val data by viewModel.data.collectAsState()

    AnimatedContent(
        targetState = step,
        transitionSpec = {
            if (targetState > initialState) {
                slideInHorizontally { it } togetherWith slideOutHorizontally { -it }
            } else {
                slideInHorizontally { -it } togetherWith slideOutHorizontally { it }
            }
        }
    ) { currentStep ->
        when (currentStep) {
            0 -> WelcomeStep(onNext = { viewModel.nextStep() })
            1 -> GoalStep(
                data = data,
                onGoalSelected = { viewModel.setGoal(it) },
                onNext = { viewModel.nextStep() },
                onBack = { viewModel.prevStep() }
            )
            2 -> PreferencesStep(
                data = data,
                onToggle = { viewModel.togglePreference(it) },
                onNext = { viewModel.nextStep() },
                onBack = { viewModel.prevStep() }
            )
            3 -> DeliveryStep(
                data = data,
                onAddressChange = { viewModel.setAddress(it) },
                onToggleNotif = { type, value -> viewModel.toggleNotification(type, value) },
                onFinish = { viewModel.completeOnboarding(); onFinished() },
                onBack = { viewModel.prevStep() }
            )
        }
    }
}

// ─── Step 1: Welcome ───────────────────────────────────────────────────────────

@Composable
fun WelcomeStep(onNext: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
    ) {
        // Hero area
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .background(VitaGreenLight),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(VitaGreenMid),
                    contentAlignment = Alignment.Center
                ) {
                    Text("🥗", fontSize = 52.sp)
                }
                Spacer(Modifier.height(16.dp))
                Text(
                    "Come bien,",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )
                Text(
                    "vive mejor",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    "Comida saludable entregada a tu puerta",
                    fontSize = 13.sp,
                    color = Color(0xFF5A7D66)
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        // Feature rows
        FeatureRow(
            emoji = "🔥",
            bgColor = Color(0xFFE8F8EE),
            title = "Macros calculados",
            subtitle = "Cal, proteína, carbs y grasas por platillo"
        )
        FeatureRow(
            emoji = "📅",
            bgColor = Color(0xFFFFF5E6),
            title = "Plan semanal",
            subtitle = "Organiza tus comidas con un clic"
        )
        FeatureRow(
            emoji = "📈",
            bgColor = Color(0xFFEEF1FF),
            title = "Sigue tu progreso",
            subtitle = "Rachas, logros y metas diarias"
        )

        Spacer(Modifier.height(32.dp))

        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            Button(
                onClick = onNext,
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = VitaGreen)
            ) {
                Text("Comenzar", fontSize = 15.sp, fontWeight = FontWeight.Medium, color = Color.White)
            }
            Spacer(Modifier.height(12.dp))
            OutlinedButton(
                onClick = {},
                modifier = Modifier.fillMaxWidth().height(46.dp),
                shape = RoundedCornerShape(14.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, CardBorder)
            ) {
                Text("Ya tengo cuenta", fontSize = 14.sp, color = TextSecondary)
            }
        }

        Spacer(Modifier.height(24.dp))
        OnboardingDots(currentStep = 0)
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun FeatureRow(emoji: String, bgColor: Color, title: String, subtitle: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(bgColor),
            contentAlignment = Alignment.Center
        ) {
            Text(emoji, fontSize = 20.sp)
        }
        Spacer(Modifier.width(12.dp))
        Column {
            Text(title, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
            Text(subtitle, fontSize = 11.sp, color = TextSecondary)
        }
    }
}

// ─── Step 2: Goal ──────────────────────────────────────────────────────────────

@Composable
fun GoalStep(
    data: OnboardingData,
    onGoalSelected: (UserGoal) -> Unit,
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    val progress by animateFloatAsState(targetValue = 0.5f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
            .padding(horizontal = 24.dp)
    ) {
        Spacer(Modifier.height(48.dp))
        StepHeader(label = "PASO 2 DE 4", progress = progress)
        Spacer(Modifier.height(24.dp))
        Text("¿Cuál es tu", fontSize = 26.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
        Text("objetivo?", fontSize = 26.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
        Spacer(Modifier.height(6.dp))
        Text("Personalizamos tus calorías según tu meta", fontSize = 13.sp, color = TextSecondary)
        Spacer(Modifier.height(24.dp))

        // 2x2 grid
        val goals = UserGoal.entries
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            GoalCard(goal = goals[0], isSelected = data.goal == goals[0], onSelect = { onGoalSelected(goals[0]) }, modifier = Modifier.weight(1f))
            GoalCard(goal = goals[1], isSelected = data.goal == goals[1], onSelect = { onGoalSelected(goals[1]) }, modifier = Modifier.weight(1f))
        }
        Spacer(Modifier.height(12.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            GoalCard(goal = goals[2], isSelected = data.goal == goals[2], onSelect = { onGoalSelected(goals[2]) }, modifier = Modifier.weight(1f))
            GoalCard(goal = goals[3], isSelected = data.goal == goals[3], onSelect = { onGoalSelected(goals[3]) }, modifier = Modifier.weight(1f))
        }

        Spacer(Modifier.height(20.dp))

        // Calorie box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF5F5F5))
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Column {
                Text("Meta de calorías sugerida", fontSize = 12.sp, color = TextSecondary)
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        "${data.dailyCalories}",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary
                    )
                    Spacer(Modifier.width(6.dp))
                    Text("kcal / día", fontSize = 13.sp, color = TextSecondary)
                }
            }
        }

        Spacer(Modifier.height(24.dp))
        Button(
            onClick = onNext,
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = VitaGreen)
        ) {
            Text("Continuar", fontSize = 15.sp, fontWeight = FontWeight.Medium, color = Color.White)
        }
        Spacer(Modifier.height(16.dp))
        OnboardingDots(currentStep = 1)
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun GoalCard(goal: UserGoal, isSelected: Boolean, onSelect: () -> Unit, modifier: Modifier) {
    val borderColor by animateColorAsState(if (isSelected) VitaGreen else CardBorder)
    val bgColor by animateColorAsState(if (isSelected) VitaGreenLight else Color.White)

    Box(
        modifier = modifier
            .height(110.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(bgColor)
            .border(if (isSelected) 2.dp else 1.dp, borderColor, RoundedCornerShape(14.dp))
            .clickable { onSelect() }
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(goal.emoji, fontSize = 30.sp)
            Spacer(Modifier.height(6.dp))
            Text(
                goal.label,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary
            )
            Text(
                goal.description,
                fontSize = 11.sp,
                color = if (isSelected) VitaGreen else TextSecondary
            )
        }
    }
}

// ─── Step 3: Preferences ───────────────────────────────────────────────────────

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PreferencesStep(
    data: OnboardingData,
    onToggle: (String) -> Unit,
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    val progress by animateFloatAsState(targetValue = 0.75f)

    val allPreferences = listOf(
        "Sin gluten", "Vegano", "Alto en proteína", "Vegetariano",
        "Keto", "Sin lácteos", "Bajo en sodio", "Probióticos",
        "Sin azúcar", "Fibra alta"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
            .padding(horizontal = 24.dp)
    ) {
        Spacer(Modifier.height(48.dp))
        StepHeader(label = "PASO 3 DE 4", progress = progress)
        Spacer(Modifier.height(24.dp))
        Text("Preferencias", fontSize = 26.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
        Text("alimenticias", fontSize = 26.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
        Spacer(Modifier.height(6.dp))
        Text("Filtramos el menú según lo que necesitas", fontSize = 13.sp, color = TextSecondary)
        Spacer(Modifier.height(24.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            allPreferences.forEach { pref ->
                val isSelected = data.dietaryPreferences.contains(pref)
                PreferenceChip(label = pref, isSelected = isSelected, onClick = { onToggle(pref) })
            }
        }

        Spacer(Modifier.height(20.dp))

        val count = data.dietaryPreferences.size
        if (count > 0) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(VitaGreenLight)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Text(
                    "✓  $count ${if (count == 1) "preferencia seleccionada" else "preferencias seleccionadas"} — menú filtrado por ti",
                    fontSize = 12.sp,
                    color = VitaGreen,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(Modifier.height(16.dp))
        }

        HorizontalDivider(color = Color(0xFFEEEEEE))
        Spacer(Modifier.height(12.dp))
        Text(
            "Puedes cambiar estas preferencias en cualquier momento desde tu perfil.",
            fontSize = 12.sp,
            color = TextSecondary
        )

        Spacer(Modifier.height(32.dp))
        Button(
            onClick = onNext,
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = VitaGreen)
        ) {
            Text("Continuar", fontSize = 15.sp, fontWeight = FontWeight.Medium, color = Color.White)
        }
        Spacer(Modifier.height(16.dp))
        OnboardingDots(currentStep = 2)
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun PreferenceChip(label: String, isSelected: Boolean, onClick: () -> Unit) {
    val bgColor by animateColorAsState(if (isSelected) VitaGreenLight else Color.White)
    val borderColor by animateColorAsState(if (isSelected) VitaGreen else CardBorder)
    val textColor by animateColorAsState(if (isSelected) VitaGreen else TextSecondary)

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(bgColor)
            .border(if (isSelected) 1.5.dp else 1.dp, borderColor, RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 8.dp)
    ) {
        Text(
            label,
            fontSize = 12.sp,
            color = textColor,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}

// ─── Step 4: Delivery ──────────────────────────────────────────────────────────

@Composable
fun DeliveryStep(
    data: OnboardingData,
    onAddressChange: (String) -> Unit,
    onToggleNotif: (String, Boolean) -> Unit,
    onFinish: () -> Unit,
    onBack: () -> Unit
) {
    val progress by animateFloatAsState(targetValue = 1f)
    var showSuggestions by remember { mutableStateOf(false) }

    val suggestions = listOf(
        "Av. Reforma 123, CDMX",
        "Av. Reforma 456, Col. Juárez"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
            .padding(horizontal = 24.dp)
    ) {
        Spacer(Modifier.height(48.dp))
        StepHeader(label = "PASO 4 DE 4", progress = progress)
        Spacer(Modifier.height(24.dp))
        Text("¿A dónde", fontSize = 26.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
        Text("enviamos?", fontSize = 26.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
        Spacer(Modifier.height(6.dp))
        Text("Tu dirección y preferencias de notificación", fontSize = 13.sp, color = TextSecondary)
        Spacer(Modifier.height(24.dp))

        OutlinedTextField(
            value = data.address,
            onValueChange = {
                onAddressChange(it)
                showSuggestions = it.isNotEmpty()
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("📍  Tu dirección de entrega", color = TextSecondary) },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = VitaGreen,
                unfocusedBorderColor = CardBorder
            ),
            singleLine = true
        )

        if (showSuggestions) {
            Spacer(Modifier.height(4.dp))
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFFF5F5F5)
            ) {
                Column {
                    suggestions.forEachIndexed { index, suggestion ->
                        Text(
                            "📍  $suggestion",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onAddressChange(suggestion)
                                    showSuggestions = false
                                }
                                .padding(horizontal = 16.dp, vertical = 14.dp),
                            fontSize = 12.sp,
                            color = TextPrimary
                        )
                        if (index < suggestions.lastIndex) {
                            HorizontalDivider(color = Color(0xFFE0E0E0))
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))
        Text("Notificaciones", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
        Spacer(Modifier.height(8.dp))

        NotificationRow(
            title = "Recordatorio de comida",
            description = "Te avisamos a la hora de comer",
            checked = data.notifyMealReminder,
            onCheckedChange = { onToggleNotif("meal", it) }
        )
        HorizontalDivider(color = Color(0xFFEEEEEE))
        NotificationRow(
            title = "Estado del pedido",
            description = "Preparando, en camino, entregado",
            checked = data.notifyOrderStatus,
            onCheckedChange = { onToggleNotif("order", it) }
        )
        HorizontalDivider(color = Color(0xFFEEEEEE))
        NotificationRow(
            title = "Ofertas especiales",
            description = "Platillos nuevos y descuentos",
            checked = data.notifyOffers,
            onCheckedChange = { onToggleNotif("offers", it) }
        )

        Spacer(Modifier.height(32.dp))
        Button(
            onClick = onFinish,
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = VitaGreen)
        ) {
            Text("¡Empezar!", fontSize = 15.sp, fontWeight = FontWeight.Medium, color = Color.White)
        }
        Spacer(Modifier.height(16.dp))
        OnboardingDots(currentStep = 3)
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun NotificationRow(
    title: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
            Text(description, fontSize = 11.sp, color = TextSecondary)
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = VitaGreen,
                uncheckedTrackColor = CardBorder
            )
        )
    }
}

// ─── Shared composables ────────────────────────────────────────────────────────

@Composable
fun StepHeader(label: String, progress: Float) {
    Text(label, fontSize = 11.sp, fontWeight = FontWeight.Medium, color = VitaGreen)
    Spacer(Modifier.height(6.dp))
    LinearProgressIndicator(
        progress = { progress },
        modifier = Modifier.fillMaxWidth().height(4.dp).clip(RoundedCornerShape(2.dp)),
        color = VitaGreen,
        trackColor = Color(0xFFE8F8EE)
    )
}

@Composable
fun OnboardingDots(currentStep: Int, totalSteps: Int = 4) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(totalSteps) { index ->
            val isActive = index == currentStep
            val width: Dp by animateDpAsState(if (isActive) 22.dp else 8.dp)
            Box(
                modifier = Modifier
                    .padding(horizontal = 3.dp)
                    .size(width = width, height = 8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(if (isActive) VitaGreen else Color(0xFFCCE8D8))
            )
        }
    }
}
