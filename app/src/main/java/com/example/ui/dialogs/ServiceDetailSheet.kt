package com.example.ui.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.model.EmitraCategory
import com.example.model.EmitraService
import com.example.model.ShopInfo
import com.example.ui.components.Card3D
import com.example.ui.theme.*
import com.example.util.IntentUtils

@Composable
fun ServiceDetailSheet(
    service: EmitraService,
    isHindi: Boolean,
    onDismiss: () -> Unit,
    onApplyClick: () -> Unit
) {
    val context = LocalContext.current

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.9f)
                .clip(RoundedCornerShape(24.dp)),
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp)
            ) {
                // Top Header Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = if (service.category == EmitraCategory.GOVT_VACANCY) ShyamBlueContainer else ShyamGoldLight
                    ) {
                        Text(
                            text = if (isHindi) service.category.displayNameHi else service.category.displayNameEn,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (service.category == EmitraCategory.GOVT_VACANCY) ShyamBluePrimary else ShyamGoldDark,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }

                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = if (isHindi) service.titleHi else service.titleEn,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = ShyamBluePrimary
                )

                Text(
                    text = service.department,
                    fontSize = 12.sp,
                    color = Color(0xFF64748B),
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Important Info Grid
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    InfoPill(
                        icon = Icons.Outlined.Event,
                        label = "अंतिम तिथि",
                        value = service.lastDate,
                        modifier = Modifier.weight(1f)
                    )
                    InfoPill(
                        icon = Icons.Default.CurrencyRupee,
                        label = "कुल फीस",
                        value = "₹${service.govtFee + service.emitraFee}",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Highlights
                if (service.highlights.isNotEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF1F5F9), RoundedCornerShape(12.dp))
                            .padding(12.dp)
                    ) {
                        service.highlights.forEach { highlight ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(vertical = 2.dp)
                            ) {
                                Icon(Icons.Default.Star, contentDescription = null, tint = ShyamGold, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(text = highlight, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = ShyamBlueDark)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                }

                // Description
                Text(
                    text = "विवरण (About):",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = ShyamBlueDark
                )
                Text(
                    text = service.description,
                    fontSize = 13.sp,
                    color = Color(0xFF334155),
                    lineHeight = 18.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Eligibility & Age Limit
                Card3D(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = Color(0xFFF8FAFC),
                    elevation = 2.dp
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "शैक्षणिक योग्यता एवं आयु सीमा",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = ShyamBluePrimary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "• योग्यता: ${service.eligibility}",
                            fontSize = 12.sp,
                            color = Color(0xFF334155)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "• आयु सीमा: ${service.ageLimit}",
                            fontSize = 12.sp,
                            color = Color(0xFF334155)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Required Documents List
                Text(
                    text = "आवश्यक दस्तावेज (Required Documents):",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = ShyamBlueDark
                )

                Spacer(modifier = Modifier.height(8.dp))

                service.requiredDocuments.forEachIndexed { index, doc ->
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = if (doc.isMandatory) Color(0xFFEFF6FF) else Color(0xFFF8FAFC),
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            if (doc.isMandatory) ShyamBlueLight.copy(alpha = 0.5f) else Color(0xFFE2E8F0)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .background(if (doc.isMandatory) ShyamBluePrimary else Color(0xFF94A3B8)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${index + 1}",
                                    color = Color.White,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = doc.name,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = ShyamBlueDark
                                )
                                if (doc.hindiHint.isNotEmpty()) {
                                    Text(
                                        text = doc.hindiHint,
                                        fontSize = 11.sp,
                                        color = Color(0xFF64748B)
                                    )
                                }
                            }
                            if (doc.isMandatory) {
                                Surface(
                                    shape = RoundedCornerShape(4.dp),
                                    color = CrimsonAlert.copy(alpha = 0.1f)
                                ) {
                                    Text(
                                        text = "अनिवार्य",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = CrimsonAlert,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Action buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // WhatsApp inquiry
                    OutlinedButton(
                        onClick = {
                            IntentUtils.openWhatsAppChat(
                                context,
                                ShopInfo.PRIMARY_PHONE,
                                "नमस्ते श्री श्याम ई-मित्र बूँदी, मुझे '${service.titleHi}' का फॉर्म भरवाना है। कृपया सहायता करें।"
                            )
                        },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.weight(0.9f)
                    ) {
                        Icon(Icons.Default.Chat, contentDescription = null, tint = EmeraldSuccess, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("व्हाट्सएप", fontWeight = FontWeight.Bold)
                    }

                    // Apply online
                    Button(
                        onClick = {
                            onDismiss()
                            onApplyClick()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = ShyamBluePrimary),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.weight(1.3f)
                    ) {
                        Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (isHindi) "फॉर्म भरवाएं" else "Apply Online",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun InfoPill(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFF1F5F9),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = ShyamBluePrimary, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = label, fontSize = 10.sp, color = Color(0xFF64748B))
                Text(text = value, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = ShyamBlueDark)
            }
        }
    }
}
