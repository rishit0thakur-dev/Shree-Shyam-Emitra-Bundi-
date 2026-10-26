package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.ShopInfo
import com.example.ui.theme.*
import com.example.util.IntentUtils

@Composable
fun AppTopBar(
    isHindi: Boolean,
    onToggleLanguage: () -> Unit,
    activeCount: Int,
    onOpenTracking: () -> Unit,
    onOpenLocation: () -> Unit
) {
    val context = LocalContext.current
    var showCallPicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        ShyamBlueDark,
                        ShyamBluePrimary
                    )
                )
            )
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        // Top row: Brand & Actions
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                // 3D Emblem Badge
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .shadow(4.dp, CircleShape)
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(
                                colors = listOf(ShyamGold, ShyamGoldDark)
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "SS",
                        fontWeight = FontWeight.Black,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = if (isHindi) "श्री श्याम ई-मित्र" else "Shree Shyam E-mitra",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        // Bundi Badge
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = ShyamGold
                        ) {
                            Text(
                                text = "बूँदी",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = ShyamBlueDark,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 1.dp)
                            )
                        }
                    }
                    Text(
                        text = if (isHindi) "मोबाइल रिपेयर • सरकारी योजनाएं • बूँदी जॉब्स" else "Mobile Repair • Govt Vacancies • Bundi Jobs",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White.copy(alpha = 0.82f)
                    )
                }
            }

            // Action buttons
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // Tracking Status button
                IconButton(
                    onClick = onOpenTracking,
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.15f))
                ) {
                    BadgedBox(
                        badge = {
                            if (activeCount > 0) {
                                Badge(
                                    containerColor = ShyamGold,
                                    contentColor = ShyamBlueDark
                                ) {
                                    Text(
                                        text = "$activeCount",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 10.sp
                                    )
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ReceiptLong,
                            contentDescription = "My Applications",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                // Phone Call button
                IconButton(
                    onClick = { showCallPicker = true },
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(EmeraldSuccess)
                ) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "Call Shree Shyam",
                        tint = Color.White,
                        modifier = Modifier.size(19.dp)
                    )
                }

                // Language toggle pill
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White.copy(alpha = 0.18f),
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { onToggleLanguage() }
                ) {
                    Text(
                        text = if (isHindi) "EN" else "हिन्दी",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Quick Hotline & Location Strip
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = Color.White.copy(alpha = 0.12f),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { showCallPicker = true }
                ) {
                    Icon(
                        imageVector = Icons.Default.PhoneInTalk,
                        contentDescription = null,
                        tint = ShyamGold,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${ShopInfo.PRIMARY_PHONE} / ${ShopInfo.SECONDARY_PHONE}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { onOpenLocation() }
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = ShyamGold,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = if (isHindi) "विशाल मेगा मॉल, बूँदी" else "Vishal Mega Mall, Bundi",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = ShyamGold
                    )
                }
            }
        }
    }

    if (showCallPicker) {
        AlertDialog(
            onDismissRequest = { showCallPicker = false },
            title = {
                Text(
                    text = if (isHindi) "श्री श्याम ई-मित्र पर कॉल करें" else "Call Shree Shyam E-mitra",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = if (isHindi) "नीचे दिए गए किसी भी नंबर पर सीधा संपर्क करें:" else "Directly connect via any of the numbers below:",
                        fontSize = 13.sp
                    )

                    // Number 1
                    Button(
                        onClick = {
                            showCallPicker = false
                            IntentUtils.dialPhoneNumber(context, ShopInfo.PRIMARY_PHONE)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = ShyamBluePrimary),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("कॉल करें: ${ShopInfo.PRIMARY_PHONE} (मुख्य नंबर)", fontWeight = FontWeight.Bold)
                    }

                    // Number 2
                    Button(
                        onClick = {
                            showCallPicker = false
                            IntentUtils.dialPhoneNumber(context, ShopInfo.SECONDARY_PHONE)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = ShyamBluePrimary),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("कॉल करें: ${ShopInfo.SECONDARY_PHONE}", fontWeight = FontWeight.Bold)
                    }

                    // WhatsApp Action
                    OutlinedButton(
                        onClick = {
                            showCallPicker = false
                            IntentUtils.openWhatsAppChat(
                                context,
                                ShopInfo.PRIMARY_PHONE,
                                "नमस्ते श्री श्याम ई-मित्र बूँदी, मुझे फॉर्म / रिपेयर के बारे में जानकारी चाहिए।"
                            )
                        },
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = EmeraldSuccess),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(Icons.Default.Chat, contentDescription = null, tint = EmeraldSuccess, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("व्हाट्सएप पर मैसेज भेजें", fontWeight = FontWeight.Bold)
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showCallPicker = false }) {
                    Text(if (isHindi) "बंद करें" else "Close")
                }
            }
        )
    }
}
