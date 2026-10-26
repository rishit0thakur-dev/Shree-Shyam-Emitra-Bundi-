package com.example.ui.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.data.db.ApplicationEntity
import com.example.data.db.RepairEntity
import com.example.model.ApplicationStatus
import com.example.model.ServiceApplication
import com.example.model.ShopInfo
import com.example.ui.components.Card3D
import com.example.ui.theme.*
import com.example.util.IntentUtils
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TrackingDialog(
    applications: List<ApplicationEntity>,
    repairs: List<RepairEntity>,
    isHindi: Boolean,
    onDismiss: () -> Unit,
    onViewReceipt: (ServiceApplication) -> Unit
) {
    val context = LocalContext.current
    var selectedTab by remember { mutableStateOf(0) } // 0: Forms, 1: Repairs

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
                    .padding(16.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = if (isHindi) "आवेदन व रिपेयर स्टेटस ट्रैकर" else "Application Status Tracker",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black,
                            color = ShyamBluePrimary
                        )
                        Text(
                            text = "श्री श्याम ई-मित्र बूँदी रिकॉर्ड्स",
                            fontSize = 12.sp,
                            color = Color(0xFF64748B)
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Tab Row
                TabRow(
                    selectedTabIndex = selectedTab,
                    containerColor = Color(0xFFF1F5F9),
                    contentColor = ShyamBluePrimary,
                    modifier = Modifier.clip(RoundedCornerShape(12.dp))
                ) {
                    Tab(
                        selected = selectedTab == 0,
                        onClick = { selectedTab = 0 },
                        text = {
                            Text(
                                text = "ई-मित्र फॉर्म (${applications.size})",
                                fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        }
                    )
                    Tab(
                        selected = selectedTab == 1,
                        onClick = { selectedTab = 1 },
                        text = {
                            Text(
                                text = "मोबाइल रिपेयर (${repairs.size})",
                                fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                if (selectedTab == 0) {
                    // Form applications list
                    if (applications.isEmpty()) {
                        EmptyTrackingState(
                            message = if (isHindi) "आपने अभी तक कोई फॉर्म अप्लाई नहीं किया है।" else "No form applications found yet."
                        )
                    } else {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(applications) { app ->
                                FormTrackingCard(
                                    app = app,
                                    isHindi = isHindi,
                                    onViewReceipt = {
                                        val statusEnum = runCatching {
                                            ApplicationStatus.valueOf(app.status)
                                        }.getOrDefault(ApplicationStatus.PENDING)

                                        val model = ServiceApplication(
                                            id = app.id,
                                            serviceId = app.serviceId,
                                            serviceTitle = app.serviceTitle,
                                            applicantName = app.applicantName,
                                            fatherName = app.fatherName,
                                            phone = app.phone,
                                            aadharOrJanAadhar = app.aadharOrJanAadhar,
                                            category = app.category,
                                            notes = app.notes,
                                            checkedDocuments = if (app.checkedDocuments.isNotBlank()) app.checkedDocuments.split(", ") else emptyList(),
                                            totalFee = app.totalFee,
                                            paymentMode = app.paymentMode,
                                            utrNumber = app.utrNumber,
                                            status = statusEnum,
                                            timestamp = app.timestamp
                                        )
                                        onViewReceipt(model)
                                    },
                                    onAskShop = {
                                        IntentUtils.openWhatsAppChat(
                                            context,
                                            ShopInfo.PRIMARY_PHONE,
                                            "नमस्ते श्री श्याम ई-मित्र बूँदी, कृपया मेरे फॉर्म की स्थिति बताएं:\n" +
                                                    "टोकन ID: ${app.id}\n" +
                                                    "फॉर्म: ${app.serviceTitle}\n" +
                                                    "अभ्यर्थी: ${app.applicantName}"
                                        )
                                    }
                                )
                            }
                        }
                    }
                } else {
                    // Mobile repairs list
                    if (repairs.isEmpty()) {
                        EmptyTrackingState(
                            message = if (isHindi) "आपने कोई मोबाइल रिपेयरिंग बुक नहीं की है।" else "No mobile repairs booked yet."
                        )
                    } else {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(repairs) { rep ->
                                RepairTrackingCard(
                                    repair = rep,
                                    onAskShop = {
                                        IntentUtils.openWhatsAppChat(
                                            context,
                                            ShopInfo.PRIMARY_PHONE,
                                            "नमस्ते श्री श्याम मोबाइल केयर बूँदी, मेरे रिपेयर का स्टेटस बताएं:\n" +
                                                    "टोकन: ${rep.id}\n" +
                                                    "मॉडल: ${rep.phoneModel}\n" +
                                                    "समस्या: ${rep.issueType}"
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FormTrackingCard(
    app: ApplicationEntity,
    isHindi: Boolean,
    onViewReceipt: () -> Unit,
    onAskShop: () -> Unit
) {
    val dateStr = SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault()).format(Date(app.timestamp))

    Card3D(
        modifier = Modifier.fillMaxWidth(),
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
                    color = ShyamBlueContainer
                ) {
                    Text(
                        text = app.id,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = ShyamBluePrimary,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = EmeraldSuccess.copy(alpha = 0.15f)
                ) {
                    Text(
                        text = app.status,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = EmeraldSuccessDark,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = app.serviceTitle,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = ShyamBlueDark
            )

            Text(
                text = "आवेदक: ${app.applicantName} • मो: ${app.phone}",
                fontSize = 12.sp,
                color = Color(0xFF64748B)
            )

            Text(
                text = "दिनांक: $dateStr • फीस: ₹${app.totalFee} (${if (app.paymentMode == "UPI_QR") "ऑनलाइन UPI" else "नकद"})",
                fontSize = 11.sp,
                color = Color(0xFF64748B)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = onViewReceipt,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Receipt, contentDescription = null, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("रसीद देखें", fontSize = 12.sp)
                }

                Button(
                    onClick = onAskShop,
                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldSuccess),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("व्हाट्सएप पूछताछ", fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
private fun RepairTrackingCard(
    repair: RepairEntity,
    onAskShop: () -> Unit
) {
    val dateStr = SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault()).format(Date(repair.timestamp))

    Card3D(
        modifier = Modifier.fillMaxWidth(),
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
                    color = ShyamGoldLight
                ) {
                    Text(
                        text = repair.id,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = ShyamGoldDark,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = ShyamBlueContainer
                ) {
                    Text(
                        text = "दुकान पर जांच जारी",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = ShyamBluePrimary,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "${repair.phoneBrand} ${repair.phoneModel}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = ShyamBlueDark
            )

            Text(
                text = "समस्या: ${repair.issueType}",
                fontSize = 12.sp,
                color = ShyamBluePrimary,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "ग्राहक: ${repair.customerName} (${repair.customerPhone}) • $dateStr",
                fontSize = 11.sp,
                color = Color(0xFF64748B)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = onAskShop,
                colors = ButtonDefaults.buttonColors(containerColor = EmeraldSuccess),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("रिपेयरिंग स्टेटस व्हाट्सएप पर पूछें", fontWeight = FontWeight.Bold, fontSize = 12.sp)
            }
        }
    }
}

@Composable
private fun EmptyTrackingState(message: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color(0xFFF1F5F9)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.History, contentDescription = null, tint = Color(0xFF94A3B8), modifier = Modifier.size(32.dp))
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = message,
            fontSize = 13.sp,
            color = Color(0xFF64748B),
            fontWeight = FontWeight.Medium
        )
    }
}
