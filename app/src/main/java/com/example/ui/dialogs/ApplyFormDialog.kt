package com.example.ui.dialogs

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.model.EmitraService
import com.example.model.ServiceApplication
import com.example.model.ShopInfo
import com.example.ui.components.Card3D
import com.example.ui.components.QrCodeView
import com.example.ui.theme.*
import com.example.util.IntentUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplyFormDialog(
    service: EmitraService,
    isHindi: Boolean,
    onDismiss: () -> Unit,
    onSubmit: (ServiceApplication) -> Unit
) {
    val context = LocalContext.current
    var applicantName by remember { mutableStateOf("") }
    var fatherName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var aadharOrJanAadhar by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("General / OBC") }
    var notes by remember { mutableStateOf("") }

    // Track checked documents
    val checkedDocuments = remember {
        mutableStateMapOf<String, Boolean>().apply {
            service.requiredDocuments.forEach { put(it.name, true) }
        }
    }

    var paymentMode by remember { mutableStateOf("UPI_QR") } // "UPI_QR" or "PAY_AT_SHOP"
    var utrNumber by remember { mutableStateOf("") }
    val totalFee = service.govtFee + service.emitraFee

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.92f)
                .clip(RoundedCornerShape(24.dp)),
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (isHindi) "फॉर्म भरवाने का आवेदन" else "Apply for Form at Shop",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black,
                            color = ShyamBluePrimary
                        )
                        Text(
                            text = if (isHindi) service.titleHi else service.titleEn,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF64748B),
                            maxLines = 2
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }

                Divider(modifier = Modifier.padding(vertical = 12.dp))

                // Applicant details
                Text(
                    text = if (isHindi) "1. आवेदक की जानकारी" else "1. Applicant Information",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = ShyamBlueDark
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = applicantName,
                    onValueChange = { applicantName = it },
                    label = { Text(if (isHindi) "अभ्यर्थी का पूरा नाम *" else "Full Name *") },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = fatherName,
                    onValueChange = { fatherName = it },
                    label = { Text(if (isHindi) "पिता/पति का नाम *" else "Father's / Husband's Name *") },
                    leadingIcon = { Icon(Icons.Default.FamilyRestroom, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = phone,
                    onValueChange = { if (it.length <= 10) phone = it },
                    label = { Text(if (isHindi) "मोबाइल नंबर (व्हाट्सएप) *" else "Mobile Number *") },
                    leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = aadharOrJanAadhar,
                    onValueChange = { aadharOrJanAadhar = it },
                    label = { Text(if (isHindi) "आधार नंबर या जन आधार नंबर" else "Aadhaar or Jan Aadhaar No.") },
                    leadingIcon = { Icon(Icons.Default.Badge, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Documents Checklist
                Text(
                    text = if (isHindi) "2. आवश्यक दस्तावेज (टिक करें जो आपके पास हैं)" else "2. Documents Checklist",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = ShyamBlueDark
                )

                Spacer(modifier = Modifier.height(6.dp))

                Card3D(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    elevation = 2.dp
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        service.requiredDocuments.forEach { doc ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 3.dp)
                                    .clickable {
                                        checkedDocuments[doc.name] = !(checkedDocuments[doc.name] ?: false)
                                    },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = checkedDocuments[doc.name] ?: false,
                                    onCheckedChange = { checkedDocuments[doc.name] = it },
                                    colors = CheckboxDefaults.colors(checkedColor = ShyamBluePrimary)
                                )
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = doc.name,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                    if (doc.hindiHint.isNotEmpty()) {
                                        Text(
                                            text = doc.hindiHint,
                                            fontSize = 11.sp,
                                            color = Color(0xFF64748B)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Fee & Payment section
                Text(
                    text = if (isHindi) "3. शुल्क एवं भुगतान विवरण" else "3. Fee & Payment Details",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = ShyamBlueDark
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Fee breakdown box
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = ShyamGoldLight.copy(alpha = 0.5f),
                    border = androidx.compose.foundation.BorderStroke(1.dp, ShyamGold),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "सरकारी परीक्षा/पोर्टल फीस:", fontSize = 12.sp, color = Color(0xFF475569))
                            Text(text = "₹${service.govtFee}", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "ई-मित्र चार्ज (प्रति फॉर्म):", fontSize = 12.sp, color = Color(0xFF475569))
                            Text(text = "₹${service.emitraFee} (निर्धारित)", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = ShyamBluePrimary)
                        }
                        Divider(modifier = Modifier.padding(vertical = 6.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "कुल देय राशि (सरकारी फीस + ई-मित्र चार्ज):",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = ShyamBlueDark
                            )
                            Text(
                                text = "₹$totalFee",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Black,
                                color = ShyamBluePrimary
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Payment mode choices
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = paymentMode == "UPI_QR",
                        onClick = { paymentMode = "UPI_QR" },
                        label = { Text("ऑनलाइन UPI / QR कोड") },
                        leadingIcon = { Icon(Icons.Default.QrCode, contentDescription = null, modifier = Modifier.size(16.dp)) },
                        modifier = Modifier.weight(1f)
                    )
                    FilterChip(
                        selected = paymentMode == "PAY_AT_SHOP",
                        onClick = { paymentMode = "PAY_AT_SHOP" },
                        label = { Text("दुकान पर नकद जमा") },
                        leadingIcon = { Icon(Icons.Default.Storefront, contentDescription = null, modifier = Modifier.size(16.dp)) },
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                if (paymentMode == "UPI_QR") {
                    Card3D(
                        modifier = Modifier.fillMaxWidth(),
                        backgroundColor = Color(0xFFF8FAFC),
                        elevation = 3.dp
                    ) {
                        Column(
                            modifier = Modifier.padding(14.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "QR कोड स्कैन करके ₹$totalFee का भुगतान करें",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = ShyamBluePrimary
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            QrCodeView(amount = totalFee)

                            Spacer(modifier = Modifier.height(12.dp))

                            // Prominent 1-Tap Copy UPI ID button
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = Color.White,
                                border = androidx.compose.foundation.BorderStroke(1.5.dp, ShyamBlueMedium),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp))
                                    .clickable {
                                        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                        clipboard.setPrimaryClip(ClipData.newPlainText("UPI ID", ShopInfo.UPI_ID))
                                        Toast.makeText(context, "UPI ID कॉपी हो गई: ${ShopInfo.UPI_ID}", Toast.LENGTH_SHORT).show()
                                    }
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Icon(Icons.Default.ContentCopy, contentDescription = null, tint = ShyamBluePrimary, modifier = Modifier.size(18.dp))
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "1-टैप UPI ID कॉपी: ${ShopInfo.UPI_ID}",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = ShyamBluePrimary
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            // Direct UPI Apps Launch button
                            Button(
                                onClick = {
                                    IntentUtils.launchUpiPayment(
                                        context,
                                        totalFee,
                                        "Form Fee: ${service.titleEn.take(20)}"
                                    )
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = EmeraldSuccess),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(Icons.Default.Payment, contentDescription = null, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("सीधे PhonePe / GPay / Paytm से भुगतान करें", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            OutlinedTextField(
                                value = utrNumber,
                                onValueChange = { utrNumber = it },
                                label = { Text("भुगतान के बाद UTR / ट्रांजैक्शन नंबर डालें (वैकल्पिक)") },
                                placeholder = { Text("उदा. 423985712344") },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(10.dp),
                                singleLine = true
                            )
                        }
                    }
                } else {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFFF1F5F9),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Info, contentDescription = null, tint = ShyamBluePrimary)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "आप दुकान पर आकर आवश्यक दस्तावेज व नकद राशि जमा करवा सकते हैं। आपका फॉर्म प्राथमिकता से भरा जाएगा।",
                                fontSize = 12.sp,
                                color = Color(0xFF334155)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Submit button
                Button(
                    onClick = {
                        if (applicantName.isBlank() || phone.isBlank()) {
                            Toast.makeText(context, "कृपया नाम व मोबाइल नंबर भरें!", Toast.LENGTH_SHORT).show()
                            return@Button
                        }

                        val appId = "SSE-2026-${(1000..9999).random()}"
                        val selectedDocsList = checkedDocuments.filter { it.value }.keys.toList()

                        val newApp = ServiceApplication(
                            id = appId,
                            serviceId = service.id,
                            serviceTitle = service.titleHi,
                            applicantName = applicantName,
                            fatherName = fatherName,
                            phone = phone,
                            aadharOrJanAadhar = aadharOrJanAadhar,
                            category = category,
                            notes = notes,
                            checkedDocuments = selectedDocsList,
                            totalFee = totalFee,
                            paymentMode = paymentMode,
                            utrNumber = utrNumber
                        )
                        onSubmit(newApp)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = ShyamBluePrimary),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Icon(Icons.Default.CheckCircle, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isHindi) "आवेदन सबमिट करें व रसीद प्राप्त करें" else "Submit Application & Get Receipt",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
