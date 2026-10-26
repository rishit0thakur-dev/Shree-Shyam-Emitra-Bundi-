package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.EmitraCategory
import com.example.model.EmitraService
import com.example.ui.components.Card3D
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmitraScreen(
    services: List<EmitraService>,
    isHindi: Boolean,
    onSelectServiceForDetails: (EmitraService) -> Unit,
    onSelectServiceForApply: (EmitraService) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(EmitraCategory.ALL) }

    val filteredServices = remember(services, searchQuery, selectedCategory) {
        services.filter { item ->
            val matchesCategory = selectedCategory == EmitraCategory.ALL || item.category == selectedCategory
            val matchesQuery = searchQuery.isBlank() ||
                    item.titleHi.contains(searchQuery, ignoreCase = true) ||
                    item.titleEn.contains(searchQuery, ignoreCase = true) ||
                    item.department.contains(searchQuery, ignoreCase = true) ||
                    item.eligibility.contains(searchQuery, ignoreCase = true)
            matchesCategory && matchesQuery
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Hero Banner
        item {
            EmitraHeroBanner(isHindi = isHindi)
        }

        // Search Bar & Filter Chips
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = {
                        Text(
                            text = if (isHindi) "फॉर्म खोजें (उदा. रीट, पुलिस, जन आधार, जाति...)" else "Search form or vacancy...",
                            fontSize = 13.sp
                        )
                    },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = null, tint = ShyamBluePrimary)
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(Icons.Default.Clear, contentDescription = "Clear")
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        unfocusedBorderColor = Color(0xFFE2E8F0)
                    )
                )

                // Category Filter Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    EmitraCategory.values().forEach { cat ->
                        val isSelected = selectedCategory == cat
                        FilterChip(
                            selected = isSelected,
                            onClick = { selectedCategory = cat },
                            label = {
                                Text(
                                    text = if (isHindi) cat.displayNameHi else cat.displayNameEn,
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = ShyamBluePrimary,
                                selectedLabelColor = Color.White
                            )
                        )
                    }
                }
            }
        }

        // Section header
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (selectedCategory == EmitraCategory.GOVT_VACANCY) "नवीनतम सरकारी भर्तियां"
                    else if (selectedCategory == EmitraCategory.CITIZEN_SERVICE) "ई-मित्र नागरिक सेवाएं"
                    else "सभी फॉर्म व सेवाएं (${filteredServices.size})",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = ShyamBlueDark
                )
                Text(
                    text = "दस्तावेज व फीस सहित",
                    fontSize = 11.sp,
                    color = ShyamGoldDark,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Form Cards
        items(filteredServices, key = { it.id }) { service ->
            ServiceCard(
                service = service,
                isHindi = isHindi,
                onViewDetails = { onSelectServiceForDetails(service) },
                onApply = { onSelectServiceForApply(service) },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
private fun EmitraHeroBanner(isHindi: Boolean) {
    Card3D(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(22.dp),
        elevation = 6.dp,
        borderBrush = Brush.linearGradient(listOf(ShyamGold, ShyamBlueLight))
    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            ShyamBlueDark,
                            ShyamBluePrimary
                        )
                    )
                )
                .padding(18.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = ShyamGold
                    ) {
                        Text(
                            text = "★ अधिकृत ई-मित्र केंद्र बूँदी",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            color = ShyamBlueDark,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }

                    Text(
                        text = "100% सटीक फॉर्म",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = if (isHindi) "सरकारी फॉर्म भरवाएं घर बैठे\nया दुकान पर आकर" else "Apply Govt Forms & E-Mitra Services Easily",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White,
                    lineHeight = 24.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "आवश्यक दस्तावेज चेक करें • ऑनलाइन फीस जमा करें • तुरंत रसीद प्राप्त करें",
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.85f)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Stats row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BannerStat(label = "सरकारी भर्तियां", value = "REET, Police, Patwar")
                    BannerStat(label = "नागरिक सेवाएं", value = "जन आधार, जाति, मूल निवास")
                }
            }
        }
    }
}

@Composable
private fun BannerStat(label: String, value: String) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = Color.White.copy(alpha = 0.12f)
    ) {
        Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)) {
            Text(text = label, fontSize = 10.sp, color = ShyamGoldLight, fontWeight = FontWeight.SemiBold)
            Text(text = value, fontSize = 11.sp, color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun ServiceCard(
    service: EmitraService,
    isHindi: Boolean,
    onViewDetails: () -> Unit,
    onApply: () -> Unit,
    modifier: Modifier = Modifier
) {
    val totalFee = service.govtFee + service.emitraFee

    Card3D(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        elevation = 3.dp,
        backgroundColor = Color.White,
        onClick = onViewDetails
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            // Category & Last date tag
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = if (service.category == EmitraCategory.GOVT_VACANCY) ShyamBlueContainer else ShyamGoldLight
                ) {
                    Text(
                        text = if (isHindi) service.category.displayNameHi else service.category.displayNameEn,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (service.category == EmitraCategory.GOVT_VACANCY) ShyamBluePrimary else ShyamGoldDark,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Outlined.Event,
                        contentDescription = null,
                        tint = CrimsonAlert,
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "अंतिम तिथि: ${service.lastDate}",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = CrimsonAlert
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Title
            Text(
                text = if (isHindi) service.titleHi else service.titleEn,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = ShyamBlueDark
            )

            Text(
                text = service.department,
                fontSize = 11.sp,
                color = Color(0xFF64748B),
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Eligibility snippet
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFFF8FAFC),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Outlined.School, contentDescription = null, tint = ShyamBluePrimary, modifier = Modifier.size(15.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = service.eligibility,
                        fontSize = 11.sp,
                        color = Color(0xFF334155),
                        maxLines = 1
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Fee & Documents info
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Documents count badge
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Outlined.FolderCopy, contentDescription = null, tint = ShyamBluePrimary, modifier = Modifier.size(15.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${service.requiredDocuments.size} जरूरी दस्तावेज",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = ShyamBluePrimary
                    )
                }

                // Total Fee
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = "कुल फीस: ",
                        fontSize = 11.sp,
                        color = Color(0xFF64748B)
                    )
                    Text(
                        text = "₹$totalFee",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Black,
                        color = EmeraldSuccessDark
                    )
                    Text(
                        text = " (पोर्टल+ई-मित्र)",
                        fontSize = 9.sp,
                        color = Color(0xFF94A3B8)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Action buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = onViewDetails,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Info, contentDescription = null, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("दस्तावेज देखें", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                }

                Button(
                    onClick = onApply,
                    colors = ButtonDefaults.buttonColors(containerColor = ShyamBluePrimary),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("फॉर्म भरवाएं", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
