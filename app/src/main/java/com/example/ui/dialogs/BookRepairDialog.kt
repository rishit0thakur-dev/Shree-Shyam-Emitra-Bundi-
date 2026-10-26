package com.example.ui.dialogs

import android.widget.Toast
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
import com.example.model.RepairRequest
import com.example.model.ShopInfo
import com.example.ui.theme.*
import com.example.util.IntentUtils

@Composable
fun BookRepairDialog(
    preselectedIssue: String? = null,
    isHindi: Boolean,
    onDismiss: () -> Unit,
    onSubmit: (RepairRequest) -> Unit
) {
    val context = LocalContext.current
    var phoneBrand by remember { mutableStateOf("Vivo") }
    var phoneModel by remember { mutableStateOf("") }
    var issueType by remember { mutableStateOf(preselectedIssue ?: "स्क्रीन व फोल्डर बदलना (Display/Touch)") }
    var description by remember { mutableStateOf("") }
    var customerName by remember { mutableStateOf("") }
    var customerPhone by remember { mutableStateOf("") }
    var visitType by remember { mutableStateOf("दुकान पर आएंगे (Visit Shop)") }

    val brands = listOf("Vivo", "Realme", "Oppo", "Samsung", "Redmi/Mi", "OnePlus", "Apple iPhone", "Motorola", "Poco", "अन्य (Other)")
    val issueTypes = listOf(
        "स्क्रीन व फोल्डर बदलना (Display/Touch)",
        "ओरिजिनल बैटरी बदलना (Battery)",
        "चार्जिंग जैक व Type-C पोर्ट (Charging Pin)",
        "माइक, ईयर स्पीकर व लाउडस्पीकर (Sound Problem)",
        "पानी में गिरा फोन (Water Damage)",
        "सॉफ्टवेयर, हैंग व लॉक अनलॉक (Software/FRP)",
        "डेड फोन मदरबोर्ड रिपेयर (Dead Phone/IC)"
    )

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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = if (isHindi) "मोबाइल रिपेयरिंग बुकिंग" else "Book Mobile Repair",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black,
                            color = ShyamBluePrimary
                        )
                        Text(
                            text = "श्री श्याम मोबाइल केयर, खोजा गेट बूँदी",
                            fontSize = 12.sp,
                            color = Color(0xFF64748B)
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }

                Divider(modifier = Modifier.padding(vertical = 12.dp))

                // Brand selection chips
                Text(
                    text = "मोबाइल की कंपनी (Brand) चुनें:",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = ShyamBlueDark
                )

                Spacer(modifier = Modifier.height(6.dp))

                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        brands.take(4).forEach { b ->
                            FilterChip(
                                selected = phoneBrand == b,
                                onClick = { phoneBrand = b },
                                label = { Text(b, fontSize = 11.sp) },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        brands.drop(4).take(4).forEach { b ->
                            FilterChip(
                                selected = phoneBrand == b,
                                onClick = { phoneBrand = b },
                                label = { Text(b, fontSize = 11.sp) },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = phoneModel,
                    onValueChange = { phoneModel = it },
                    label = { Text("मोबाइल का मॉडल (उदा. Vivo V29, Realme 11 Pro)") },
                    leadingIcon = { Icon(Icons.Default.Smartphone, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "समस्या क्या है (Problem):",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = ShyamBlueDark
                )

                Spacer(modifier = Modifier.height(6.dp))

                issueTypes.forEach { issue ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = issueType == issue,
                            onClick = { issueType = issue },
                            colors = RadioButtonDefaults.colors(selectedColor = ShyamBluePrimary)
                        )
                        Text(
                            text = issue,
                            fontSize = 12.sp,
                            fontWeight = if (issueType == issue) FontWeight.Bold else FontWeight.Normal,
                            color = if (issueType == issue) ShyamBluePrimary else Color(0xFF334155)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("समस्या का और विवरण (वैकल्पिक)") },
                    placeholder = { Text("उदा. टच काम नहीं कर रहा, चार्जिंग ढीली है...") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    maxLines = 3
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "ग्राहक का विवरण:",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = ShyamBlueDark
                )

                Spacer(modifier = Modifier.height(6.dp))

                OutlinedTextField(
                    value = customerName,
                    onValueChange = { customerName = it },
                    label = { Text("आपका शुभ नाम *") },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = customerPhone,
                    onValueChange = { if (it.length <= 10) customerPhone = it },
                    label = { Text("मोबाइल नंबर (व्हाट्सएप) *") },
                    leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Submit button
                Button(
                    onClick = {
                        if (customerName.isBlank() || customerPhone.isBlank()) {
                            Toast.makeText(context, "कृपया नाम व मोबाइल नंबर दर्ज करें", Toast.LENGTH_SHORT).show()
                            return@Button
                        }

                        val reqId = "REP-${(100..999).random()}"
                        val newReq = RepairRequest(
                            id = reqId,
                            phoneBrand = phoneBrand,
                            phoneModel = if (phoneModel.isBlank()) phoneBrand else "$phoneBrand $phoneModel",
                            issueType = issueType,
                            description = description,
                            customerName = customerName,
                            customerPhone = customerPhone,
                            visitType = visitType,
                            estCost = "जांच के उपरांत उचित मूल्य"
                        )
                        onSubmit(newReq)

                        // Open WhatsApp to notify shopkeeper
                        IntentUtils.openWhatsAppChat(
                            context,
                            ShopInfo.PRIMARY_PHONE,
                            "नमस्ते श्री श्याम मोबाइल केयर बूँदी,\nमैंने मोबाइल रिपेयरिंग हेतु रिक्वेस्ट भेजी है:\n" +
                                    "टोकन ID: $reqId\n" +
                                    "मॉडल: ${newReq.phoneModel}\n" +
                                    "समस्या: $issueType\n" +
                                    "ग्राहक: $customerName (${customerPhone})\n" +
                                    "कृपया कोटेशन दें।"
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = ShyamBluePrimary),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Icon(Icons.Default.Build, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "रिपेयरिंग बुक करें व व्हाट्सएप पर भेजें",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
