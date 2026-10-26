package com.example.ui.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.model.ServiceApplication
import com.example.model.ShopInfo
import com.example.ui.theme.*
import com.example.util.IntentUtils
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ReceiptDialog(
    application: ServiceApplication,
    isHindi: Boolean,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val dateStr = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault()).format(Date(application.timestamp))

    val shareableText = buildString {
        append("🧾 *श्री श्याम ई-मित्र बूँदी - आवेदन रसीद*\n")
        append("रजिस्ट्रेशन नंबर: ${application.id}\n")
        append("फॉर्म का नाम: ${application.serviceTitle}\n")
        append("अभ्यर्थी: ${application.applicantName}\n")
        append("पिता/पति: ${application.fatherName}\n")
        append("मोबाइल: ${application.phone}\n")
        append("कुल शुल्क: ₹${application.totalFee}\n")
        append("भुगतान माध्यम: ${if (application.paymentMode == "UPI_QR") "ऑनलाइन UPI" else "दुकान पर नकद"}\n")
        if (application.utrNumber.isNotBlank()) {
            append("UTR / ट्रांजेक्शन: ${application.utrNumber}\n")
        }
        append("स्थिति: ${application.status.titleHi}\n")
        append("दुकान: ${ShopInfo.SHOP_NAME_HI}\n")
        append("पता: ${ShopInfo.ADDRESS_HI}\n")
        append("गूगल मैप्स: ${ShopInfo.GOOGLE_MAPS_LINK}\n")
        append("संपर्क: ${ShopInfo.PRIMARY_PHONE} / ${ShopInfo.SECONDARY_PHONE}")
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .fillMaxHeight(0.88f)
                .clip(RoundedCornerShape(24.dp)),
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Success Badge
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(EmeraldSuccess.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = EmeraldSuccess,
                        modifier = Modifier.size(36.dp)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = if (isHindi) "आवेदन सफलतापूर्वक प्राप्त हुआ!" else "Application Received Successfully!",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Black,
                    color = ShyamBluePrimary
                )

                Text(
                    text = "श्री श्याम ई-मित्र बूँदी में आपका स्वागत है",
                    fontSize = 12.sp,
                    color = Color(0xFF64748B)
                )

                Spacer(modifier = Modifier.height(14.dp))

                // The Slip Card
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = Color(0xFFF8FAFC),
                    border = androidx.compose.foundation.BorderStroke(1.5.dp, ShyamBluePrimary.copy(alpha = 0.3f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "टोकन / रसीद संख्या:",
                                fontSize = 11.sp,
                                color = Color(0xFF64748B)
                            )
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = ShyamBlueContainer
                            ) {
                                Text(
                                    text = application.id,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = ShyamBluePrimary,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        Divider()
                        Spacer(modifier = Modifier.height(8.dp))

                        ReceiptRow(label = "फॉर्म का नाम:", value = application.serviceTitle)
                        ReceiptRow(label = "अभ्यर्थी का नाम:", value = application.applicantName)
                        if (application.fatherName.isNotBlank()) {
                            ReceiptRow(label = "पिता/पति का नाम:", value = application.fatherName)
                        }
                        ReceiptRow(label = "मोबाइल नंबर:", value = application.phone)
                        ReceiptRow(label = "दिनांक व समय:", value = dateStr)
                        ReceiptRow(
                            label = "कुल जमा राशि:",
                            value = "₹${application.totalFee} (${if (application.paymentMode == "UPI_QR") "ऑनलाइन UPI" else "दुकान पर देय"})"
                        )
                        if (application.utrNumber.isNotBlank()) {
                            ReceiptRow(label = "UTR रेफरेंस:", value = application.utrNumber)
                        }

                        ReceiptRow(
                            label = "वर्तमान स्थिति:",
                            value = application.status.titleHi,
                            valueColor = EmeraldSuccessDark
                        )

                        if (application.checkedDocuments.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "संलग्न दस्तावेज सूची:",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF475569)
                            )
                            application.checkedDocuments.forEach { doc ->
                                Text(
                                    text = "• $doc",
                                    fontSize = 11.sp,
                                    color = Color(0xFF64748B),
                                    modifier = Modifier.padding(start = 6.dp, top = 1.dp)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // WhatsApp send button
                Button(
                    onClick = {
                        IntentUtils.openWhatsAppChat(
                            context,
                            ShopInfo.PRIMARY_PHONE,
                            "नमस्ते श्री श्याम ई-मित्र बूँदी, मैंने ऐप के माध्यम से फॉर्म का आवेदन किया है। रसीद विवरण:\n\n$shareableText"
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldSuccess),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("दुकानदार को व्हाट्सएप पर भेजें", fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Share slip
                OutlinedButton(
                    onClick = {
                        IntentUtils.shareText(
                            context,
                            "श्री श्याम ई-मित्र रसीद - ${application.id}",
                            shareableText
                        )
                    },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("रसीद शेयर करें / सेव करें", fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(8.dp))

                TextButton(onClick = onDismiss) {
                    Text("बंद करें (Done)")
                }
            }
        }
    }
}

@Composable
private fun ReceiptRow(
    label: String,
    value: String,
    valueColor: Color = ShyamBlueDark
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontSize = 12.sp, color = Color(0xFF64748B))
        Text(
            text = value,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = valueColor,
            modifier = Modifier.widthIn(max = 200.dp)
        )
    }
}
