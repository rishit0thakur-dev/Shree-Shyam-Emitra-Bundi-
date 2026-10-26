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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.MobileAccessory
import com.example.model.RepairService
import com.example.model.ShopInfo
import com.example.ui.components.Card3D
import com.example.ui.theme.*
import com.example.util.IntentUtils

@Composable
fun MobileRepairScreen(
    repairServices: List<RepairService>,
    accessories: List<MobileAccessory>,
    isHindi: Boolean,
    onOpenBookRepair: (String?) -> Unit
) {
    val context = LocalContext.current
    var selectedCategoryFilter by remember { mutableStateOf("सभी (All)") }
    val categories = listOf("सभी (All)", "रिपेयरिंग सेवाएं", "मोबाइल एक्सेसरीज")

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Hero Banner
        item {
            RepairHeroBanner(
                onBookClick = { onOpenBookRepair(null) },
                onCallClick = { IntentUtils.dialPhoneNumber(context, ShopInfo.PRIMARY_PHONE) }
            )
        }

        // Category Filter
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                categories.forEach { cat ->
                    val isSelected = selectedCategoryFilter == cat
                    FilterChip(
                        selected = isSelected,
                        onClick = { selectedCategoryFilter = cat },
                        label = { Text(cat, fontSize = 12.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = ShyamBluePrimary,
                            selectedLabelColor = Color.White
                        )
                    )
                }
            }
        }

        // Repair Services Section
        if (selectedCategoryFilter == "सभी (All)" || selectedCategoryFilter == "रिपेयरिंग सेवाएं") {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "मोबाइल रिपेयरिंग सेवाएं (Repair Services)",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = ShyamBlueDark
                    )
                    Text(
                        text = "हाथो-हाथ तैयार",
                        fontSize = 11.sp,
                        color = EmeraldSuccessDark,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            items(repairServices, key = { it.id }) { service ->
                RepairServiceCard(
                    service = service,
                    onBook = { onOpenBookRepair(service.titleHi) },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }

        // Accessories Section
        if (selectedCategoryFilter == "सभी (All)" || selectedCategoryFilter == "मोबाइल एक्सेसरीज") {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "ओरिजिनल मोबाइल एक्सेसरीज (Accessories)",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = ShyamBlueDark
                    )
                    Text(
                        text = "दुकान से प्राप्त करें",
                        fontSize = 11.sp,
                        color = ShyamGoldDark,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            items(accessories, key = { it.id }) { item ->
                AccessoryCard(
                    item = item,
                    onWhatsAppOrder = {
                        IntentUtils.openWhatsAppChat(
                            context,
                            ShopInfo.PRIMARY_PHONE,
                            "नमस्ते श्री श्याम मोबाइल केयर बूँदी,\nमुझे '${item.nameHi}' (मूल्य: ₹${item.price}) चाहिए। क्या यह दुकान पर उपलब्ध है?"
                        )
                    },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@Composable
private fun RepairHeroBanner(
    onBookClick: () -> Unit,
    onCallClick: () -> Unit
) {
    Card3D(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(22.dp),
        elevation = 6.dp,
        borderBrush = Brush.linearGradient(listOf(ShyamBlueLight, ShyamGold))
    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            ShyamBlueDark,
                            Color(0xFF1E293B)
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
                            text = "★ श्री श्याम मोबाइल रिपेयरिंग सेंटर",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            color = ShyamBlueDark,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = EmeraldSuccess.copy(alpha = 0.2f)
                    ) {
                        Text(
                            text = "गारंटी सहित काम",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = EmeraldSuccess,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "टच स्क्रीन फोल्डर, बैटरी\nचार्जिंग व पानी में गिरा मोबाइल",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White,
                    lineHeight = 24.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Vivo, Oppo, Realme, Samsung, Redmi, Apple, OnePlus सभी मॉडल्स की फास्ट रिपेयरिंग",
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.85f)
                )

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = onBookClick,
                        colors = ButtonDefaults.buttonColors(containerColor = ShyamBluePrimary),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.weight(1.2f)
                    ) {
                        Icon(Icons.Default.Build, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("रिपेयरिंग बुक करें", fontWeight = FontWeight.Bold)
                    }

                    OutlinedButton(
                        onClick = onCallClick,
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.weight(0.8f)
                    ) {
                        Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("कॉल करें", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
private fun RepairServiceCard(
    service: RepairService,
    onBook: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card3D(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = 3.dp,
        backgroundColor = Color.White
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(ShyamBlueContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Build,
                            contentDescription = null,
                            tint = ShyamBluePrimary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = service.titleHi,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = ShyamBlueDark
                        )
                        Text(
                            text = "समय: ${service.turnaroundTime} • ${service.warrantyText}",
                            fontSize = 11.sp,
                            color = Color(0xFF64748B)
                        )
                    }
                }

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = ShyamGoldLight
                ) {
                    Text(
                        text = service.estPrice,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = ShyamGoldDark,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Features list
            service.features.forEach { feature ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 1.dp)
                ) {
                    Icon(Icons.Default.Check, contentDescription = null, tint = EmeraldSuccess, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = feature, fontSize = 11.sp, color = Color(0xFF334155))
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = onBook,
                colors = ButtonDefaults.buttonColors(containerColor = ShyamBluePrimary),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Handyman, contentDescription = null, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("इस सेवा के लिए फोन बुक करें", fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun AccessoryCard(
    item: MobileAccessory,
    onWhatsAppOrder: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card3D(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = 3.dp,
        backgroundColor = Color.White
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = Color(0xFFF1F5F9)
                ) {
                    Text(
                        text = item.category,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = ShyamBlueDark,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = EmeraldSuccess.copy(alpha = 0.15f)
                ) {
                    Text(
                        text = item.warranty,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = EmeraldSuccessDark,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = item.nameHi,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = ShyamBlueDark
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Pricing row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = "₹${item.price}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Black,
                        color = ShyamBluePrimary
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "₹${item.originalPrice}",
                        fontSize = 12.sp,
                        color = Color(0xFF94A3B8),
                        textDecoration = TextDecoration.LineThrough
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    val discount = ((item.originalPrice - item.price) * 100) / item.originalPrice
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = ShyamGoldLight
                    ) {
                        Text(
                            text = "$discount% छूट",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = ShyamGoldDark,
                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                        )
                    }
                }

                Button(
                    onClick = onWhatsAppOrder,
                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldSuccess),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("व्हाट्सएप ऑर्डर", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
