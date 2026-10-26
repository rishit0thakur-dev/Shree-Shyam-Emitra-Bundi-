package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.ShopInfo
import com.example.ui.components.Card3D
import com.example.ui.components.QrCodeView
import com.example.ui.theme.*
import com.example.util.IntentUtils

@Composable
fun ShopLocationScreen(
    isHindi: Boolean
) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Hero Map & Address Card
        item {
            ShopHeroCard(
                onNavigateMaps = { IntentUtils.openShopInGoogleMaps(context) },
                onCallPrimary = { IntentUtils.dialPhoneNumber(context, ShopInfo.PRIMARY_PHONE) },
                onCallSecondary = { IntentUtils.dialPhoneNumber(context, ShopInfo.SECONDARY_PHONE) },
                onWhatsApp = {
                    IntentUtils.openWhatsAppChat(
                        context,
                        ShopInfo.PRIMARY_PHONE,
                        "नमस्ते श्री श्याम ई-मित्र बूँदी, मैं आपकी दुकान की लोकेशन पर आ रहा हूँ।"
                    )
                }
            )
        }

        // Direct Dialing Numbers Box
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "दुकान के संपर्क नंबर (Call & WhatsApp):",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = ShyamBlueDark
                )

                ContactNumberCard(
                    title = "मुख्य संपर्क नंबर (E-Mitra & Repair)",
                    number = ShopInfo.PRIMARY_PHONE,
                    isPrimary = true,
                    onCall = { IntentUtils.dialPhoneNumber(context, ShopInfo.PRIMARY_PHONE) },
                    onWhatsApp = {
                        IntentUtils.openWhatsAppChat(
                            context,
                            ShopInfo.PRIMARY_PHONE,
                            "नमस्ते श्री श्याम ई-मित्र बूँदी!"
                        )
                    }
                )

                ContactNumberCard(
                    title = "हेल्पलाइन एवं जॉब्स नंबर (Bundi Jobs & Query)",
                    number = ShopInfo.SECONDARY_PHONE,
                    isPrimary = false,
                    onCall = { IntentUtils.dialPhoneNumber(context, ShopInfo.SECONDARY_PHONE) },
                    onWhatsApp = {
                        IntentUtils.openWhatsAppChat(
                            context,
                            ShopInfo.SECONDARY_PHONE,
                            "नमस्ते जॉब वाले भैया बूँदी!"
                        )
                    }
                )
            }
        }

        // Location & Landmark details
        item {
            Card3D(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(18.dp),
                elevation = 3.dp,
                backgroundColor = Color.White
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "दुकान तक कैसे पहुंचे (How to Reach):",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = ShyamBluePrimary
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    LocationPoint(
                        icon = Icons.Default.LocationOn,
                        title = "पता (Address)",
                        desc = ShopInfo.ADDRESS_HI
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    LocationPoint(
                        icon = Icons.Default.NearMe,
                        title = "प्रमुख लैंडमार्क (Landmark)",
                        desc = ShopInfo.LANDMARK
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    LocationPoint(
                        icon = Icons.Default.Schedule,
                        title = "दुकान खुलने का समय (Timings)",
                        desc = ShopInfo.WORKING_HOURS
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = ShyamBlueContainer,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { IntentUtils.openShopInGoogleMaps(context) }
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Link, contentDescription = null, tint = ShyamBluePrimary, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "मैप लिंक: ${ShopInfo.GOOGLE_MAPS_LINK}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = ShyamBluePrimary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Button(
                        onClick = { IntentUtils.openShopInGoogleMaps(context) },
                        colors = ButtonDefaults.buttonColors(containerColor = ShyamBluePrimary),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.Navigation, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("गूगल मैप्स पर रास्ता देखें (Google Maps Navigation)", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // Shop Facilities
        item {
            Card3D(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(18.dp),
                elevation = 3.dp,
                backgroundColor = Color.White
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "दुकान पर उपलब्ध सभी सुविधाएं:",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = ShyamBlueDark
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    ShopInfo.SERVICES_AT_SHOP.forEach { facility ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = EmeraldSuccess, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = facility, fontSize = 12.sp, color = Color(0xFF334155), fontWeight = FontWeight.Medium)
                        }
                    }
                }
            }
        }

        // Shop UPI QR Code
        item {
            Card3D(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(18.dp),
                elevation = 3.dp,
                backgroundColor = Color(0xFFF8FAFC)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "दुकान का डिजिटल UPI पेमेंट QR कोड",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = ShyamBluePrimary
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    QrCodeView(amount = 100)

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "दुकान पर किसी भी सेवा हेतु स्कैन करके भुगतान करें",
                        fontSize = 11.sp,
                        color = Color(0xFF64748B)
                    )
                }
            }
        }
    }
}

@Composable
private fun ShopHeroCard(
    onNavigateMaps: () -> Unit,
    onCallPrimary: () -> Unit,
    onCallSecondary: () -> Unit,
    onWhatsApp: () -> Unit
) {
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
                            text = "श्री श्याम ई-मित्र बूँदी",
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
                            text = "खुला है (Open Today)",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = EmeraldSuccess,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = ShopInfo.SHOP_NAME_HI,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White,
                    lineHeight = 24.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LocationOn, contentDescription = null, tint = ShyamGold, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = ShopInfo.ADDRESS_HI,
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Maps button
                Button(
                    onClick = onNavigateMaps,
                    colors = ButtonDefaults.buttonColors(containerColor = ShyamGold),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.Directions, contentDescription = null, tint = ShyamBlueDark)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("दुकान की लोकेशन पर नेविगेट करें (Get Directions)", color = ShyamBlueDark, fontWeight = FontWeight.Black)
                }
            }
        }
    }
}

@Composable
private fun ContactNumberCard(
    title: String,
    number: String,
    isPrimary: Boolean,
    onCall: () -> Unit,
    onWhatsApp: () -> Unit
) {
    Card3D(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        elevation = 2.dp,
        backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, fontSize = 11.sp, color = Color(0xFF64748B))
                Text(
                    text = number,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = ShyamBlueDark
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                IconButton(
                    onClick = onCall,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(ShyamBluePrimary)
                ) {
                    Icon(Icons.Default.Call, contentDescription = "Call", tint = Color.White, modifier = Modifier.size(18.dp))
                }

                IconButton(
                    onClick = onWhatsApp,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(EmeraldSuccess)
                ) {
                    Icon(Icons.Default.Chat, contentDescription = "WhatsApp", tint = Color.White, modifier = Modifier.size(18.dp))
                }
            }
        }
    }
}

@Composable
private fun LocationPoint(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    desc: String
) {
    Row(verticalAlignment = Alignment.Top) {
        Icon(icon, contentDescription = null, tint = ShyamBluePrimary, modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = title, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = ShyamBlueDark)
            Text(text = desc, fontSize = 12.sp, color = Color(0xFF475569))
        }
    }
}
