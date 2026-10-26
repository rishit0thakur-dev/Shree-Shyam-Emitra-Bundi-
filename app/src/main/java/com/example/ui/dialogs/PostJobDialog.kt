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
import com.example.data.db.CustomJobEntity
import com.example.model.ShopInfo
import com.example.ui.theme.*
import com.example.util.IntentUtils

@Composable
fun PostJobDialog(
    isHindi: Boolean,
    onDismiss: () -> Unit,
    onSubmit: (CustomJobEntity) -> Unit
) {
    val context = LocalContext.current
    var jobTitle by remember { mutableStateOf("") }
    var companyOrShop by remember { mutableStateOf("") }
    var locationInBundi by remember { mutableStateOf("") }
    var salaryText by remember { mutableStateOf("₹10,000 - ₹15,000 / महीना") }
    var timings by remember { mutableStateOf("सुबह 9:00 AM से शाम 7:00 PM") }
    var qualification by remember { mutableStateOf("10वीं / 12वीं पास") }
    var whatToDoHi by remember { mutableStateOf("") }
    var contactPerson by remember { mutableStateOf("") }
    var contactPhone by remember { mutableStateOf("") }

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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "बूँदी में नई जॉब पोस्ट करें",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black,
                            color = ShyamBluePrimary
                        )
                        Text(
                            text = "दुकानदार व व्यापारी भाईयों के लिए फ्री सेवा",
                            fontSize = 12.sp,
                            color = Color(0xFF64748B)
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }

                Divider(modifier = Modifier.padding(vertical = 12.dp))

                OutlinedTextField(
                    value = jobTitle,
                    onValueChange = { jobTitle = it },
                    label = { Text("जॉब का शीर्षक (उदा. कम्प्यूटर ऑपरेटर / सेल्स बॉय) *") },
                    leadingIcon = { Icon(Icons.Default.Work, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = companyOrShop,
                    onValueChange = { companyOrShop = it },
                    label = { Text("दुकान / शोरूम / फर्म का नाम *") },
                    leadingIcon = { Icon(Icons.Default.Storefront, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = locationInBundi,
                    onValueChange = { locationInBundi = it },
                    label = { Text("स्थान (उदा. सदर बाजार / खोजा गेट / कोटा रोड बूँदी) *") },
                    leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = salaryText,
                    onValueChange = { salaryText = it },
                    label = { Text("वेतन (उदा. ₹10,000 - ₹15,000)") },
                    leadingIcon = { Icon(Icons.Default.CurrencyRupee, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = whatToDoHi,
                    onValueChange = { whatToDoHi = it },
                    label = { Text("क्या काम करना होगा (Role / Work Details) *") },
                    placeholder = { Text("उदा. ग्राहकों को सामान दिखाना, बिलिंग करना...") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    maxLines = 3
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = contactPerson,
                    onValueChange = { contactPerson = it },
                    label = { Text("संपर्क व्यक्ति का नाम *") },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = contactPhone,
                    onValueChange = { if (it.length <= 10) contactPhone = it },
                    label = { Text("मोबाइल नंबर (कॉल व व्हाट्सएप) *") },
                    leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (jobTitle.isBlank() || companyOrShop.isBlank() || contactPhone.isBlank()) {
                            Toast.makeText(context, "कृपया आवश्यक जानकारी भरें", Toast.LENGTH_SHORT).show()
                            return@Button
                        }

                        val customJob = CustomJobEntity(
                            id = "CUSTOM_JOB_${System.currentTimeMillis()}",
                            titleHi = jobTitle,
                            titleEn = jobTitle,
                            companyOrShop = companyOrShop,
                            locationInBundi = locationInBundi,
                            salaryText = salaryText,
                            jobType = "फुल टाइम",
                            timings = timings,
                            qualification = qualification,
                            whatToDoHi = whatToDoHi,
                            contactPerson = contactPerson,
                            contactPhone = contactPhone,
                            whatsappPhone = contactPhone,
                            timestamp = System.currentTimeMillis()
                        )
                        onSubmit(customJob)

                        // Also inform Shree Shyam E-mitra on WhatsApp for verification and posting
                        IntentUtils.openWhatsAppChat(
                            context,
                            ShopInfo.PRIMARY_PHONE,
                            "नमस्ते श्री श्याम ई-मित्र बूँदी, मैंने अपनी दुकान/फर्म के लिए नई जॉब पोस्ट की है:\n" +
                                    "पद: $jobTitle\n" +
                                    "फर्म: $companyOrShop\n" +
                                    "स्थान: $locationInBundi\n" +
                                    "वेतन: $salaryText\n" +
                                    "संपर्क: $contactPerson ($contactPhone)"
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = ShyamBluePrimary),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Icon(Icons.Default.AddBusiness, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("जॉब प्रकाशित करें (Publish Job)", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
            }
        }
    }
}
