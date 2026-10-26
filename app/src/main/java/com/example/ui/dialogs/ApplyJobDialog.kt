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
import com.example.model.JobApplication
import com.example.model.LocalJob
import com.example.model.ShopInfo
import com.example.ui.theme.*
import com.example.util.IntentUtils

@Composable
fun ApplyJobDialog(
    job: LocalJob,
    isHindi: Boolean,
    onDismiss: () -> Unit,
    onSubmit: (JobApplication) -> Unit
) {
    val context = LocalContext.current
    var applicantName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var bundiAddress by remember { mutableStateOf("") }
    var qualification by remember { mutableStateOf("12वीं पास") }
    var experience by remember { mutableStateOf("फ्रेशर (कोई अनुभव नहीं)") }
    var notes by remember { mutableStateOf("") }

    val qualificationsList = listOf("10वीं पास", "12वीं पास", "स्नातक (BA/BSc/BCom)", "RSCIT / ITI", "पोस्ट ग्रेजुएट")
    val experienceList = listOf("फ्रेशर (कोई अनुभव नहीं)", "6 महीने से 1 वर्ष", "1 से 2 वर्ष", "2 वर्ष से अधिक")

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
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "जॉब हेतु आवेदन करें (Apply)",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black,
                            color = ShyamBluePrimary
                        )
                        Text(
                            text = "${job.titleHi} @ ${job.companyOrShop}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF64748B),
                            maxLines = 1
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }

                Divider(modifier = Modifier.padding(vertical = 12.dp))

                OutlinedTextField(
                    value = applicantName,
                    onValueChange = { applicantName = it },
                    label = { Text("उम्मीदवार का नाम (Candidate Name) *") },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = phone,
                    onValueChange = { if (it.length <= 10) phone = it },
                    label = { Text("मोबाइल नंबर (कॉलिंग / व्हाट्सएप) *") },
                    leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = bundiAddress,
                    onValueChange = { bundiAddress = it },
                    label = { Text("बूँदी में निवास स्थान / मोहल्ला (उदा. सदर बाजार / देवपुरा)") },
                    leadingIcon = { Icon(Icons.Default.Home, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(text = "शैक्षणिक योग्यता:", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = ShyamBlueDark)
                Spacer(modifier = Modifier.height(6.dp))

                Column {
                    qualificationsList.forEach { q ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 2.dp)
                        ) {
                            RadioButton(
                                selected = qualification == q,
                                onClick = { qualification = q },
                                colors = RadioButtonDefaults.colors(selectedColor = ShyamBluePrimary)
                            )
                            Text(text = q, fontSize = 12.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "कार्य अनुभव (Experience):", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = ShyamBlueDark)
                Spacer(modifier = Modifier.height(6.dp))

                Column {
                    experienceList.forEach { exp ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 2.dp)
                        ) {
                            RadioButton(
                                selected = experience == exp,
                                onClick = { experience = exp },
                                colors = RadioButtonDefaults.colors(selectedColor = ShyamBluePrimary)
                            )
                            Text(text = exp, fontSize = 12.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("अन्य कोई जानकारी या हुनर (Skill / Bike / Laptop)") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Submit button
                Button(
                    onClick = {
                        if (applicantName.isBlank() || phone.isBlank()) {
                            Toast.makeText(context, "कृपया नाम व फोन नंबर भरें!", Toast.LENGTH_SHORT).show()
                            return@Button
                        }

                        val newJobApp = JobApplication(
                            id = "JOBAPP-${(100..999).random()}",
                            jobId = job.id,
                            jobTitle = job.titleHi,
                            companyName = job.companyOrShop,
                            applicantName = applicantName,
                            phone = phone,
                            bundiAddress = bundiAddress,
                            qualification = qualification,
                            experience = experience,
                            notes = notes
                        )
                        onSubmit(newJobApp)

                        // Launch WhatsApp message to Job Provider / Shree Shyam
                        val contactTarget = if (job.whatsappPhone.isNotBlank()) job.whatsappPhone else ShopInfo.PRIMARY_PHONE
                        IntentUtils.openWhatsAppChat(
                            context,
                            contactTarget,
                            "नमस्ते, मैंने श्री श्याम ई-मित्र बूँदी ऐप पर आपकी जॉब '${job.titleHi} (${job.companyOrShop})' देखी है।\n" +
                                    "मेरा नाम: $applicantName\n" +
                                    "मोबाइल: $phone\n" +
                                    "पता: $bundiAddress\n" +
                                    "योग्यता: $qualification\n" +
                                    "अनुभव: $experience\n" +
                                    "कृपया इंटरव्यू का समय बताएं।"
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = ShyamBluePrimary),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Icon(Icons.Default.Send, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "आवेदन भेजें व नियोक्ता से बात करें",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
